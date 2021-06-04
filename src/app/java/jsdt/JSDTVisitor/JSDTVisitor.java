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
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import jolie.lang.NativeType;
import jolie.lang.parse.OLVisitor;
import jolie.lang.parse.ast.*;
import jolie.lang.parse.ast.courier.CourierChoiceStatement;
import jolie.lang.parse.ast.courier.CourierDefinitionNode;
import jolie.lang.parse.ast.courier.NotificationForwardStatement;
import jolie.lang.parse.ast.courier.SolicitResponseForwardStatement;
import jolie.lang.parse.ast.expression.*;
import jolie.lang.parse.ast.types.*;
import jsdt.core.cardinality.Cardinalities;

import java.util.*;

import static jsdt.JSDTVisitor.JSDTVisitorUtils.*;

public class JSDTVisitor implements OLVisitor< Void, Void > {

	private final List< CompilationUnit > compilationUnits;
	private final String packageName;
	private final Stack< String > lineage;
	private final Set< TypeDefinition > collectedInterfaceTypes;
	static private final Set< String > visitedTypes = new HashSet<>();

	private JSDTVisitor( String packageName ) {
		this.compilationUnits = new LinkedList<>();
		this.lineage = new Stack<>();
		this.packageName = packageName;
		this.collectedInterfaceTypes = new HashSet<>();
	}

	public static List< CompilationUnit > generateTypeClasses( TypeDefinition ctx, String packageName ) {
		JSDTVisitor jsdt = new JSDTVisitor( packageName );
		jsdt.lineage.push( ctx.name() );
		jsdt.visit( ctx );
		jsdt.lineage.pop();
		return jsdt.compilationUnits;
	}

	public static List< CompilationUnit > generateInterfaceClass( InterfaceDefinition ctx, String packageName ) {
		JSDTVisitor jsdt = new JSDTVisitor( packageName );
		jsdt.visit( ctx, null );
		return jsdt.compilationUnits;
	}

	public static List< CompilationUnit > generateInterfaceAndTypeClasses( InterfaceDefinition ctx, String packageName ){
		JSDTVisitor jsdt = new JSDTVisitor( packageName );
		jsdt.visit( ctx, null );
		jsdt.collectedInterfaceTypes.forEach( td -> {
			jsdt.compilationUnits.addAll( generateTypeClasses( td, packageName ) );
		});
		return jsdt.compilationUnits;
	}

	private String getLineage() {
		return String.join( "_", lineage );
	}

	public void visit( TypeDefinition typeDefinition ) {
		if ( typeDefinition instanceof TypeInlineDefinition ) {
			visit( ( TypeInlineDefinition ) typeDefinition, null );
		} else if ( typeDefinition instanceof TypeDefinitionLink ) {
			visit( ( TypeDefinitionLink ) typeDefinition, null );
		} else if ( typeDefinition instanceof TypeChoiceDefinition ) {
			visit( ( TypeChoiceDefinition ) typeDefinition, null );
		} else {
			throw new RuntimeException( "Unrecognized " + typeDefinition.getClass() );
		}
	}

	@Override
	public Void visit( Program program, Void unused ) {
		return null;
	}

	@Override
	public Void visit( OneWayOperationDeclaration oneWayOperationDeclaration, Void unused ) {
		return null;
	}

	@Override
	public Void visit( RequestResponseOperationDeclaration requestResponseOperationDeclaration, Void unused ) {
		return null;
	}

	@Override
	public Void visit( DefinitionNode definitionNode, Void unused ) {
		return null;
	}

	@Override
	public Void visit( ParallelStatement parallelStatement, Void unused ) {
		return null;
	}

	@Override
	public Void visit( SequenceStatement sequenceStatement, Void unused ) {
		return null;
	}

	@Override
	public Void visit( NDChoiceStatement ndChoiceStatement, Void unused ) {
		return null;
	}

	@Override
	public Void visit( OneWayOperationStatement oneWayOperationStatement, Void unused ) {
		return null;
	}

	@Override
	public Void visit( RequestResponseOperationStatement requestResponseOperationStatement, Void unused ) {
		return null;
	}

