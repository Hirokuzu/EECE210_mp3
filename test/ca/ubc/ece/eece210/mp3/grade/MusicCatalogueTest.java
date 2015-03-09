package ca.ubc.ece.eece210.mp3.grade;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;

import org.junit.Test;

import ca.ubc.ece.eece210.mp3.Album;
import ca.ubc.ece.eece210.mp3.Genre;

public class MusicCatalogueTest {

	@Test
	public void testGenreEquals() {
		Genre genre = new Genre("Jazz");
		Genre genre2 = new Genre("Jazz");
		Genre genre3 = new Genre("blah");
		
		assertEquals("Checking genre equality", genre, genre2);
		assertNotEquals("Checking genre inequality", genre, genre3);
	}
	
	@Test
	public void testGenreHashCode() {
		Genre genre = new Genre("Jazz");
		Genre genre2 = new Genre("Jazz");
		assertEquals("Checking hashcode equality", genre.hashCode(), genre2.hashCode());
	}
	
	@Test
	public void testAlbumEquals() {
		Album album0 = new Album("album0", "hello kitty", new ArrayList<String>());
		Album album1 = new Album("album0", "hello kitty", new ArrayList<String>());
		Album album2 = new Album("album1", "hello kitty", new ArrayList<String>());
		Album album3 = new Album("album0", "hello kitty 2", new ArrayList<String>());
		
		assertEquals("Checking album equality", album0, album1);
		assertNotEquals("Checking title inequality", album0, album2);
		assertNotEquals("Checking performer inequality", album0, album3);
	}
	
	@Test
	public void testAlbumHashCode() {
		Album album0 = new Album("album0", "hello kitty", new ArrayList<String>());
		Album album1 = new Album("album0", "hello kitty", new ArrayList<String>());
		assertEquals("Checking hashcode equality", album1.hashCode(), album0.hashCode());
	}
}
