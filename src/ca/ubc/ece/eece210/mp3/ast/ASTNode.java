package ca.ubc.ece.eece210.mp3.ast;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import ca.ubc.ece.eece210.mp3.Element;
import ca.ubc.ece.eece210.mp3.Catalogue;

public abstract class ASTNode {
	protected List<ASTNode> children;
	protected Token token;

	// "by(...)", "matches(...)" and "in(...)" and matches all take arguments
	// e.g. matches("Title"). The String "Title" must be stored in this
	// arguments variable.
	protected String arguments = null;

	/**
	 * Create a new ASTNode given a parser token. An ASTNode can have two
	 * children because we deal with binary operations such as &&, ||
	 * 
	 * @param token
	 *            to create the ASTNode with
	 */
	public ASTNode(Token token) {
		children = new ArrayList<ASTNode>(2);
		this.token = token;
	}

	/**
	 * Set the arguments for an ASTNode. The argument is what is needed to
	 * evaluate a query at a leaf node.
	 * 
	 * @param arguments
	 *            the values to work with at the leaf node.
	 */
	public void setArguments(String arguments) {
		this.arguments = arguments;
	}

	/**
	 * Add children to an ASTNode that represents a binary (possibly unary)
	 * operation
	 * 
	 * @param node
	 *            a child node to add to this ASTNode.
	 */
	public void addChild(ASTNode node) {
		children.add(node);
	}

	/**
	 * Obtain the text associated with this ASTNode. This would be the text form
	 * of the operator associated with this node.
	 * 
	 * @return a String representation of the token associated with this
	 *         ASTNode.
	 */
	public String getText() {
		return token.getPayload();
	}

	/**
	 * An abstract method to evaluate this ASTNode.
	 * 
	 * @param catalogue
	 *            the Catalogue object to run a query snippet over.
	 * @return a Set of elements that matches the query snippet.
	 */
	public abstract Set<Element> interpret(Catalogue catalogue);

	/**
	 * Convert this ASTNode to a String. Good for testing.
	 * 
	 */
	@Override
	public String toString() {
		if (children == null || children.size() == 0) {
			return this.getText();
		}
		StringBuffer buf = new StringBuffer();
		if (children != null) {
			buf.append("(");
			buf.append(this.getText());
			buf.append(' ');
		}
		for (int i = 0; children != null && i < children.size(); i++) {
			ASTNode t = children.get(i);
			if (i > 0) {
				buf.append(' ');
			}
			buf.append(t.toString());
		}
		if (children != null) {
			buf.append(")");
		}
		return buf.toString();
	}
}
