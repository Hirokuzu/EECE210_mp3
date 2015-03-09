package ca.ubc.ece.eece210.mp3.grade;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ca.ubc.ece.eece210.mp3.Album;
import ca.ubc.ece.eece210.mp3.Catalogue;
import ca.ubc.ece.eece210.mp3.Genre;

public class MusicCatalogueQueryTest {
	
	private Catalogue myCatalogue;

	Genre jazz;
	Genre psychJazz;
	Genre cinema;

	Album ripley;
	Album crossings;
	Album angels;

	@Before
	// Builds up sample catalogue
	public void setup() {

		// Build template catalogue
		jazz = new Genre("Jazz");
		psychJazz = new Genre("Psychedelic Jazz");

		cinema = new Genre("Cinema");

		ArrayList<String> angelsSongs = new ArrayList<String>();
		angelsSongs.add("When Did You Leave Heaven?");
		angelsSongs.add("You're a Heavenly Thing");
		angelsSongs.add("I Married an Angel");
		angelsSongs.add("A Sinner Kissed an Angel");
		angelsSongs.add("Angela Mia");
		angelsSongs.add("Angel Child");
		angelsSongs.add("And the Angels Sing");
		angelsSongs.add("Fools Rush In (Where Angels Fear to Tread)");
		angelsSongs.add("I'll String Along With You");
		angelsSongs.add("Angel");
		angelsSongs.add("The Prisoner's Song");
		angelsSongs.add("Goodnight, Angel");
		angels = new Album("Louis and the Angels", "Louis Armstrong",
				angelsSongs);

		ArrayList<String> crossingsSongs = new ArrayList<String>();
		crossingsSongs.add("Sleeping Giant");
		crossingsSongs.add("Quasar");
		crossingsSongs.add("Water Torture");
		crossings = new Album("Crossings", "Herbie Hancock", crossingsSongs);

		ArrayList<String> ripleySongs = new ArrayList<String>();
		ripleySongs.add("Tu Vuo' Fa L'Americano");
		ripleySongs.add("My Funny Valentine");
		ripleySongs.add("Italia");
		ripleySongs.add("Lullaby for Cain");
		ripleySongs.add("Crazy Tom");
		ripleySongs.add("Ko-Ko");
		ripleySongs.add("Nature Boy");
		ripleySongs.add("Mischief");
		ripleySongs.add("Ripley");
		ripleySongs.add("Pent-Up House");
		ripleySongs.add("Guaglione");
		ripleySongs.add("Moanin'");
		ripleySongs.add("Proust");
		ripleySongs.add("Four");
		ripleySongs.add("Promise");
		ripleySongs.add("The Champ");
		ripleySongs.add("Syncopes");
		ripleySongs.add("Stabat Mater");
		ripleySongs.add("You Don't Know What Love Is");
		ripley = new Album("The Talented Mr. Ripley", "Gabriel Yared",
				ripleySongs);

		angels.setGenre(jazz);
		jazz.addToGenre(psychJazz);
		crossings.setGenre(psychJazz);
		psychJazz.addToGenre(crossings);

		ripley.setGenre(cinema);

		// create new Catalogue
		myCatalogue = new Catalogue();
		myCatalogue.add(jazz);
		myCatalogue.add(cinema);

	}
	
	@Test
	public void testIn() {
		
		String query = "in (\"Jazz\")";
		List<Album> queryResults = myCatalogue.query(query);
		
		assertEquals("Checking size of results", 2, queryResults.size());
		assertTrue("Checking results contains \"Louis and the Angles\"", queryResults.contains(angels));
		assertTrue("Checking results contains \"Crossings\"", queryResults.contains(crossings));
	}
	
	@Test
	public void testBy() {
		
		String query = "by (\"Herbie Hancock\")";
		List<Album> queryResults = myCatalogue.query(query);
		
		assertEquals("Checking size of results", 1, queryResults.size());
		assertTrue("Checking results contains \"Crossings\"", queryResults.contains(crossings));
		
	}
	
	@Test
	public void testMatches() {
		
		String query = "matches (\".*[tT]he.*\")";
		List<Album> queryResults = myCatalogue.query(query);
		
		assertEquals("Checking size of results", 2, queryResults.size());
		assertTrue("Checking results contains \"Louis and the Angles\"", queryResults.contains(angels));
		assertTrue("Checking results contains \"The Talended Mr. Ripley\"", queryResults.contains(ripley));
		
	}

	@Test
	public void testInAndMatches() {
		
		String query = "in (\"Jazz\") && matches (\".*Ripley.*\")";
		List<Album> queryResults = myCatalogue.query(query);
		assertTrue(queryResults.isEmpty());
		
		query = "in (\"Jazz\") && matches (\".*ross.*\")";
		queryResults = myCatalogue.query(query);
		assertEquals("Checking size of results", 1, queryResults.size());
		assertTrue("Checking results contains \"Crossings\"", queryResults.contains(crossings));
	}

	@Test
	public void testInOrMatches() {
		
		String query = "in (\"Jazz\") || matches (\".*Ripley.*\")";
		List<Album> queryResults = myCatalogue.query(query);
		
		assertEquals("Checking size of results", 3, queryResults.size());
		assertTrue("Checking results contains \"Louis and the Angles\"", queryResults.contains(angels));
		assertTrue("Checking results contains \"Crossings\"", queryResults.contains(crossings));
		assertTrue("Checking results contains \"The Talented Mr. Ripley\"", queryResults.contains(ripley));
	}
	
	@Test
	public void testAndOrAnd() {
		
		String query = "in (\"Jazz\")  && by (\"Louis Armstrong\") || in (\"Cinema\") && matches (\".*Ripley.*\")";
		List<Album> queryResults = myCatalogue.query(query);
		
		assertEquals("Checking size of results", 2, queryResults.size());
		assertTrue("Checking results contains \"Louis and the Angles\"", queryResults.contains(angels));
		assertTrue("Checking results contains \"The Talented Mr. Ripley\"", queryResults.contains(ripley));
	}
	
}
