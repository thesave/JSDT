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

package jolieParser;

import com.github.javaparser.ast.CompilationUnit;
import jolie.Interpreter;
import jolie.cli.CommandLineException;
import jolie.cli.CommandLineParser;
import jolie.lang.CodeCheckingException;
import jolie.lang.parse.*;
import jolie.lang.parse.ast.InterfaceDefinition;
import jolie.lang.parse.ast.Program;
import jolie.lang.parse.module.ModuleException;
import jolie.lang.parse.util.ParsingUtils;
import jsdt.JSDTVisitor.JSDTVisitor;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Test {

	public static void main( String[] args ) throws IOException, CommandLineException, ParserException, CodeCheckingException, ModuleException {

		String filename = "src/test/jolie/MyInterface.ol";
		String[] args_ = { filename };
		Interpreter.Configuration interpreterConfiguration =
						new CommandLineParser( args_, Test.class.getClassLoader() ).getInterpreterConfiguration();
		SemanticVerifier.Configuration configuration =
						new SemanticVerifier.Configuration( interpreterConfiguration.executionTarget() );
		configuration.setCheckForMain( false );
		final InputStream sourceIs;
		sourceIs = interpreterConfiguration.inputStream();
		Program program = ParsingUtils.parseProgram(
						sourceIs,
						interpreterConfiguration.programFilepath().toURI(),
						interpreterConfiguration.charset(),
						new String[ 0 ], // includesPath
						new String[ 0 ], // packagePath
						interpreterConfiguration.jolieClassLoader(),
						interpreterConfiguration.constants(),
						configuration,
						true );

		List< CompilationUnit > cu = JSDTVisitor.generateInterfaceAndTypeClasses(
						program.children().stream()
										.filter( e -> e instanceof InterfaceDefinition )
										.filter( e -> ( ( InterfaceDefinition ) e ).name().equals( "MyInterface" ) )
										.map( e -> ( ( InterfaceDefinition ) e ) )
										.findAny().get()
						, "MyPackage" );
		System.out.println( cu );
	}

}
