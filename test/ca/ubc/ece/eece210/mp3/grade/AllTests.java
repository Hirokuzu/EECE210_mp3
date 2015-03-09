package ca.ubc.ece.eece210.mp3.grade;

import java.lang.reflect.Method;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({  QueryParserTest.class, MusicCatalogueTest.class, MusicCatalogueQueryTest.class })
public class AllTests {

	// for use with non-junit
	public static void main(String[] args) {

		int numPassed = 0;
		int numTotal = 0;

		// run tests
		System.out.println("Query parser tests:");
		for (Method m : QueryParserTest.class.getDeclaredMethods()) {

			if (m.getName().startsWith("test")) {
				// run test
				numTotal++;
				try {
					// initialize
					QueryParserTest tester = new QueryParserTest();
					m.invoke(tester);
					System.out.println("Test: " + m.getName() + " passed");
					numPassed++;
				} catch (Error | Exception e) {
					System.out.println("Test: " + m.getName() + " failed");
					System.out.print("   ");
					String msg = e.getMessage();
					if (msg == null && e.getCause() != null) {
						msg = e.getCause().getMessage();
					}
					System.out.println(msg);
					e.printStackTrace();
				}
			}
		}

		// run tests
		System.out.println("Music catalogue tests:");
		for (Method m : MusicCatalogueTest.class.getDeclaredMethods()) {

			if (m.getName().startsWith("test")) {
				// run test
				numTotal++;
				try {
					// initialize
					MusicCatalogueTest tester = new MusicCatalogueTest();
					m.invoke(tester);
					System.out.println("Test: " + m.getName() + " passed");
					numPassed++;
				} catch (Error | Exception e) {
					System.out.println("Test: " + m.getName() + " failed");
					System.out.print("   ");
					String msg = e.getMessage();
					if (msg == null && e.getCause() != null) {
						msg = e.getCause().getMessage();
					}
					System.out.println(msg);
					e.printStackTrace();
				}
			}
		}
		
		// run tests
		System.out.println("Music catalogue interpreter tests:");
		for (Method m : MusicCatalogueQueryTest.class.getDeclaredMethods()) {

			if (m.getName().startsWith("test")) {
				// run test
				numTotal++;
				try {
					// initialize
					MusicCatalogueQueryTest tester = new MusicCatalogueQueryTest();
					tester.setup();
					m.invoke(tester);
					System.out.println("Test: " + m.getName() + " passed");
					numPassed++;
				} catch (Error | Exception e) {
					System.out.println("Test: " + m.getName() + " failed");
					System.out.print("   ");
					String msg = e.getMessage();
					if (msg == null && e.getCause() != null) {
						msg = e.getCause().getMessage();
					}
					System.out.println(msg);
					e.printStackTrace();
				}
			}
		}

		System.out.println("DONE!  Passed: " + numPassed + "/" + numTotal);

	}

}
