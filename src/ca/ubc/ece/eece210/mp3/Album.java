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
 * 
 * @author Sathish Gopalakrishnan
 * 
 *         This class contains the information needed to represent an album in
 *         our application.
 * 
 */

public final class Album extends Element {

	// Representation invariant:
	// (1) title, performer, songList and genreName are not null.
	// (2) if parentGenre is set then Album is also a child of the appropriate
	// Genre object.

	private String title;
	private String performer;
	private List<String> songList;
	private Genre parentGenre;

	private static final String openTag = "<album>";
	private static final String closeTag = "</album>";
	private static final String openNameTag = "<name>";
	private static final String closeNameTag = "</name>";
	private static final String openPerformerTag = "<performer>";
	private static final String closePerformerTag = "</performer>";
	private static final String openSongTag = "<song>";
	private static final String closeSongTag = "</song>";

	/**
	 * Builds an album with the given title, performer and song list
	 * 
	 * @param title
	 *            the title of the album
	 * @param performer
	 *            the performer
	 * @param songlist
	 *            the list of songs in the album
	 */
	public Album(String title, String performer, List<String> songlist) {

		this.title = title;
		this.performer = performer;
		this.songList = songlist;
		this.parentGenre = null;  // "Unclassified";

	}

	/**
	 * Builds an album from the string representation of the object. It is used
	 * when restoring an album from a file.
	 * 
	 * @param stringRepresentation
	 *            the string representation
	 */
	public Album(String stringRepresentation) {

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

		if ((c.size() == 1) && (!c.get(0).hasChildren())) {
			// there was exactly one element in the String and it was an Album
			Album album = (Album) c.get(0);
			this.title = album.title;
			this.performer = album.performer;
			this.songList = album.songList;
			this.parentGenre = null;  // must be set by adding to a genre
		} else {
			throw new IllegalArgumentException(
					"The input string is not a valid representation of an album.");
		}
	}
	
	
	/**
	 * Remove album from supplied genre (if present)
	 * @param genre
	 * 		the genre to remove the album from
	 * @return
	 * 		true if successfully removed
	 */
	public boolean removeFromGenre(Genre genre) {
		boolean removed = false;
		if (genre == parentGenre) {
			removed = parentGenre.removeChild(this);
			parentGenre = null;
		}
		return removed;
		
	}

	/**
	 * Add the album to the given genre
	 * 
	 * @param genre
	 *            the genre to add the album to.
	 */
	public void setGenre(Genre genre) {
	
		
		// add to new genre
		if (genre != null) {
		
			// remove from old genre first (prevents duplicates)
			if (parentGenre != null) {
				removeFromGenre(parentGenre);
			}
			
			genre.addChild(this);
			parentGenre = genre;
			// System.out.println(genreName);
		}
	}

	/**
	 * Returns the genre that this album belongs to.
	 * 
	 * @return the genre that this album belongs to
	 */
	public Genre getGenre() {
		return parentGenre;
	}

	/**
	 * Set the album title. Permits setting a title and renaming album title.
	 * 
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Returns the title of the album
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Returns the performer of the album
	 * 
	 * @return the performer
	 */
	public String getPerformer() {
		return performer;
	}

	/**
	 * An album cannot have any children (it cannot contain anything).
	 */
	@Override
	public boolean hasChildren() {
		return false;
	}

	/**
	 * Returns the string representation of the given album. The representation
	 * contains the title, performer and songlist.
	 * 
	 * @return the string representation
	 */
	public String toString() {
		StringBuilder sb;
		sb = new StringBuilder(openTag + "\n");
		sb = sb.append(openNameTag + title + closeNameTag + "\n");
		sb = sb.append(openPerformerTag + performer + closePerformerTag + "\n");
		for (String song : songList) {
			sb = sb.append(openSongTag + song + closeSongTag + "\n");
		}
		sb = sb.append(closeTag + "\n");
		return sb.toString();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((performer == null) ? 0 : performer.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	/**
	 * Checks if the Album has the same artist and title
	 * 
	 * @param the Album to compare
	 * 
	 * @return
	 * 		true of the Albums title and artist match
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Album other = (Album) obj;
		if (performer == null) {
			if (other.performer != null)
				return false;
		} else if (!performer.equals(other.performer))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

}