package ca.ubc.ece.eece210.mp3.ast;

import java.util.HashSet;
import java.util.Set;

import ca.ubc.ece.eece210.mp3.Element;
import ca.ubc.ece.eece210.mp3.Catalogue;

/**
 * 
 * @author Sathish Gopalakrishnan
 * 
 */

public class AndNode extends ASTNode {

	/**
	 * Create a new AndNode given a parser token
	 * 
	 * @param token
	 */
	public AndNode(Token token) {
		super(token);
	}

	/**
	 * Interpret/evaluate an ANDNode of a query over a given catalogue
	 */
	@Override
	public Set<Element> interpret(Catalogue catalogue) {
		// TODO Auto-generated method stub
		Set<Element> intersect;
		if( catalogue == null) {
    		return new HashSet<Element>();
    	}
		
		if( children != null && !children.isEmpty( ) ) {
			int i = 0;
			intersect = children.get(i).interpret(catalogue);
			for (i = 1; i < children.size(); i++ ) {
				intersect.retainAll(children.get(i).interpret(catalogue));
			}
		} else {
			intersect = new HashSet<Element>();
		}
		return intersect;
	}

}