	@Override
	public Void visit( NotificationOperationStatement notificationOperationStatement, Void unused ) {
		return null;
	}

	@Override
	public Void visit( SolicitResponseOperationStatement solicitResponseOperationStatement, Void unused ) {
		return null;
	}

	@Override
	public Void visit( LinkInStatement linkInStatement, Void unused ) {
		return null;
	}

	@Override
	public Void visit( LinkOutStatement linkOutStatement, Void unused ) {
		return null;
	}

	@Override
	public Void visit( AssignStatement assignStatement, Void unused ) {
		return null;
	}

	@Override
	public Void visit( AddAssignStatement addAssignStatement, Void unused ) {
		return null;
	}

	@Override
	public Void visit( SubtractAssignStatement subtractAssignStatement, Void unused ) {
		return null;
	}

	@Override
	public Void visit( MultiplyAssignStatement multiplyAssignStatement, Void unused ) {
		return null;
	}

	@Override
	public Void visit( DivideAssignStatement divideAssignStatement, Void unused ) {
		return null;
	}

	@Override
	public Void visit( IfStatement ifStatement, Void unused ) {
		return null;
	}

	@Override
	public Void visit( DefinitionCallStatement definitionCallStatement, Void unused ) {
		return null;
	}

	@Override
	public Void visit( WhileStatement whileStatement, Void unused ) {
		return null;
	}

	@Override
	public Void visit( OrConditionNode orConditionNode, Void unused ) {
		return null;
	}

	@Override
	public Void visit( AndConditionNode andConditionNode, Void unused ) {
		return null;
	}

	@Override
	public Void visit( NotExpressionNode notExpressionNode, Void unused ) {
		return null;
	}

	@Override
	public Void visit( CompareConditionNode compareConditionNode, Void unused ) {
		return null;
	}

	@Override
	public Void visit( ConstantIntegerExpression constantIntegerExpression, Void unused ) {
		return null;
	}

	@Override
	public Void visit( ConstantDoubleExpression constantDoubleExpression, Void unused ) {
		return null;
	}

	@Override
	public Void visit( ConstantBoolExpression constantBoolExpression, Void unused ) {
		return null;
	}

	@Override
	public Void visit( ConstantLongExpression constantLongExpression, Void unused ) {
		return null;
	}

	@Override
	public Void visit( ConstantStringExpression constantStringExpression, Void unused ) {
		return null;
	}

	@Override
	public Void visit( ProductExpressionNode productExpressionNode, Void unused ) {
		return null;
	}

	@Override
	public Void visit( SumExpressionNode sumExpressionNode, Void unused ) {
		return null;
	}

	@Override
	public Void visit( VariableExpressionNode variableExpressionNode, Void unused ) {
		return null;
	}

	@Override
	public Void visit( NullProcessStatement nullProcessStatement, Void unused ) {
		return null;
	}

	@Override
	public Void visit( Scope scope, Void unused ) {
		return null;
	}

	@Override
	public Void visit( InstallStatement installStatement, Void unused ) {
		return null;
	}

	@Override
	public Void visit( CompensateStatement compensateStatement, Void unused ) {
		return null;
	}

	@Override
	public Void visit( ThrowStatement throwStatement, Void unused ) {
		return null;
	}

	@Override
	public Void visit( ExitStatement exitStatement, Void unused ) {
		return null;
	}

	@Override
	public Void visit( ExecutionInfo executionInfo, Void unused ) {
		return null;
	}

	@Override
	public Void visit( CorrelationSetInfo correlationSetInfo, Void unused ) {
		return null;
	}

	@Override
	public Void visit( InputPortInfo inputPortInfo, Void unused ) {
		return null;
	}

	@Override
	public Void visit( OutputPortInfo outputPortInfo, Void unused ) {
		return null;
	}

	@Override
	public Void visit( PointerStatement pointerStatement, Void unused ) {
		return null;
	}

	@Override
	public Void visit( DeepCopyStatement deepCopyStatement, Void unused ) {
		return null;
	}

	@Override
	public Void visit( RunStatement runStatement, Void unused ) {
		return null;
	}

