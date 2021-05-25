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

package JSDTVisitor;

import cardinality.Cardinalities;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import grammar.JolieTypesParser;
import grammar.JolieTypesVisitor;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.*;
import java.util.stream.Collectors;

class JSDTVisitorImplementation implements JolieTypesVisitor< Void > {

	private final List< CompilationUnit > compilationUnits;
	private Stack< String > lineage;

	public JSDTVisitorImplementation() {
		this.compilationUnits = new LinkedList<>();
		this.lineage = new Stack<>();
	}

	public List< CompilationUnit > visit( JolieTypesParser.TypeDeclarationContext ctx ) {
		this.visitTypeDeclaration( ctx );
		return this.compilationUnits;
	}

	private String jolieToJavaType( JolieTypesParser.NativeTypeContext ctx ) {
		switch ( ctx.getText() ) {
			case "void":
				return "Void";
			case "bool":
				return "Boolean";
			case "int":
				return "Integer";
			case "long":
				return "Long";
			case "string":
				return "String";
			case "byte":
				return "Byte";
			default: // "any" or "undefined"
				return "Value";
		}
	}

	private Cardinalities getCardinalityClass( JolieTypesParser.CardinalityContext ctx ) {
		if ( ctx == null || ctx.Single() != null ) {
			return Cardinalities.Single;
		} else if ( ctx.MaybeSingle() != null ) {
			return Cardinalities.MaybeSingle;
		} else {
			return Cardinalities.Multi;
		}
	}

	private String getLineage(){
		return String.join( "_", lineage );
	}

	@Override
	public Void visitTypeDeclaration( JolieTypesParser.TypeDeclarationContext ctx ) {
		// check if it is a type choice
		// if NO
		if ( ctx.typeChoice() == null ) {
			CompilationUnit compilationUnit = new CompilationUnit();

			compilationUnit.addImport( "types.BasicType" );
			compilationUnit.addImport( "jolie.runtime.Value" );

			String nativeType = jolieToJavaType( ctx.nativeType() );

			lineage.push( ctx.Identifier().getText() );

			ClassOrInterfaceDeclaration theClass = compilationUnit.addClass( getLineage() )
							.setModifier( Modifier.Keyword.PUBLIC, true )
							.addExtendedType( "BasicType<" + nativeType + ">" );

			ConstructorDeclaration constructorDeclaration = theClass.addConstructor( Modifier.Keyword.PUBLIC );
			BlockStmt constructorDeclarationBody = constructorDeclaration.createBody();

			MethodDeclaration parseMethod = theClass.addMethod( "parse", Modifier.Keyword.PUBLIC, Modifier.Keyword.STATIC );
			parseMethod.addParameter( "Value", "value" );

			StringJoiner parseReturnParameters = new StringJoiner( ", " );
			switch ( ctx.nativeType().getText() ) {
				case "bool":
				case "int":
				case "long":
				case "string":
				case "byte":
					parseReturnParameters.add( "v." + ctx.nativeType().getText() + "Value()" );
					break;
				case "any":
				case "undefined":
					parseReturnParameters.add( "v" );
					break;
				default: // "void"
					break;
			}

			parseMethod.setType( new ClassOrInterfaceType().setName( getLineage() ) );
			BlockStmt parseBody = parseMethod.createBody();
			IfStmt parseIfStm = new IfStmt();
			parseBody.addStatement( parseIfStm );
			parseIfStm.setCondition( new ExpressionStmt().setExpression( "v != null" ).getExpression() );
			BlockStmt ifBranch = new BlockStmt();
			parseIfStm.setElseStmt( new BlockStmt().addStatement( "return null;" ) );
			parseIfStm.setThenStmt( ifBranch );

			if ( ctx.nativeType().getText().equals( "void" ) ) {
				constructorDeclaration.addParameter( jolieToJavaType( ctx.nativeType() ), "value" );
				constructorDeclarationBody.addStatement( "super( value );" );
			} else {
				constructorDeclarationBody.addStatement( "super();" );
			}
			if ( ctx.nodes() != null ) {


				ctx.nodes().subNodes().forEach( node -> {
					String nodeName = node.Identifier().getText();
					lineage.push( nodeName );

					visitSubNodes( node );

					Cardinalities cardinalityClass = getCardinalityClass( node.cardinality() );
					compilationUnit.addImport( "cardinality." + cardinalityClass );
					FieldDeclaration field = theClass.addField(
									cardinalityClass + "<" + getLineage() + ">",
									node.Identifier().getText(),
									Modifier.Keyword.PRIVATE, Modifier.Keyword.FINAL
					);
					constructorDeclaration.addParameter( field.getCommonType(), nodeName );
					constructorDeclarationBody.addStatement( "this." + nodeName + "=" + nodeName + ";" );
					field.createGetter().setName( nodeName );
					StringJoiner s = new StringJoiner( " " );
					if ( cardinalityClass.equals( Cardinalities.Multi ) ) {
						s.add( cardinalityClass + "<" + getLineage() + ">" )
										.add( nodeName )
										.add( "=" )
										.add( cardinalityClass + ".of( v.getChildren(" )
										.add( "\"" + nodeName + "\"" )
										.add( ").stream().map(" )
										.add( getLineage() + "::parse" )
										.add( ").collect( Collectors.toList() ) );" );
					} else {
						s.add( cardinalityClass + "<" + getLineage() + ">" )
										.add( nodeName )
										.add( "=" )
										.add( cardinalityClass + ".of(" )
										.add( getLineage() + ".parse(" )
										.add( "v.getChildren(" )
										.add( "\"" + nodeName + "\"" )
										.add( ").get( 0 ) ) );" );
					}
					ifBranch.addStatement( s.toString() );
					parseReturnParameters.add( nodeName );
					lineage.pop();
				} );
			}
			ifBranch.addStatement( "return new " + ctx.Identifier().getText() + "(" + parseReturnParameters + ");" );

			compilationUnits.add( compilationUnit );
		}
		// if YES
		// create a class that extends ChoiceType
		// then create the class for the two branches of the choice
		else {

		}
		return null;
	}

