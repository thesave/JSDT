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

import jsdt.core.cardinality.Cardinalities;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import jsdt.grammar.JolieTypesParser;
import jsdt.grammar.JolieTypesVisitor;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.*;

class JSDTVisitorImplementation implements JolieTypesVisitor< Void > {

	private final List< CompilationUnit > compilationUnits;
	private final String packageName;
	private final Stack< String > lineage;
	private final Set< String > collectedInterfaceTypes;

	private JSDTVisitorImplementation( String packageName ) {
		this.compilationUnits = new LinkedList<>();
		this.lineage = new Stack<>();
		this.packageName = packageName;
		this.collectedInterfaceTypes = new HashSet<>();
	}

	public static List< CompilationUnit > generateTypeClasses( String symbolName, String packageName, List< JolieTypesParser.TypeDeclarationContext > ctx ) {
		JSDTVisitorImplementation jsdt = new JSDTVisitorImplementation( packageName );
		ctx.stream()
						.filter( td -> td.Identifier().getText().equals( symbolName ) )
						.forEach( jsdt::visitTypeDeclaration );
		return jsdt.compilationUnits;
	}

	public static List< CompilationUnit > generateInterfaceClass( String symbolName, String packageName, List< JolieTypesParser.InterfaceDeclarationContext > ctx ) {
		JSDTVisitorImplementation jsdt = new JSDTVisitorImplementation( packageName );
		ctx.stream()
						.filter( id -> id.Identifier().getText().equals( symbolName ) )
						.forEach( jsdt::visitInterfaceDeclaration );
		return jsdt.compilationUnits;
	}