	@Override
	public Void visit( UndefStatement undefStatement, Void unused ) {
		return null;
	}

	@Override
	public Void visit( ValueVectorSizeExpressionNode valueVectorSizeExpressionNode, Void unused ) {
		return null;
	}

	@Override
	public Void visit( PreIncrementStatement preIncrementStatement, Void unused ) {
		return null;
	}

	@Override
	public Void visit( PostIncrementStatement postIncrementStatement, Void unused ) {
		return null;
	}

	@Override
	public Void visit( PreDecrementStatement preDecrementStatement, Void unused ) {
		return null;
	}

	@Override
	public Void visit( PostDecrementStatement postDecrementStatement, Void unused ) {
		return null;
	}

	@Override
	public Void visit( ForStatement forStatement, Void unused ) {
		return null;
	}

	@Override
	public Void visit( ForEachSubNodeStatement forEachSubNodeStatement, Void unused ) {
		return null;
	}

	@Override
	public Void visit( ForEachArrayItemStatement forEachArrayItemStatement, Void unused ) {
		return null;
	}

	@Override
	public Void visit( SpawnStatement spawnStatement, Void unused ) {
		return null;
	}

	@Override
	public Void visit( IsTypeExpressionNode isTypeExpressionNode, Void unused ) {
		return null;
	}

	@Override
	public Void visit( InstanceOfExpressionNode instanceOfExpressionNode, Void unused ) {
		return null;
	}

	@Override
	public Void visit( TypeCastExpressionNode typeCastExpressionNode, Void unused ) {
		return null;
	}

	@Override
	public Void visit( SynchronizedStatement synchronizedStatement, Void unused ) {
		return null;
	}

	@Override
	public Void visit( CurrentHandlerStatement currentHandlerStatement, Void unused ) {
		return null;
	}

	@Override
	public Void visit( EmbeddedServiceNode embeddedServiceNode, Void unused ) {
		return null;
	}

	@Override
	public Void visit( InstallFixedVariableExpressionNode installFixedVariableExpressionNode, Void unused ) {
		return null;
	}

	@Override
	public Void visit( VariablePathNode variablePathNode, Void unused ) {
		return null;
	}

