package ca.ubc.ece.eece210.mp3.grade;

import static org.junit.Assert.*;
import ca.ubc.ece.eece210.mp3.ast.ASTNode;
import ca.ubc.ece.eece210.mp3.ast.QueryParser;
import ca.ubc.ece.eece210.mp3.ast.QueryTokenizer;
import ca.ubc.ece.eece210.mp3.ast.Token;
import java.util.List;
import org.junit.Test;

import org.junit.Test;

/**
 * This is a simple class that shows how the parser is supposed to work. Feel
 * free to play with it and see how ASTs are generated.
 * 
 * @author Sathish Gopalakrishnan
 * 
 */
public class QueryParserTest {

	@Test
	public void testIn() {
		List<Token> tokens = QueryTokenizer.tokenizeInput("in (\"Jazz\")");
		QueryParser parser = new QueryParser(tokens);
		ASTNode rootNode = parser.getRoot();
		assertEquals("in", rootNode.toString());
	}
	
	@Test
	public void testBy() {
		List<Token> tokens = QueryTokenizer
				.tokenizeInput("by (\"Herbie Hancock\")");
		QueryParser parser = new QueryParser(tokens);
		ASTNode root = parser.getRoot();
		assertEquals("by", root.toString());
	}

	@Test
	public void testInAnd() {
		List<Token> tokens = QueryTokenizer
				.tokenizeInput("matches (\".*Ripley.*\") && in (\"Jazz\")");
		QueryParser parser = new QueryParser(tokens);
		ASTNode root = parser.getRoot();
		assertEquals("(&& matches in)", root.toString());
	}
	
	@Test
	public void testAndAnd() {
		List<Token> tokens = QueryTokenizer
				.tokenizeInput("in (\"Jazz\") && matches (\".*Ripley.*\") && matches (\".*Believe.*\")");
		QueryParser parser = new QueryParser(tokens);
		ASTNode root = parser.getRoot();
		assertEquals("(&& (&& in matches) matches)", root.toString());
	}

	@Test
	public void testOr() {
		List<Token> tokens = QueryTokenizer
				.tokenizeInput("in (\"Jazz\") || matches (\".*Ripley.*\")");
		QueryParser parser = new QueryParser(tokens);
		ASTNode root = parser.getRoot();
		assertEquals("(|| in matches)", root.toString());
	}
	
	
	@Test
	public void testAndOrAnd() {
		List<Token> tokens = QueryTokenizer
				.tokenizeInput("in (\"Jazz\") && matches (\".*Ripley.*\") || in (\"Jazz\") && matches (\".*Angel.*\")");
		QueryParser parser = new QueryParser(tokens);
		ASTNode root = parser.getRoot();
		assertEquals("(|| (&& in matches) (&& in matches))", root.toString());
	}
	
}