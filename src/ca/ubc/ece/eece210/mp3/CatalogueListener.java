package ca.ubc.ece.eece210.mp3;

// Generated from Catalogue.g4 by ANTLR 4.4
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CatalogueParser}.
 */
public interface CatalogueListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CatalogueParser#genre}.
	 * @param ctx the parse tree
	 */
	void enterGenre(@NotNull CatalogueParser.GenreContext ctx);
	/**
	 * Exit a parse tree produced by {@link CatalogueParser#genre}.
	 * @param ctx the parse tree
	 */
	void exitGenre(@NotNull CatalogueParser.GenreContext ctx);
	/**
	 * Enter a parse tree produced by {@link CatalogueParser#song}.
	 * @param ctx the parse tree
	 */
	void enterSong(@NotNull CatalogueParser.SongContext ctx);
	/**
	 * Exit a parse tree produced by {@link CatalogueParser#song}.
	 * @param ctx the parse tree
	 */
	void exitSong(@NotNull CatalogueParser.SongContext ctx);
	/**
	 * Enter a parse tree produced by {@link CatalogueParser#catalogue}.
	 * @param ctx the parse tree
	 */
	void enterCatalogue(@NotNull CatalogueParser.CatalogueContext ctx);
	/**
	 * Exit a parse tree produced by {@link CatalogueParser#catalogue}.
	 * @param ctx the parse tree
	 */
	void exitCatalogue(@NotNull CatalogueParser.CatalogueContext ctx);
	/**
	 * Enter a parse tree produced by {@link CatalogueParser#root}.
	 * @param ctx the parse tree
	 */
	void enterRoot(@NotNull CatalogueParser.RootContext ctx);
	/**
	 * Exit a parse tree produced by {@link CatalogueParser#root}.
	 * @param ctx the parse tree
	 */
	void exitRoot(@NotNull CatalogueParser.RootContext ctx);
	/**
	 * Enter a parse tree produced by {@link CatalogueParser#album}.
	 * @param ctx the parse tree
	 */
	void enterAlbum(@NotNull CatalogueParser.AlbumContext ctx);
	/**
	 * Exit a parse tree produced by {@link CatalogueParser#album}.
	 * @param ctx the parse tree
	 */
	void exitAlbum(@NotNull CatalogueParser.AlbumContext ctx);
	/**
	 * Enter a parse tree produced by {@link CatalogueParser#name}.
	 * @param ctx the parse tree
	 */
	void enterName(@NotNull CatalogueParser.NameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CatalogueParser#name}.
	 * @param ctx the parse tree
	 */
	void exitName(@NotNull CatalogueParser.NameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CatalogueParser#performer}.
	 * @param ctx the parse tree
	 */
	void enterPerformer(@NotNull CatalogueParser.PerformerContext ctx);
	/**
	 * Exit a parse tree produced by {@link CatalogueParser#performer}.
	 * @param ctx the parse tree
	 */
	void exitPerformer(@NotNull CatalogueParser.PerformerContext ctx);
}