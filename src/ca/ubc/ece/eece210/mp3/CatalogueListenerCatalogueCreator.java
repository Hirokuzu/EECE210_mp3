package ca.ubc.ece.eece210.mp3;

import java.util.List;
import java.util.Stack;
import java.util.ArrayList;
import org.antlr.v4.runtime.tree.TerminalNode;

public class CatalogueListenerCatalogueCreator extends CatalogueBaseListener {
	// This is the Catalogue object that we will return when the parse tree has
	// been walked.
	private Catalogue catalogue = new Catalogue();

	// some variables that we will need for intermediate processing
	private String currentName;
	private String currentPerformer;
	private List<String> currSongList;
	private Album currAlbum;
	private Genre currGenre;

	// We will use this stack to keep track of top-level Genres when there are
	// nested (sub-) genres.
	private Stack<Genre> genreStack = new Stack<Genre>();

	// Help us track whether we are processing a Genre, an Album or the
	// Catalogue. We need to maintain state information because some objects can
	// only be created after multiple steps on the parse tree.
	private enum State {
		ProcessingGenre, ProcessingAlbum, ProcessingCatalogue
	};

	private State currentState;

	/**
	 * Start a processing a new Genre
	 * 
	 * @param ctx
	 *            the current Genre context in the parser
	 */
	@Override
	public void enterGenre(CatalogueParser.GenreContext ctx) {
		currentState = State.ProcessingGenre;
		if (currGenre != null) {
			// Save the current genre because we will need it later
			genreStack.push(currGenre);
		}
	}

	/**
	 * Start a processing a new Album
	 * 
	 * @param ctx
	 *            the current Album context in the parser
	 */
	@Override
	public void enterAlbum(CatalogueParser.AlbumContext ctx) {
		// We are starting a new album! Let us change the state.
		currentState = State.ProcessingAlbum;

		// Let us create an empty song list.
		// This will get populated as we process more of the text
		currSongList = new ArrayList<String>();
	}

	/**
	 * Start processing a new name
	 * 
	 * @param ctx
	 *            the current name context in the parser
	 */
	@Override
	public void enterName(CatalogueParser.NameContext ctx) {
		TerminalNode token = ctx.TEXT();
		currentName = token.getText();

		// If we are processing a Genre, then we can create the Genre because we
		// have its name
		if (currentState == State.ProcessingGenre) {
			currGenre = new Genre(currentName);
		}
	}

	/**
	 * Process the 'performer' name
	 * 
	 * @param ctx
	 *            the current performer context in the parser
	 */
	@Override
	public void enterPerformer(CatalogueParser.PerformerContext ctx) {
		TerminalNode token = ctx.TEXT();
		currentPerformer = token.getText();
	}

	/**
	 * Start processing a new song
	 * 
	 * @param ctx
	 *            the current song context in the parser
	 */
	@Override
	public void enterSong(CatalogueParser.SongContext ctx) {
		TerminalNode token = ctx.TEXT();
		// we would have also created a song list, so let us add the song to
		// that list
		currSongList.add(token.getText());
	}

	/**
	 * Wrap up an album when the </album> tag is encountered.
	 * 
	 * @param ctx
	 *            the current Album context in the parser
	 */
	@Override
	public void exitAlbum(CatalogueParser.AlbumContext ctx) {
		// we are done with the album text so we can create the object
		currAlbum = new Album(currentName, currentPerformer, currSongList);

		// add this album to the appropriate genre or to the root of the
		// catalogue
		if (currGenre != null) {
			// System.out.println("adding album to genre\n");
			currAlbum.setGenre(currGenre);
		} else {
			catalogue.add(currAlbum);
		}
		// now change the current state and set currAlbum to null
		if (currGenre != null) {
			currentState = State.ProcessingGenre;
		} else {
			currentState = State.ProcessingCatalogue;
		}
		currAlbum = null;
	}

	/**
	 * Wrap up processing a Genre when the </genre> tag is encountered.
	 * 
	 * @param ctx
	 *            the current Genre context in the parser
	 */
	@Override
	public void exitGenre(CatalogueParser.GenreContext ctx) {
		// we have processed a genre
		// now we must add it to the parent genre if there is a genre on the
		// stack otherwise we add it to the catalogue
		if (!genreStack.empty()) {
			genreStack.peek().addChild(currGenre);
			currGenre = genreStack.pop();
			// state remains unchanged. still processing genres, except parent
			// genre now.
		} else {
			catalogue.add(currGenre); 
			currGenre = null;
			// now we change the state
			currentState = State.ProcessingCatalogue;
		}
	}

	/**
	 * Return a Catalogue object after having parsed the tree representation.
	 * Requires that the parse tree has been walked with this listener object
	 * before this method can be called.
	 * 
	 * @return the catalogue obtained from the input source.
	 */
	public Catalogue getCatalogue() {
		return catalogue;
	}
}
