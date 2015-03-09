package ca.ubc.ece.eece210.mp3.ast;

import java.util.HashSet;
import java.util.Set;

import ca.ubc.ece.eece210.mp3.Element;
import ca.ubc.ece.eece210.mp3.Catalogue;

public class OrNode extends ASTNode {

    public OrNode(Token token) {
    	super(token);
    }

    @Override
    public Set<Element> interpret(Catalogue argument) {
    	// TODO Auto-generated method stub
    	Set<Element> union;
    	if( argument == null) {
    		return new HashSet<Element>();
    	}
		
		if( children != null && !children.isEmpty( ) ) {
			int i = 0;
			union = children.get(i).interpret(argument);
			for (i = 1; i < children.size(); i++ ) {
				union.addAll(children.get(i).interpret(argument));
			}
		} else {
			union = new HashSet<Element>();
		}
		return union;
    }

}
