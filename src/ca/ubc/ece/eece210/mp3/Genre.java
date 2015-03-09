package ca.ubc.ece.eece210.mp3;

import java.util.List;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

/**
 * Represents a genre (or collection of albums/genres).
 * 
 * @author Sathish Gopalakrishnan
 * 
 */
public final class Genre extends Element {

	// Representation invariant:
	// title is not null and not an empty String.

	private static final String openTag = "<genre>";
	private static final String closeTag = "</genre>";
	private static final String openNameTag = "<name>";
	private static final String closeNameTag = "</name>";

	// The Genre name
	String title;

	/**
	 * Creates a new genre with the given name.
	 * 
	 * @param name
	 *            the name of the genre.
	 */
	public Genre(String name) {
		if (name.equals("")) {
			throw new IllegalArgumentException(
					"The Genre title cannot be an empty string.");
		}
		title = name;
	}

	/**
	 * Returns the genre name.
	 * 
	 * @return genre name.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Restores a genre from its given string representation.
	 * 
	 * @param stringRepresentation
	 */
	public static Genre restoreCollection(String stringRepresentation) {
		CharStream stream = new ANTLRInputStream(stringRepresentation);
		CatalogueLexer lexer = new CatalogueLexer(stream);
		TokenStream tokens = new CommonTokenStream(lexer);

		CatalogueParser parser = new CatalogueParser(tokens);
		ParseTree tree = parser.root();
		((RuleContext) tree).inspect(parser);

		ParseTreeWalker walker = new ParseTreeWalker();
		CatalogueListenerCatalogueCreator listener = new CatalogueListenerCatalogueCreator();
		walker.walk(listener, tree);
		Catalogue c = listener.getCatalogue();

		if ((c.size() == 1) && (c.get(0).hasChildren())) {
			// there was exactly one element in the String and it was a Genre
			return (Genre) c.get(0);
		} else {
			throw new IllegalArgumentException(
					"The input string is not a valid representation of a genre.");
		}
	}

	/**
	 * Returns the string representation of a genre. The string representation
	 * is: <album><br />
	 * album title <br />
	 * <b><performer></b> the performer's name <b></performer></b><br />
	 * <b><song></b> song 1 <b></song></b><br />
	 * <b><song></b> song 2 <b></song></b><br />
	 * ... <b></album></b><br />
	 * 
	 * @return the string representation.
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(openTag + "\n");
		sb.append(openNameTag + title + closeNameTag + "\n");
		List<Element> listOfChildren = getChildren();
		for (Element e : listOfChildren) {
			// System.out.println(e.toString());
			sb.append(e.toString());
		}
		sb.append(closeTag + "\n");
		return sb.toString();
	}

	/**
	 * Adds the given album or genre to this genre
	 * 
	 * @param b
	 *            the element to be added to the collection.
	 */
	public void addToGenre(Element b) {
		// Delegate to proper add method
		if (b instanceof Album) {
			addToGenre((Album)b);
		} else if (b instanceof Genre) {
			addToGenre((Genre)b);
		} else {
			addChild(b);
		}
	}
	
	public void addToGenre(Album a) {
		a.setGenre(this);
	}
	
	public void addToGenre(Genre subgenre) {
		addChild(subgenre);
	}

	/**
	 * Returns true, since a genre can contain other albums and/or genres.
	 */
	@Override
	public boolean hasChildren() {
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Genre other = (Genre) obj;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
}