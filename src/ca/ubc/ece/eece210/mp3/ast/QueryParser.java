package ca.ubc.ece.eece210.mp3.ast;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This is a hand-written recursive descent parser that will build an AST for
 * our simple query language.
 * 
 * To use this class, create a new instance of SimpleParser and call the
 * getRoot() method.
 * 
 * @author Sathish Gopalakrishnan
 * 
 */
public class QueryParser {
	private static final Token END_TOKEN = new Token(TokenType.END, "END");
	final List<Token> tokenStream;
	int currentPosition;
	ASTNode root;

	static Map<TokenType, Class<? extends ASTNode>> map = new HashMap<TokenType, Class<? extends ASTNode>>();

	static {
		map.put(TokenType.AND, AndNode.class);
		map.put(TokenType.OR, OrNode.class);
		map.put(TokenType.MATCHES, MatchesNode.class);
		map.put(TokenType.IN, InNode.class);
		map.put(TokenType.BY, ByNode.class);
	}

	/**
	 * Create a QueryParser from a list of tokens. This constructor only starts processing tokens. Further steps are needed before a tree can be obtained.
	 * 
	 * @param _tokenStream is a list of tokens to create the parse tree from
	 */
	public QueryParser(List<Token> _tokenStream) {
		tokenStream = _tokenStream;
		currentPosition = 0;
	}

	/**
	 * Obtain the root of the QueryParser tree using the tokens that are part of the QueryParser.
	 * 
	 * @return the root node of the parse tree
	 */
	public ASTNode getRoot() {
		/* TODO: change me */
		ASTNode ast = orExpr();
		return ast;
	}

	/**
	 * Start processing the tokens in the QueryParser assuming we are going to see an OR expression
	 * @return a reference to the OR node created
	 * @modifies the internal token stream by consuming the appropriate tokens
	 */ 
	public ASTNode orExpr() {
		/* TODO: implement me */
		ASTNode current;
		
		ASTNode leftTree = andExpr();
		current = leftTree;
		
		Token nextToken;
		
		do {
			nextToken = peek();
			
			if( nextToken.getType() == TokenType.OR ) {
				consume();
				ASTNode head = new OrNode(Token.getTokenInstance(nextToken.getPayload()));
				head.addChild(current);
				ASTNode rightTree = andExpr();
				head.addChild(rightTree);
				current = head;
			} else {
				return current;
			}
		} while(nextToken.getType() != TokenType.END);
		
		return current;
	}

	/**
	 * Start processing the tokens for the parse assuming we are going to see an AND expression
	 * @return a reference to the AND node created
	 * @modifies the internal token stream by consuming the appropriate tokens
	 */ 
	protected ASTNode andExpr() {
		ASTNode current;

		ASTNode leftTree = atom();
		current = leftTree;

		Token nextToken;

		do {
			nextToken = peek();

			if (nextToken.getType() == TokenType.AND) {
				consume();
				ASTNode head = new AndNode(Token.getTokenInstance(nextToken
						.getPayload()));
				head.addChild(current);
				ASTNode rightTree = atom();
				head.addChild(rightTree);
				current = head;

			} else {
				return current;
			}

		} while (nextToken.getType() != TokenType.END);

		return current;

	}

	/**
	 * Process the next token assuming it is an atom/terminal node
	 * @returb a reference to the leaf node created or return null if the end of the token list is reached.
	 * @modifies the internal token stream by consuming the appropriate tokens
	 */ 
	protected ASTNode atom() {
		Token nextToken = consume();

		if (nextToken.getType() == TokenType.END) {
			return null;
		}

		if (nextToken.getType() == TokenType.L_PARAN) {
			// Process compound expression
			ASTNode tree = orExpr();
			consume(); // remove RPAREN
			return tree;
		} else {
			return processLeaveNodes(nextToken);
		}

	}

	/**
	 * Process a leaf token and return a node for the parse tree
	 * @param a token from the token stream to process
	 * @return a leaf node for the parse tree using the given token
	 * @modifies the token stream by consuming the appropriate tokens
	 */ 
	@SuppressWarnings("rawtypes")
	private ASTNode processLeaveNodes(Token token) {
		Class<? extends ASTNode> astClass = map.get(token.getType());
		Class[] parameters = new Class[] { Token.class };
		try {
			Constructor cons = astClass.getConstructor(parameters);
			Object[] arguments = new Object[] { token };
			ASTNode ast = (ASTNode) cons.newInstance(arguments);

			consume(); // remove LPAREN
			Token argument = consume();
			ast.setArguments(sanitizeString(argument));
			consume(); // remove RPAREN

			return ast;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Sanitize the token by removing quotation marks
	 * @param argument is a token to sanitize
	 * @return a String with quotation marks removed
	 */ 
	// "Some string" -> Some string (quotes removed)
	private String sanitizeString(Token argument) {
		String payload = argument.getPayload();
		payload = payload.substring(1, payload.length() - 1);
		return payload;
	}

	/**
	 * Move ahead in the token steam
	 * @return next token if there is one, and return an end of stream marker otherwise
	 * @modifies tokenStream by moving ahead in the stream
	 */ 
	private Token consume() {
		if (currentPosition == tokenStream.size())
			return END_TOKEN;

		return tokenStream.get(currentPosition++);
	}

	/**
	 * Obtain the token at the current position in the token stream
	 * @return current token
	 */ 
	private Token peek() {
		if (currentPosition == tokenStream.size())
			return END_TOKEN;

		return tokenStream.get(currentPosition);
	}
}
