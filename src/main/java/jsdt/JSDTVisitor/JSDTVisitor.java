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

package jsdt.JSDTVisitor;

import com.github.javaparser.ast.CompilationUnit;
import jsdt.grammar.JolieTypesLexer;
import jsdt.grammar.JolieTypesParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.lang.reflect.Type;
import java.util.List;

public class JSDTVisitor {

	public static List< CompilationUnit > visitInterfaces( List< JolieTypesParser.InterfaceDeclarationContext > interfaces, String interfaceName, String packageName ){
		return JSDTVisitorImplementation.generateInterfaceClass( interfaceName, packageName, interfaces );
	}

	public static List< CompilationUnit > visitTypes( List< JolieTypesParser.TypeDeclarationContext > types, String typeName, String packageName ){
		return JSDTVisitorImplementation.generateTypeClasses( typeName, packageName, types );
	}

	public static List< CompilationUnit > visit( List< JolieTypesParser.InterfaceDeclarationContext > interfaces, String interfaceName, String packageName, List< JolieTypesParser.TypeDeclarationContext > types ){
		return JSDTVisitorImplementation.generateInterfaceAndTypeClasses( interfaceName, packageName, interfaces, types );
	}

}
