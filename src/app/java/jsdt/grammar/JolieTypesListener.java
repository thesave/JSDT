// Generated from JolieTypes.g4 by ANTLR 4.9.2
package jsdt.grammar;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link JolieTypesParser}.
 */
public interface JolieTypesListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link JolieTypesParser#typesOrInterfaces}.
	 * @param ctx the parse tree
	 */
	void enterTypesOrInterfaces(JolieTypesParser.TypesOrInterfacesContext ctx);
	/**
	 * Exit a parse tree produced by {@link JolieTypesParser#typesOrInterfaces}.
	 * @param ctx the parse tree
	 */
	void exitTypesOrInterfaces(JolieTypesParser.TypesOrInterfacesContext ctx);
	/**
	 * Enter a parse tree produced by {@link JolieTypesParser#interfaceDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterInterfaceDeclaration(JolieTypesParser.InterfaceDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JolieTypesParser#interfaceDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitInterfaceDeclaration(JolieTypesParser.InterfaceDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JolieTypesParser#oneWays}.
	 * @param ctx the parse tree
	 */
	void enterOneWays(JolieTypesParser.OneWaysContext ctx);
	/**
	 * Exit a parse tree produced by {@link JolieTypesParser#oneWays}.
	 * @param ctx the parse tree
	 */
	void exitOneWays(JolieTypesParser.OneWaysContext ctx);
	/**
	 * Enter a parse tree produced by {@link JolieTypesParser#requestResponses}.
	 * @param ctx the parse tree
	 */
	void enterRequestResponses(JolieTypesParser.RequestResponsesContext ctx);
	/**
	 * Exit a parse tree produced by {@link JolieTypesParser#requestResponses}.
	 * @param ctx the parse tree
	 */
	void exitRequestResponses(JolieTypesParser.RequestResponsesContext ctx);
	/**
	 * Enter a parse tree produced by {@link JolieTypesParser#throwTypeList}.
	 * @param ctx the parse tree
	 */
	void enterThrowTypeList(JolieTypesParser.ThrowTypeListContext ctx);
	/**
	 * Exit a parse tree produced by {@link JolieTypesParser#throwTypeList}.
	 * @param ctx the parse tree
	 */
	void exitThrowTypeList(JolieTypesParser.ThrowTypeListContext ctx);
	/**
	 * Enter a parse tree produced by {@link JolieTypesParser#identifierOrNativeType}.
	 * @param ctx the parse tree
	 */
	void enterIdentifierOrNativeType(JolieTypesParser.IdentifierOrNativeTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link JolieTypesParser#identifierOrNativeType}.
	 * @param ctx the parse tree
	 */
	void exitIdentifierOrNativeType(JolieTypesParser.IdentifierOrNativeTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link JolieTypesParser#typeDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterTypeDeclaration(JolieTypesParser.TypeDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JolieTypesParser#typeDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitTypeDeclaration(JolieTypesParser.TypeDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JolieTypesParser#nativeType}.
	 * @param ctx the parse tree
	 */
	void enterNativeType(JolieTypesParser.NativeTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link JolieTypesParser#nativeType}.
	 * @param ctx the parse tree
	 */
	void exitNativeType(JolieTypesParser.NativeTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link JolieTypesParser#nodes}.
	 * @param ctx the parse tree
	 */
	void enterNodes(JolieTypesParser.NodesContext ctx);
	/**
	 * Exit a parse tree produced by {@link JolieTypesParser#nodes}.
	 * @param ctx the parse tree
	 */
	void exitNodes(JolieTypesParser.NodesContext ctx);
	/**
	 * Enter a parse tree produced by {@link JolieTypesParser#subNodes}.
	 * @param ctx the parse tree
	 */
	void enterSubNodes(JolieTypesParser.SubNodesContext ctx);
	/**
	 * Exit a parse tree produced by {@link JolieTypesParser#subNodes}.
	 * @param ctx the parse tree
	 */
	void exitSubNodes(JolieTypesParser.SubNodesContext ctx);
	/**
	 * Enter a parse tree produced by {@link JolieTypesParser#typeChoice}.
	 * @param ctx the parse tree
	 */
	void enterTypeChoice(JolieTypesParser.TypeChoiceContext ctx);
	/**
	 * Exit a parse tree produced by {@link JolieTypesParser#typeChoice}.
	 * @param ctx the parse tree
	 */
	void exitTypeChoice(JolieTypesParser.TypeChoiceContext ctx);
	/**
	 * Enter a parse tree produced by {@link JolieTypesParser#cardinality}.
	 * @param ctx the parse tree
	 */
	void enterCardinality(JolieTypesParser.CardinalityContext ctx);
	/**
	 * Exit a parse tree produced by {@link JolieTypesParser#cardinality}.
	 * @param ctx the parse tree
	 */
	void exitCardinality(JolieTypesParser.CardinalityContext ctx);
}