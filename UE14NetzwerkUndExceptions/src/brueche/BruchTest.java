package brueche;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BruchTest {

	Bruch b1, b2, b3;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		b1 = new Bruch(5, 8);
		b2 = new Bruch(9, 5);
		b3 = new Bruch(80, 37);
	}

	@Test
	void testBruch() {

		assertThrows(BruchException.class, () -> new Bruch(5, 0));
		assertEquals(5, b1.getZaehler());
		assertEquals(8, b1.getNenner());

		// Test kürzen bei Constructor
		try {
			Bruch b = new Bruch(8, 4);
			assertEquals(2, b.getZaehler());
			assertEquals(1, b.getNenner());
		} catch (BruchException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testGetZaehler() {
		assertEquals(5, b1.getZaehler());
	}

	@Test
	void testSetZaehler() {
		b1.setZaehler(9);
		assertEquals(9, b1.getZaehler());
	}

	@Test
	void testGetNenner() {
		assertEquals(8, b1.getNenner());
	}

	@Test
	void testSetNenner() {
		try {
			b1.setNenner(1);
		} catch (BruchException e) {
			e.printStackTrace();
		}
		assertEquals(1, b1.getNenner());
		assertThrows(BruchException.class, () -> b1.setNenner(0));
	}

	@Test
	void testToString() {
		assertEquals(b1.toString(), "5/8");
	}

	@Test
	void testEqualsBruch() {
		try {
			assertTrue(b1.equals(new Bruch(5, 8)));
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (BruchException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testClone() {
		assertTrue(b1.equals(b1.clone()));
	}

	@Test
	void testAddiere() {
		b1.addiere(b2);
		assertEquals(97, b1.getZaehler());
		assertEquals(40, b1.getNenner());

	}

	@Test
	void testSubtrahiere() {
			b1.subtrahiere(b2);
			assertEquals(-47, b1.getZaehler());
			assertEquals(40, b1.getNenner());
	}

	@Test
	void testMultipliziere() {
		b1.multipliziere(b2);
		assertEquals(8, b1.getNenner());
		assertEquals(9, b1.getZaehler());
	}

	@Test
	void testDividiere() {
		b1.dividiere(b2);
		assertEquals(72, b1.getNenner());
		assertEquals(25, b1.getZaehler());
	}

}
