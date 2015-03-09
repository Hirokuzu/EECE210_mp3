package ca.ubc.ece.eece210.mp3.ast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import ca.ubc.ece.eece210.mp3.Album;
import ca.ubc.ece.eece210.mp3.Catalogue;
import ca.ubc.ece.eece210.mp3.Element;

public class ByNode extends ASTNode {
	
	/**
	 * Create a new ASTNode given a parser token. An ASTNode can have two
	 * children because we deal with binary operations such as &&, ||
	 * 
	 * @param token
	 *            to create the ASTNode with
	 */
	public ByNode(Token token) {
		super(token);
	}

	@Override
	public Set<Element> interpret(Catalogue argument) {
		// TODO Auto-generated method stub
		// traverse the catalogue and go in depth for genres in genres
		Set<Element> albumsByArtist = new HashSet<Element>();
		if(argument != null) {
			for(int i = 0; i < argument.size(); i++) {
				albumsByArtist.addAll(findAlbumsByArtist(argument.get(i)));
			}
			return albumsByArtist;
		}
		else
			return albumsByArtist;
	}	
	
	/**
	 * Finds all albums which are by the specified artist
	 * 
	 * @param element
	 * 		an Element
	 * @return a set of elements which match the artist queried
	 */
	public Set<Element> findAlbumsByArtist(Element element) {
		Set<Element> albumsByArtist = new HashSet<Element>();
		if( element == null) { // nothing actually there
			return albumsByArtist;
		}
		
		if( element.hasChildren() ) {
			for(Element e: element.getChildren() ) {
				albumsByArtist.addAll(findAlbumsByArtist(e));
			}
		} else { // no children, must be an album (by spec of hasChildren() )
			if( ((Album) element).getPerformer().equals( arguments ) ) {
				albumsByArtist.add(element);
			}
		}
		return albumsByArtist;
	}
	
}