	@Override
	public Void visit( TypeInlineDefinition typeInlineDefinition, Void unused ) {
		visitedTypes.add( typeInlineDefinition.name() );
		BasicTypeDefinition basicTypeDefinition = typeInlineDefinition.basicType();
		Set< Map.Entry< String, TypeDefinition > > subNodes = typeInlineDefinition.subTypes();

		CompilationUnit compilationUnit = new CompilationUnit();
		compilationUnit.setPackageDeclaration( packageName );

		compilationUnit.addImport( "jsdt.core.types.BasicType" );
		compilationUnit.addImport( "jolie.runtime.Value" );

		String javaNativeType = jolieToJavaType( basicTypeDefinition.nativeType() );

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
		if ( !basicTypeDefinition.nativeType().equals( NativeType.VOID ) ) {
			parseReturnParameters.add(
							jolieToGetValueOptional( basicTypeDefinition.nativeType() ).isEmpty() ? "value" : "value." + jolieToGetValueOptional( basicTypeDefinition.nativeType() ).get() + "()" );
		}

		parseMethod.setType( new ClassOrInterfaceType().setName( getLineage() ) );
		BlockStmt parseBody = parseMethod.createBody();
		IfStmt parseIfStm = new IfStmt();
		parseBody.addStatement( parseIfStm );
		parseIfStm.setCondition( new ExpressionStmt()
						.setExpression( "value != null"
										+ ( jolieToIsValue( basicTypeDefinition.nativeType() ).isEmpty() ? "" : " && value." + jolieToIsValue( basicTypeDefinition.nativeType() ).get() + "()" ) )
						.getExpression() );
		BlockStmt ifBranch = new BlockStmt();
		parseIfStm.setElseStmt( new BlockStmt().addStatement( "return null;" ) );
		parseIfStm.setThenStmt( ifBranch );

		if ( !basicTypeDefinition.nativeType().equals( NativeType.VOID ) ) {
			constructorDeclaration.addParameter( jolieToJavaType( basicTypeDefinition.nativeType() ), "root" );
			constructorDeclarationBody.addStatement( "super( root );" );
		} else {
			constructorDeclarationBody.addStatement( "super( null );" );
		}
		if ( subNodes != null && !subNodes.isEmpty() ) {

			subNodes.forEach( nodeEntry -> {
				String nodeName = nodeEntry.getKey();
				TypeDefinition node = nodeEntry.getValue();
				lineage.push( nodeName );

				visit( node );

				Cardinalities cardinalityClass = getCardinalityClass( node.cardinality() );
				compilationUnit.addImport( "jsdt.core.cardinality." + cardinalityClass );
				if ( cardinalityClass.equals( Cardinalities.Multi ) ) {
					compilationUnit.addImport( "java.util.stream.Collectors" );
				}
				String fieldTypeName = node instanceof TypeDefinitionLink ? ( ( TypeDefinitionLink ) node ).linkedTypeName() : getLineage();
				FieldDeclaration field = theClass.addField(
								cardinalityClass + "<" + fieldTypeName + ">",
								nodeName,
								Modifier.Keyword.PRIVATE, Modifier.Keyword.FINAL
				);
				constructorDeclaration.addParameter( field.getCommonType(), nodeName );
				constructorDeclarationBody.addStatement( "this." + nodeName + "=" + nodeName + ";" );
				field.createGetter().setName( nodeName );
				StringJoiner s = new StringJoiner( " " );
				if ( cardinalityClass.equals( Cardinalities.Multi ) ) {
					s.add( cardinalityClass + "<" + fieldTypeName + ">" )
									.add( nodeName )
									.add( "=" )
									.add( cardinalityClass + ".of( value.getChildren(" )
									.add( "\"" + nodeName + "\"" )
									.add( ").stream().map(" )
									.add( fieldTypeName + "::parse" )
									.add( ").collect( Collectors.toList() ) );" );
				} else {
					s.add( cardinalityClass + "<" + fieldTypeName + ">" )
									.add( nodeName )
									.add( "=" )
									.add( cardinalityClass + ".of(" )
									.add( fieldTypeName + ".parse(" )
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
		return null;
	}

	@Override
	public Void visit( TypeDefinitionLink typeDefinitionLink, Void unused ) {
		if ( !visitedTypes.contains( typeDefinitionLink.linkedType().name() ) ) {
			visitedTypes.add( typeDefinitionLink.linkedType().name() );
			this.compilationUnits.addAll(
							JSDTVisitor.generateTypeClasses( typeDefinitionLink.linkedType(), packageName )
			);
		}
		return null;
	}

	@Override
	public Void visit( InterfaceDefinition interfaceDefinition, Void unused ) {
		CompilationUnit compilationUnit = new CompilationUnit();
		compilationUnit.addImport( "jolie.runtime.JavaService" );
		compilationUnit.addImport( "jolie.runtime.Value" );
		compilationUnit.setPackageDeclaration( packageName );
		ClassOrInterfaceDeclaration theClass = compilationUnit.addClass( interfaceDefinition.name() + "Service" );
		theClass.setModifier( Modifier.Keyword.PUBLIC, true );
		theClass.addExtendedType( "JavaService" );
		interfaceDefinition.operationsMap().forEach( ( name, operation ) -> {
			MethodDeclaration methodDeclaration = theClass.addMethod( name );
			// we do not set the type of the method, since, if it is not set, it defaults to void
			methodDeclaration.setModifiers( Modifier.Keyword.PUBLIC );
			methodDeclaration.addParameter( "Value", "value" );
			BlockStmt methodBody = methodDeclaration.createBody();
			TypeDefinition requestType = operation instanceof OneWayOperationDeclaration ?
							( ( OneWayOperationDeclaration ) operation ).requestType()
							: ( ( RequestResponseOperationDeclaration ) operation ).requestType();
			switch ( requestType.name() ) {
				case "void":
					break;
				case "bool":
					methodBody.addStatement( "boolean request = value." + jolieToGetValue( requestType.name() ) + "();" );
					break;
				case "int":
					methodBody.addStatement( "int request = value." + jolieToGetValue( requestType.name() ) + "();" );
					break;
				case "double":
					methodBody.addStatement( "double request = value." + jolieToGetValue( requestType.name() ) + "();" );
					break;
				case "long":
					methodBody.addStatement( "long request = value." + jolieToGetValue( requestType.name() ) + "();" );
					break;
				case "string":
					methodBody.addStatement( "String request = value." + jolieToGetValue( requestType.name() ) + "();" );
					break;
				case "byte":
					methodBody.addStatement( "ByteArray request = value." + jolieToGetValue( requestType.name() ) + "();" );
					break;
				default:
					collectedInterfaceTypes.add( requestType );
					methodBody.addStatement( requestType.name() + " request = " + requestType.name() + ".parse( value );" );
			}
			if ( operation instanceof RequestResponseOperationDeclaration ) {
				compilationUnit.addImport( "jolie.runtime.embedding.RequestResponse" );
				methodDeclaration.addAnnotation( "RequestResponse" );
				TypeDefinition responseType = ( ( RequestResponseOperationDeclaration ) operation ).responseType();
				switch ( responseType.name() ) {
					case "void": case "bool": case "int": case "double": case "long": case "string": case "byte":
						break;
					default:
						collectedInterfaceTypes.add( responseType );
				}
				methodDeclaration.setType( "Value" );
			}
		} );
		compilationUnits.add( compilationUnit );
		return null;
	}

	@Override
	public Void visit( DocumentationComment documentationComment, Void unused ) {
		return null;
	}

	@Override
	public Void visit( FreshValueExpressionNode freshValueExpressionNode, Void unused ) {
		return null;
	}

	@Override
	public Void visit( CourierDefinitionNode courierDefinitionNode, Void unused ) {
		return null;
	}

	@Override
	public Void visit( CourierChoiceStatement courierChoiceStatement, Void unused ) {
		return null;
	}

	@Override
	public Void visit( NotificationForwardStatement notificationForwardStatement, Void unused ) {
		return null;
	}

	@Override
	public Void visit( SolicitResponseForwardStatement solicitResponseForwardStatement, Void unused ) {
		return null;
	}

	@Override
	public Void visit( InterfaceExtenderDefinition interfaceExtenderDefinition, Void unused ) {
		return null;
	}

	@Override
	public Void visit( InlineTreeExpressionNode inlineTreeExpressionNode, Void unused ) {
		return null;
	}

	@Override
	public Void visit( VoidExpressionNode voidExpressionNode, Void unused ) {
		return null;
	}

	@Override
	public Void visit( ProvideUntilStatement provideUntilStatement, Void unused ) {
		return null;
	}

	@Override
	public Void visit( TypeChoiceDefinition typeChoiceDefinition, Void unused ) {
		visitedTypes.add( typeChoiceDefinition.name() );
		CompilationUnit compilationUnit = new CompilationUnit();
		compilationUnit.setPackageDeclaration( packageName );

		compilationUnit.addImport( "jsdt.core.types.ChoiceType" );
		compilationUnit.addImport( "jolie.runtime.Value" );

		String leftClassName = typeChoiceDefinition.left() instanceof TypeDefinitionLink ?
						( ( TypeDefinitionLink ) typeChoiceDefinition.left() ).linkedType().name()
						: getLineage() + "_1";
		String rightClassName = typeChoiceDefinition.right() instanceof TypeDefinitionLink ?
						( ( TypeDefinitionLink ) typeChoiceDefinition.right() ).linkedType().name()
						: getLineage() + "_2";

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
		visit( typeChoiceDefinition.left() );
		lineage.pop();

		lineage.push( "2" );
		visit( typeChoiceDefinition.right() );
		lineage.pop();

		compilationUnits.add( compilationUnit );
		return null;
	}

	@Override
	public Void visit( ImportStatement importStatement, Void unused ) {
		return null;
	}

	@Override
	public Void visit( ServiceNode serviceNode, Void unused ) {
		return null;
	}

	@Override
	public Void visit( EmbedServiceNode embedServiceNode, Void unused ) {
		return null;
	}
}