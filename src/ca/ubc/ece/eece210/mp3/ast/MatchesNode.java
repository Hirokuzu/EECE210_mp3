package ca.ubc.ece.eece210.mp3.ast;

import java.util.HashSet;
import java.util.Set;

import ca.ubc.ece.eece210.mp3.Album;
import ca.ubc.ece.eece210.mp3.Element;
import ca.ubc.ece.eece210.mp3.Catalogue;

public class MatchesNode extends ASTNode {

    public MatchesNode(Token token) {
    	super(token);
    }

    @Override
    public Set<Element> interpret(Catalogue argument) {
    	// TODO Auto-generated method stub
    	Set<Element> albumsByTitle = new HashSet<Element>();
		if(argument != null) {
			for(int i = 0; i < argument.size(); i++) {
				albumsByTitle.addAll(findAlbumsByTitle(argument.get(i)));
			}
			return albumsByTitle;
		}
		else
			return albumsByTitle;
    }
    
    public Set<Element> findAlbumsByTitle(Element element) {
    	// TODO implement
    	Set<Element> albumsByTitle = new HashSet<Element>();
		if( element == null) { // nothing actually there
			return albumsByTitle;
		}
		
		if( element.hasChildren() ) {
			for(Element e: element.getChildren() ) {
				albumsByTitle.addAll(findAlbumsByTitle(e));
			}
		} else { // no children, must be an album (by spec of hasChildren() )
			if( ((Album) element).getTitle().matches( arguments ) ) {
				albumsByTitle.add(element);
			}
		}
		return albumsByTitle;
    }
}
