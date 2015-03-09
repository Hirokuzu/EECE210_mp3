package ca.ubc.ece.eece210.mp3.ast;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ca.ubc.ece.eece210.mp3.Album;
import ca.ubc.ece.eece210.mp3.Catalogue;
import ca.ubc.ece.eece210.mp3.Element;
import ca.ubc.ece.eece210.mp3.Genre;

public class CatalogueTest {

	private Catalogue myCatalogue;

	Genre jazz;
	Genre psychJazz;
	Genre cinema;

	Album ripley;
	Album crossings;
	Album angels;
	
	@Before
	public void setUp() throws Exception {
		
		// Setup source: taken from MP2 (hacked) from Antonio Sanchez
		
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

		jazz.addToGenre(angels);
		jazz.addToGenre(psychJazz);
		psychJazz.addToGenre(crossings);

		cinema.addToGenre(ripley);

		// create new Catalogue
		myCatalogue = new Catalogue();
		myCatalogue.add(jazz);
		myCatalogue.add(cinema);
	}

	@Test
	public void testIn() {
		List<Token> tokens = QueryTokenizer.tokenizeInput("in (\"Jazz\")");
		QueryParser parser = new QueryParser(tokens);
		Iterator<Element> interprettedSet = parser.getRoot().interpret(myCatalogue).iterator();
		List<Element> testOutput = new ArrayList<Element>();
		while( interprettedSet.hasNext() ) {
			testOutput.add( interprettedSet.next() );
		}
		List<Element> expectedOutput = new ArrayList<Element>();
		expectedOutput.add(crossings);
		expectedOutput.add(angels);
		assertEquals(testOutput.containsAll(expectedOutput), expectedOutput.containsAll(testOutput));
	}

	@Test
	public void testBy() {
		List<Token> tokens = QueryTokenizer.tokenizeInput("by (\"Gabriel Yared\")");
		QueryParser parser = new QueryParser(tokens);
		Iterator<Element> interprettedSet = parser.getRoot().interpret(myCatalogue).iterator();
		List<Element> testOutput = new ArrayList<Element>();
		while( interprettedSet.hasNext() ) {
			testOutput.add( interprettedSet.next() );
		}
		List<Element> expectedOutput = new ArrayList<Element>();
		expectedOutput.add(ripley);
		assertEquals(testOutput.containsAll(expectedOutput), expectedOutput.containsAll(testOutput));
	}
	
	@Test
	public void testMatch() {
		List<Token> tokens = QueryTokenizer.tokenizeInput("matches (\".*Ripley.*\")");
		QueryParser parser = new QueryParser(tokens);
		Iterator<Element> interprettedSet = parser.getRoot().interpret(myCatalogue).iterator();
		List<Element> testOutput = new ArrayList<Element>();
		while( interprettedSet.hasNext() ) {
			testOutput.add( interprettedSet.next() );
		}
		List<Element> expectedOutput = new ArrayList<Element>();
		expectedOutput.add(ripley);
		assertEquals(testOutput.containsAll(expectedOutput), expectedOutput.containsAll(testOutput));
	}
	
	@Test
	public void testOr() {
		List<Token> tokens = QueryTokenizer.tokenizeInput("matches (\".*Ripley.*\") || in (\"Jazz\")");
		QueryParser parser = new QueryParser(tokens);
		Iterator<Element> interprettedSet = parser.getRoot().interpret(myCatalogue).iterator();
		List<Element> testOutput = new ArrayList<Element>();
		while( interprettedSet.hasNext() ) {
			testOutput.add( interprettedSet.next() );
		}
		List<Element> expectedOutput = new ArrayList<Element>();
		expectedOutput.add(ripley);
		expectedOutput.add(crossings);
		expectedOutput.add(angels);
		assertEquals(testOutput.containsAll(expectedOutput), expectedOutput.containsAll(testOutput));
	}
	
	@Test
	public void testAnd() {
		List<Token> tokens = QueryTokenizer.tokenizeInput("matches (\".*Ripley.*\") && in (\"Jazz\")");
		QueryParser parser = new QueryParser(tokens);
		Iterator<Element> interprettedSet = parser.getRoot().interpret(myCatalogue).iterator();
		List<Element> testOutput = new ArrayList<Element>();
		while( interprettedSet.hasNext() ) {
			testOutput.add( interprettedSet.next() );
		}
		List<Element> expectedOutput = new ArrayList<Element>();
		// should be empty.
		assertEquals(testOutput.containsAll(expectedOutput), expectedOutput.containsAll(testOutput));
	}
	
	public void testCompound() {
		List<Token> tokens = QueryTokenizer.tokenizeInput("matches (\".*Ripley.*\") && in (\"Jazz\") || by (\"Herbie Hancock\")");
		QueryParser parser = new QueryParser(tokens);
		Iterator<Element> interprettedSet = parser.getRoot().interpret(myCatalogue).iterator();
		List<Element> testOutput = new ArrayList<Element>();
		while( interprettedSet.hasNext() ) {
			testOutput.add( interprettedSet.next() );
		}
		List<Element> expectedOutput = new ArrayList<Element>();
		expectedOutput.add(crossings);
		assertEquals(testOutput.containsAll(expectedOutput), expectedOutput.containsAll(testOutput));
	}
}
