package ca.ubc.ece.eece210.mp3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.List;	
import java.util.ArrayList;
import java.util.Scanner;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import ca.ubc.ece.eece210.mp3.ast.ASTNode;
import ca.ubc.ece.eece210.mp3.ast.QueryParser;
import ca.ubc.ece.eece210.mp3.ast.QueryTokenizer;
import ca.ubc.ece.eece210.mp3.ast.Token;

/**
 * Container class for all the albums and genres. Its main responsibility is to
 * save and restore the collection from a file.
 * 
 * @author Sathish Gopalakrishnan
 * 
 */
public final class Catalogue {

	private List<Element> contents;

	/**
	 * Builds a new, empty catalogue.
	 */
	public Catalogue() {
		contents = new ArrayList<Element>();
	}

	public int size() {
		return contents.size();
	}
	
	public Element get(int index) {
		return contents.get(index);
	}
	
	public void add(Element e) {
		contents.add(e);
	}

	/**
	 * Builds a new catalogue and restores its contents from the given file.
	 * 
	 * @param fileName
	 *            the file from where to restore the library.
	 */
	public Catalogue(String fileName) {
		// TODO implement
		// HINT:  look at Genre.restoreCollection(...)
		File fileToRead = new File(fileName);
		Scanner fileScanner = null;
		try {
			fileScanner = new Scanner(fileToRead);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String stringRepresentation = "";
		while( fileScanner.hasNextLine() ) {
			stringRepresentation += fileScanner.nextLine();
		}
		fileScanner.close();
		
		CharStream stream = new ANTLRInputStream(stringRepresentation);
		CatalogueLexer lexer = new CatalogueLexer(stream);
		TokenStream tokens = new CommonTokenStream(lexer);

		CatalogueParser parser = new CatalogueParser(tokens);
		ParseTree tree = parser.root();
		((RuleContext) tree).inspect(parser);

		ParseTreeWalker walker = new ParseTreeWalker();
		CatalogueListenerCatalogueCreator listener = new CatalogueListenerCatalogueCreator();
		walker.walk(listener, tree);
		contents = listener.getCatalogue().contents;
	}

	/**
	 * Saved the contents of the catalogue to the given file.
	 * 
	 * @param fileName
	 *            the file where to save the library
	 */
	public void saveCatalogueToFile(String fileName) {
		// TODO implement
		PrintWriter fileToWrite = null;
		try {
			fileToWrite = new PrintWriter(fileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		for(Element e: contents) {
			fileToWrite.print(e.toString());
		}
		fileToWrite.close();
	}
	
	public List<Album> query(String queryString) {
		// TODO implement
		List<Token> tokens = QueryTokenizer.tokenizeInput(queryString);
		QueryParser parser = new QueryParser(tokens);
		ASTNode root = parser.getRoot();
		List<Album> albums = new ArrayList<Album>();
		for(Element e: root.interpret(this)) {
			albums.add((Album) e);
		}
		return albums;
	}

}