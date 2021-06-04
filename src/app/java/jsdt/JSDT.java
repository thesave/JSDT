/******************************************************************************
 * Copyright (C) 2021 by Saverio Giallorenzo <saverio.giallorenzo@gmail.com>  *
 *                                                                            *
 * This program is free software; you can redistribute it and/or modify       *
 * it under the terms of the GNU Library General Public License as            *
 * published by the Free Software Foundation; either version 2 of the         *
 * License, or (at your option) any later version.                            *
 *                                                                            *
 * This program is distributed in the hope that it will be useful,            *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of             *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the              *
 * GNU General Public License for more details.                               *
 *                                                                            *
 * You should have received a copy of the GNU Library General Public          *
 * License along with this program; if not, write to the                      *
 * Free Software Foundation, Inc.,                                            *
 * 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.                  *
 *                                                                            *
 * For details about the authors of this software, see the AUTHORS file.      *
 ******************************************************************************/

package jsdt;

import jsdt.JSDTVisitor.JSDTVisitor;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.nodeTypes.NodeWithSimpleName;
import jsdt.grammar.JolieTypesLexer;
import jsdt.grammar.JolieTypesParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import picocli.CommandLine;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;


@CommandLine.Command(
				name = "jsdt",
				mixinStandardHelpOptions = true,
				versionProvider = JSDT.VersionProvider.class,
				description = { "JavaService Development Tool" }
)
public class JSDT implements Callable< Integer > {

	@CommandLine.Parameters(index = "0", description = "The .ol file containing the type(s) and/or interface(s) to compile.")
	private File file;

	@CommandLine.Parameters(index = "1", description = "The name of the symbol source target of the compilation. By default it is an interface.")
	private String symbolName;

	@CommandLine.Option(names = { "--type" }, description = "Indicates that the target symbol is a type, instead of an interface.")
	private boolean targetIsType;

	@CommandLine.Option(names = { "--compileTypes" }, description = "Compile also the types used by the target interface.")
	private boolean compileTypes;

	@CommandLine.Option(names = { "--package" }, description = "The name of the package of the generated Java classes.")
	private String packageName;

	@CommandLine.Option(names = { "--dstDir" }, description = "The path of the destination directory of the generated Java classes.")
	private String dstDir = ".";

	public static void main( String[] args ) {
		System.exit( run( args ) );
	}

	public static int run( String[] args ) {
		CommandLine c = new CommandLine( new JSDT() );
		c.setCaseInsensitiveEnumValuesAllowed( true );
		return c.execute( args );
	}

	@Override
	public Integer call() {
		CharStream cs;
		try {
			cs = CharStreams.fromPath( file.toPath() );
			JolieTypesLexer lexer = new JolieTypesLexer( cs );
			CommonTokenStream tokens = new CommonTokenStream( lexer );
			JolieTypesParser parser = new JolieTypesParser( tokens );
			packageName = ( packageName == null ) ? symbolName : packageName;
			List< CompilationUnit > compilationUnits = null;
			JolieTypesParser.TypesOrInterfacesContext ctx = parser.typesOrInterfaces();
			if ( targetIsType ) {
				compilationUnits = JSDTVisitor.visitTypes( ctx.typeDeclaration(), symbolName, packageName );
			} else if ( compileTypes ) {
				compilationUnits = JSDTVisitor.visit( ctx.interfaceDeclaration(), symbolName, packageName, ctx.typeDeclaration() );
			} else {
				compilationUnits = JSDTVisitor.visitInterfaces( ctx.interfaceDeclaration(), symbolName, packageName );
			}
			if ( compilationUnits == null || compilationUnits.isEmpty() ) {
				System.err.println( "No classes have been generated. Please, check the input file and the launch parameters." );
			} else {
				Path destinationPath = Path.of( dstDir ).resolve( packageName );
				Files.createDirectories( destinationPath );
				for ( CompilationUnit cu : compilationUnits ) {
					if ( cu.getTypes().size() > 1 ) {
						throw new RuntimeException( "There should be a 1:1 correspondence between compilationUnits and classes, found "
										+ cu.getTypes().size() + ": "
										+ cu.getTypes().stream().map( NodeWithSimpleName::getNameAsString ).collect( Collectors.joining( "," ) ) );
					}
					Path filePath = destinationPath.resolve( cu.getTypes().get( 0 ).getNameAsString() + ".java" );
					if ( filePath.toFile().exists() ) {
						filePath.toFile().delete();
					}
					Files.createFile( filePath );
					Files.writeString( filePath, cu.toString(), StandardOpenOption.WRITE );
				}
			}
			return 0;
		} catch ( IOException e ) {
			e.printStackTrace();
			return 1;
		}
	}

	static class VersionProvider implements CommandLine.IVersionProvider {

		@Override
		public String[] getVersion() throws Exception {
			Properties properties = ProjectPropertiesLoader.loadPropertyFile( JSDT.class.getClassLoader() );
			return new String[]{ "JSDT version " + properties.getProperty( "version" ) };
		}
	}

	static class ProjectPropertiesLoader {

		public static Properties loadPropertyFile( ClassLoader c ) throws IOException {
			Properties properties = new Properties();
			String propertiesFileName = "project.properties";
			InputStream inputStream = c.getResourceAsStream( propertiesFileName );
			properties.load( inputStream );
			return properties;
		}
	}


}

