import grammar.JolieTypesParser;
import grammar.JolieTypesVisitor;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.LinkedList;
import java.util.List;
import java.util.StringJoiner;

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

public class JSDTVisitor implements JolieTypesVisitor< String > {

	private final List< String > classes;

	public JSDTVisitor() {
		this.classes = new LinkedList<>();
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

	private String getSubnodesDeclarations( String lineage, List< JolieTypesParser.SubNodesContext > ctx ){
		StringJoiner s = new StringJoiner( " " );
		System.out.println( "Found " + ctx.size() + " subnodes" );
		ctx.forEach( c -> {
			s.add( "private" )
				.add( "final" );
			if( c.cardinality() == null || c.cardinality().Single() != null ){
				s.add( "Single<" );
			} else if( c.cardinality().MaybeSingle() != null ){
				s.add( "MaybeSingle<" );
			} else {
				s.add( "Multi<" );
			}
			s.add( lineage + "_" + c.Identifier().getText() )
				.add( ">" )
				.add( c.Identifier().getText() + ";\n" );
		} );
		return s.toString();
	}

	@Override
	public String visitTypeDeclaration( JolieTypesParser.TypeDeclarationContext ctx ) {
		// check if it is a type choice
		// if NO
		if( ctx.typeChoice() == null ){
			StringJoiner s = new StringJoiner( " " );
			s.add( "public" ).add( "class" ).add( ctx.Identifier().getText() )
							.add( "extends" )
							.add( "BasicType<" ).add( jolieToJavaType( ctx.nativeType() ) ).add( ">" )
							.add( "{\n" );
							if( ctx.nodes() != null ){
								s.add( getSubnodesDeclarations( ctx.Identifier().getText(), ctx.nodes().subNodes() ) );
							}
							s.add( "\n}" );
			return s.toString();
		}
		// if YES
		// create a class that extends ChoiceType
		// then create the class for the two branches of the choice
		else {
			return null;
		}
	}

	@Override
	public String visitNativeType( JolieTypesParser.NativeTypeContext ctx ) {
		return null;
	}

	@Override
	public String visitNodes( JolieTypesParser.NodesContext ctx ) {
		return null;
	}

	@Override
	public String visitTypeChoice( JolieTypesParser.TypeChoiceContext ctx ) {
		return null;
	}

	@Override
	public String visitCardinality( JolieTypesParser.CardinalityContext ctx ) {
		return null;
	}

	@Override
	public String visitSubNodes( JolieTypesParser.SubNodesContext ctx ) {
		return null;
	}

	@Override
	public String visitNativeTypeOrIdentifier( JolieTypesParser.NativeTypeOrIdentifierContext ctx ) {
		return null;
	}

	@Override
	public String visit( ParseTree parseTree ) {
		return null;
	}

	@Override
	public String visitChildren( RuleNode ruleNode ) {
		return null;
	}

	@Override
	public String visitTerminal( TerminalNode terminalNode ) {
		return null;
	}

	@Override
	public String visitErrorNode( ErrorNode errorNode ) {
		return null;
	}
}
