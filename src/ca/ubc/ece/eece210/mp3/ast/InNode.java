package ca.ubc.ece.eece210.mp3.ast;

import java.util.HashSet;
import java.util.Set;

import ca.ubc.ece.eece210.mp3.Album;
import ca.ubc.ece.eece210.mp3.Element;
import ca.ubc.ece.eece210.mp3.Catalogue;
import ca.ubc.ece.eece210.mp3.Genre;

public class InNode extends ASTNode {

	public InNode(Token token) {
		super(token);
	}

	@Override
	public Set<Element> interpret(Catalogue argument) {
		// TODO Auto-generated method stub
		Set<Element> albumsByGenre = new HashSet<Element>();
		if(argument != null) {
			for(int i = 0; i < argument.size(); i++) {
				albumsByGenre.addAll(findAlbumsByGenre(argument.get(i)));
			}
			return albumsByGenre;
		}
		else
			return albumsByGenre;
	}

	public Set<Element> findAlbumsByGenre(Element element) {
		Set<Element> albumsByGenre = new HashSet<Element>();
		if( element == null) { // nothing actually there
			return albumsByGenre;
		}
		
		if( element.hasChildren() ) { // is a genre
			if(( (Genre) element ).getTitle().equals( arguments ) ) { // this genre is the one we're looking for
				for(Element e: element.getChildren() ) { // add ALL of the elements to the Set
					if( e.hasChildren() ) {
						albumsByGenre.addAll(e.getChildren());
					} else { // Album. just add it
						albumsByGenre.add(e);
					}
				}
			} else { // not the genre we're looking for
				// we must go deeper
				for( Element e: element.getChildren() ) {
					albumsByGenre.addAll(findAlbumsByGenre(e));
				}
			}
		} else {
			// this is an album. do nothing
		}
		return albumsByGenre;
	}
}