	@Override
	public Void visitNativeType( JolieTypesParser.NativeTypeContext ctx ) {
		return null;
	}

	@Override
	public Void visitNodes( JolieTypesParser.NodesContext ctx ) {
		return null;
	}

	@Override
	public Void visitTypeChoice( JolieTypesParser.TypeChoiceContext ctx ) {
		return null;
	}

	@Override
	public Void visitCardinality( JolieTypesParser.CardinalityContext ctx ) {
		return null;
	}

	@Override
	public Void visitSubNodes( JolieTypesParser.SubNodesContext ctx ) {
		// check if it is a type choice
		// if NO
		if ( ctx.typeChoice() == null ) {
			CompilationUnit compilationUnit = new CompilationUnit();

			compilationUnit.addImport( "types.BasicType" );
			compilationUnit.addImport( "jolie.runtime.Value" );

			String nativeType = jolieToJavaType( ctx.nativeType() );

			ClassOrInterfaceDeclaration theClass = compilationUnit.addClass( getLineage() )
							.setModifier( Modifier.Keyword.PUBLIC, true )
							.addExtendedType( "BasicType<" + nativeType + ">" );

			ConstructorDeclaration constructorDeclaration = theClass.addConstructor( Modifier.Keyword.PUBLIC );
			BlockStmt constructorDeclarationBody = constructorDeclaration.createBody();

			MethodDeclaration parseMethod = theClass.addMethod( "parse", Modifier.Keyword.PUBLIC, Modifier.Keyword.STATIC );
			parseMethod.addParameter( "Value", "value" );

			StringJoiner parseReturnParameters = new StringJoiner( ", " );
			switch ( ctx.nativeType().getText() ) {
				case "bool":
				case "int":
				case "long":
				case "string":
				case "byte":
					parseReturnParameters.add( "v." + ctx.nativeType().getText() + "Value()" );
					break;
				case "any":
				case "undefined":
					parseReturnParameters.add( "v" );
					break;
				default: // "void"
					break;
			}

			parseMethod.setType( new ClassOrInterfaceType().setName( getLineage() ) );
			BlockStmt parseBody = parseMethod.createBody();
			IfStmt parseIfStm = new IfStmt();
			parseBody.addStatement( parseIfStm );
			parseIfStm.setCondition( new ExpressionStmt().setExpression( "v != null" ).getExpression() );
			BlockStmt ifBranch = new BlockStmt();
			parseIfStm.setElseStmt( new BlockStmt().addStatement( "return null;" ) );
			parseIfStm.setThenStmt( ifBranch );

			if ( ctx.nativeType().getText().equals( "void" ) ) {
				constructorDeclaration.addParameter( jolieToJavaType( ctx.nativeType() ), "value" );
				constructorDeclarationBody.addStatement( "super( value );" );
			} else {
				constructorDeclarationBody.addStatement( "super();" );
			}
			if ( ctx.nodes() != null ) {


				ctx.nodes().subNodes().forEach( node -> {
					String nodeName = node.Identifier().getText();
					lineage.push( nodeName );

					visitSubNodes( node );

					Cardinalities cardinalityClass = getCardinalityClass( node.cardinality() );
					compilationUnit.addImport( "cardinality." + cardinalityClass );
					FieldDeclaration field = theClass.addField(
									cardinalityClass + "<" + getLineage() + ">",
									node.Identifier().getText(),
									Modifier.Keyword.PRIVATE, Modifier.Keyword.FINAL
					);
					constructorDeclaration.addParameter( field.getCommonType(), nodeName );
					constructorDeclarationBody.addStatement( "this." + nodeName + "=" + nodeName + ";" );
					field.createGetter().setName( nodeName );
					StringJoiner s = new StringJoiner( " " );
					if ( cardinalityClass.equals( Cardinalities.Multi ) ) {
						s.add( cardinalityClass + "<" + getLineage() + ">" )
										.add( nodeName )
										.add( "=" )
										.add( cardinalityClass + ".of( v.getChildren(" )
										.add( "\"" + nodeName + "\"" )
										.add( ").stream().map(" )
										.add( getLineage() + "::parse" )
										.add( ").collect( Collectors.toList() ) );" );
					} else {
						s.add( cardinalityClass + "<" + getLineage() + ">" )
										.add( nodeName )
										.add( "=" )
										.add( cardinalityClass + ".of(" )
										.add( getLineage() + ".parse(" )
										.add( "v.getChildren(" )
										.add( "\"" + nodeName + "\"" )
										.add( ").get( 0 ) ) );" );
					}
					ifBranch.addStatement( s.toString() );
					parseReturnParameters.add( nodeName );
					lineage.pop();
				} );
			}
			ifBranch.addStatement( "return new " + ctx.Identifier().getText() + "(" + parseReturnParameters + ");" );

			compilationUnits.add( compilationUnit );
		}
		// if YES
		// create a class that extends ChoiceType
		// then create the class for the two branches of the choice
		else {

		}
		return null;
	}

	@Override
	public Void visit( ParseTree parseTree ) {
		return null;
	}

	@Override
	public Void visitChildren( RuleNode ruleNode ) {
		return null;
	}

	@Override
	public Void visitTerminal( TerminalNode terminalNode ) {
		return null;
	}

	@Override
	public Void visitErrorNode( ErrorNode errorNode ) {
		return null;
	}
}
