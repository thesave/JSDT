// Generated from JolieTypes.g4 by ANTLR 4.9.2
package jsdt.grammar;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link JolieTypesParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface JolieTypesVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link JolieTypesParser#typesOrInterfaces}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypesOrInterfaces(JolieTypesParser.TypesOrInterfacesContext ctx);
	/**
	 * Visit a parse tree produced by {@link JolieTypesParser#interfaceDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInterfaceDeclaration(JolieTypesParser.InterfaceDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link JolieTypesParser#oneWays}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOneWays(JolieTypesParser.OneWaysContext ctx);
	/**
	 * Visit a parse tree produced by {@link JolieTypesParser#requestResponses}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRequestResponses(JolieTypesParser.RequestResponsesContext ctx);
	/**
	 * Visit a parse tree produced by {@link JolieTypesParser#throwTypeList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitThrowTypeList(JolieTypesParser.ThrowTypeListContext ctx);
	/**
	 * Visit a parse tree produced by {@link JolieTypesParser#identifierOrNativeType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifierOrNativeType(JolieTypesParser.IdentifierOrNativeTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link JolieTypesParser#typeDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeDeclaration(JolieTypesParser.TypeDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link JolieTypesParser#nativeType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNativeType(JolieTypesParser.NativeTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link JolieTypesParser#nodes}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNodes(JolieTypesParser.NodesContext ctx);
	/**
	 * Visit a parse tree produced by {@link JolieTypesParser#subNodes}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubNodes(JolieTypesParser.SubNodesContext ctx);
	/**
	 * Visit a parse tree produced by {@link JolieTypesParser#typeChoice}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeChoice(JolieTypesParser.TypeChoiceContext ctx);
	/**
	 * Visit a parse tree produced by {@link JolieTypesParser#cardinality}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCardinality(JolieTypesParser.CardinalityContext ctx);
}