	public static List< CompilationUnit >
	generateInterfaceAndTypeClasses( String symbolName,
																	 String packageName,
																	 List< JolieTypesParser.InterfaceDeclarationContext > ctx,
																	 List< JolieTypesParser.TypeDeclarationContext > types_ctx ) {
		JSDTVisitorImplementation jsdt = new JSDTVisitorImplementation( packageName );
		ctx.stream().filter( id -> id.Identifier().getText().equals( symbolName ) )
						.forEach( jsdt::visitInterfaceDeclaration );
		jsdt.collectedInterfaceTypes.forEach( typeName -> {
			types_ctx.stream().filter( td -> td.Identifier().getText().equals( typeName ) )
							.forEach( jsdt::visitTypeDeclaration );
		} );
		return jsdt.compilationUnits;
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
				return "ByteArray";
			default: // "any" or "undefined"
				return "Value";
		}
	}

	private Optional< String > jolieToIsValue( JolieTypesParser.NativeTypeContext ctx ) {
		String checker = null;
		switch ( ctx.getText() ) {
			case "void":
				break;
			case "bool":
				checker = "isBool";
				break;
			case "int":
				checker = "isInt";
				break;
			case "long":
				checker = "isLong";
				break;
			case "string":
				checker = "isString";
				break;
			case "byte":
				checker = "isByteArray";
				break;
			default: // "any" or "undefined"
				;
		}
		return Optional.ofNullable( checker );
	}

	private Optional< String > jolieToGetValueOptional( JolieTypesParser.NativeTypeContext ctx ) {
		return Optional.ofNullable( jolieToGetValue( ctx.getText() ) );
	}

	private String jolieToGetValue( String basicTypeName ) {
		String getter = null;
		switch ( basicTypeName ) {
			case "bool":
				getter = "boolValue";
				break;
			case "int":
				getter = "intValue";
				break;
			case "long":
				getter = "longValue";
				break;
			case "string":
				getter = "strValue";
				break;
			case "byte":
				getter = "byteArrayValue";
				break;
			default: // "void", "any" or "undefined"
				;
		}
		return getter;
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

	private String getLineage() {
		return String.join( "_", lineage );
	}

	private void visitTypeDeclarationOrSubnode(
					JolieTypesParser.NativeTypeContext nativeType,
					JolieTypesParser.NodesContext nodes,
					JolieTypesParser.TypeChoiceContext typeChoice
	) {
		CompilationUnit compilationUnit = new CompilationUnit();
		compilationUnit.setPackageDeclaration( packageName );

		if ( typeChoice == null ) {

			compilationUnit.addImport( "jsdt.core.cardinality.BasicType" );
			compilationUnit.addImport( "jolie.runtime.Value" );

			String javaNativeType = jolieToJavaType( nativeType );

			ClassOrInterfaceDeclaration theClass = compilationUnit.addClass( getLineage() )
							.setModifier( Modifier.Keyword.PUBLIC, true )
							.addExtendedType( "BasicType<" + javaNativeType + ">" );

			ConstructorDeclaration constructorDeclaration = theClass.addConstructor( Modifier.Keyword.PUBLIC );
			BlockStmt constructorDeclarationBody = constructorDeclaration.createBody();

			MethodDeclaration parseMethod = theClass.addMethod( "parse", Modifier.Keyword.PUBLIC, Modifier.Keyword.STATIC );
			parseMethod.addParameter( "Value", "value" );

			MethodDeclaration toValueMethod = theClass.addMethod( "toValue", Modifier.Keyword.PUBLIC );
			BlockStmt toValueMethodBody = toValueMethod.createBody();
			toValueMethodBody.addStatement( "Value value = super.toValue();" );
			toValueMethod.setType( "Value" );

			StringJoiner parseReturnParameters = new StringJoiner( ", " );
			if ( !nativeType.getText().equals( "void" ) ) {
				parseReturnParameters.add(
								jolieToGetValueOptional( nativeType ).isEmpty() ? "value" : "value." + jolieToGetValueOptional( nativeType ).get() + "()" );
			}

			parseMethod.setType( new ClassOrInterfaceType().setName( getLineage() ) );
			BlockStmt parseBody = parseMethod.createBody();
			IfStmt parseIfStm = new IfStmt();
			parseBody.addStatement( parseIfStm );
			parseIfStm.setCondition( new ExpressionStmt()
							.setExpression( "value != null"
											+ ( jolieToIsValue( nativeType ).isEmpty() ? "" : " && value." + jolieToIsValue( nativeType ).get() + "()" ) )
							.getExpression() );
			BlockStmt ifBranch = new BlockStmt();
			parseIfStm.setElseStmt( new BlockStmt().addStatement( "return null;" ) );
			parseIfStm.setThenStmt( ifBranch );

			if ( !nativeType.getText().equals( "void" ) ) {
				constructorDeclaration.addParameter( jolieToJavaType( nativeType ), "root" );
				constructorDeclarationBody.addStatement( "super( root );" );
			} else {
				constructorDeclarationBody.addStatement( "super( null );" );
			}
			if ( nodes != null ) {

				nodes.subNodes().forEach( node -> {
					String nodeName = node.Identifier().getText();
					lineage.push( nodeName );

					visitSubNodes( node );

					Cardinalities cardinalityClass = getCardinalityClass( node.cardinality() );
					compilationUnit.addImport( "jsdt.core.cardinality." + cardinalityClass );
					if ( cardinalityClass.equals( Cardinalities.Multi ) ) {
						compilationUnit.addImport( "java.util.stream.Collectors" );
					}
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
										.add( cardinalityClass + ".of( value.getChildren(" )
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
										.add( "value.getChildren(" )
										.add( "\"" + nodeName + "\"" )
										.add( ").get( 0 ) ) );" );
					}
					ifBranch.addStatement( s.toString() );
					parseReturnParameters.add( nodeName );
					toValueMethodBody.addStatement( "this." + nodeName + "().addChildenIfNotEmpty(\"" + nodeName + "\", value);" );
					lineage.pop();
				} );
			}
			ifBranch.addStatement( "return new " + getLineage() + "(" + parseReturnParameters + ");" );
			toValueMethodBody.addStatement( "return value;" );

			compilationUnits.add( compilationUnit );

		}
		// if YES
		// create a class that extends ChoiceType
		// then create the class for the two branches of the choice
		else {
			// we create a compilation unit for the choice
			// and then two compilation units for the left and right choice

			compilationUnit.addImport( "jsdt.core.cardinality.ChoiceType" );
			compilationUnit.addImport( "jolie.runtime.Value" );

			String leftClassName = getLineage() + "_1";
			String rightClassName = getLineage() + "_2";

			ClassOrInterfaceDeclaration theClass = compilationUnit.addClass( getLineage() )
							.setModifier( Modifier.Keyword.PUBLIC, true )
							.addExtendedType( "ChoiceType<" + leftClassName + ", " + rightClassName + ">" );
			ConstructorDeclaration constructorDeclaration = theClass.addConstructor( Modifier.Keyword.PUBLIC );
			constructorDeclaration.addParameter( leftClassName, "left" );
			constructorDeclaration.addParameter( rightClassName, "right" );
			BlockStmt constructorDeclarationBody = constructorDeclaration.createBody();
			constructorDeclarationBody.addStatement( "super( left, right );" );

			MethodDeclaration parseMethod = theClass.addMethod( "parse", Modifier.Keyword.PUBLIC, Modifier.Keyword.STATIC );
			parseMethod.addParameter( "Value", "value" );
			parseMethod.setType( new ClassOrInterfaceType().setName( getLineage() ) );
			BlockStmt parseBody = parseMethod.createBody();
			parseBody.addStatement( leftClassName + " left = " + leftClassName + ".parse( value );" );
			parseBody.addStatement( rightClassName + " right = " + rightClassName + ".parse( value );" );
			IfStmt parseIfStm = new IfStmt();
			parseBody.addStatement( parseIfStm );
			parseIfStm.setCondition( new ExpressionStmt().setExpression( "left == null && right == null" ).getExpression() );
			parseIfStm.setThenStmt( new BlockStmt().addStatement( "return null;" ) );
			parseIfStm.setElseStmt( new BlockStmt().addStatement( "return new " + getLineage() + "(left, right);" ) );

			lineage.push( "1" );
			visitTypeDeclarationOrSubnode( nativeType, nodes, null );
			lineage.pop();

			lineage.push( "2" );
			visitTypeDeclarationOrSubnode( typeChoice.nativeType(), typeChoice.nodes(), typeChoice.typeChoice() );
			lineage.pop();

			compilationUnits.add( compilationUnit );
		}
	}

	private void assembleOneWayMethod( MethodDeclaration methodDeclaration, String requestTypeName ) {
		collectedInterfaceTypes.add( requestTypeName );
		// we do not set the type of the method, since, if it is not set, it defaults to void
		methodDeclaration.setModifiers( Modifier.Keyword.PUBLIC );
		methodDeclaration.addParameter( "Value", "value" );
		BlockStmt methodBody = methodDeclaration.createBody();
		switch ( requestTypeName ) {
			case "void":
				break;
			case "bool":
				methodBody.addStatement( "boolean request = value." + jolieToGetValue( requestTypeName ) + "();" );
				break;
			case "int":
				methodBody.addStatement( "int request = value." + jolieToGetValue( requestTypeName ) + "();" );
				break;
			case "long":
				methodBody.addStatement( "long request = value." + jolieToGetValue( requestTypeName ) + "();" );
				break;
			case "string":
				methodBody.addStatement( "String request = value." + jolieToGetValue( requestTypeName ) + "();" );
				break;
			case "byte":
				methodBody.addStatement( "ByteArray request = value." + jolieToGetValue( requestTypeName ) + "();" );
				break;
			default:
				methodBody.addStatement( requestTypeName + " request = " + requestTypeName + ".parse( value );" );
		}
	}

	private void assembleRequestResponseMethod( MethodDeclaration methodDeclaration, String requestTypeName, String responseTypeName ) {
		collectedInterfaceTypes.add( responseTypeName );
		methodDeclaration.setType( "Value" );
		assembleOneWayMethod( methodDeclaration, requestTypeName );
	}

	@Override
	public Void visitTypesOrInterfaces( JolieTypesParser.TypesOrInterfacesContext ctx ) {
		return null;
	}

	@Override
	public Void visitInterfaceDeclaration( JolieTypesParser.InterfaceDeclarationContext ctx ) {
		CompilationUnit compilationUnit = new CompilationUnit();
		compilationUnit.addImport( "jolie.runtime.JavaService" );
		compilationUnit.addImport( "jolie.runtime.Value" );
		compilationUnit.setPackageDeclaration( packageName );
		ClassOrInterfaceDeclaration theClass = compilationUnit.addClass( ctx.Identifier().getText() + "Service" );
		theClass.setModifier( Modifier.Keyword.PUBLIC, true );
		theClass.addExtendedType( "JavaService" );
		ctx.oneWays().forEach( oneWaysContext -> {
			MethodDeclaration methodDeclaration = theClass.addMethod( oneWaysContext.operation.getText() );
			assembleOneWayMethod( methodDeclaration, oneWaysContext.oneWayType.getText() );
		} );
		ctx.requestResponses().forEach( requestResponsesContext -> {
			MethodDeclaration methodDeclaration = theClass.addMethod( requestResponsesContext.operation.getText() );
			compilationUnit.addImport( "jolie.runtime.embedding.RequestResponse" );
			methodDeclaration.addAnnotation( "RequestResponse" );
			assembleRequestResponseMethod(
							methodDeclaration,
							requestResponsesContext.requestType.getText(),
							requestResponsesContext.responseType.getText()
			);
		} );
		compilationUnits.add( compilationUnit );
		return null;
	}

	@Override
	public Void visitOneWays( JolieTypesParser.OneWaysContext ctx ) {
		return null;
	}

	@Override
	public Void visitRequestResponses( JolieTypesParser.RequestResponsesContext ctx ) {
		return null;
	}

	@Override
	public Void visitThrowTypeList( JolieTypesParser.ThrowTypeListContext ctx ) {
		return null;
	}

	@Override
	public Void visitIdentifierOrNativeType( JolieTypesParser.IdentifierOrNativeTypeContext ctx ) {
		return null;
	}

	@Override
	public Void visitTypeDeclaration( JolieTypesParser.TypeDeclarationContext ctx ) {
		lineage.push( ctx.Identifier().getText() );
		visitTypeDeclarationOrSubnode( ctx.nativeType(), ctx.nodes(), ctx.typeChoice() );
		lineage.pop();
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
		visitTypeDeclarationOrSubnode( ctx.nativeType(), ctx.nodes(), ctx.typeChoice() );
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
