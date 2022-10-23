/**
 * 
 */
package net.tfobz.relationship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

/**
 * @author Rafid Fallaha & Raphael Siller
 *
 */
class PersonListTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
	}

	/*
	 * Parametizierter Test, was alle Edgecases überprüft ob sie ein IllegalArgumentException werfen.
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "relationshipWrong.txt")
	@SuppressWarnings("unused")
	void StringToPersonIllegalArgumentException(String person) {
		assertThrows(IllegalArgumentException.class, () -> {
			for(String p1 : person.split("\\n")) {
//				System.out.println("\t"+ p1);
				Person p = PersonList.StringToPerson(p1);
			}
		});
	}

	/**
	 * Test method for
	 * {@link net.tfobz.relationship.PersonList#PersonList(java.lang.String)}.
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "relationshipCorrect.txt", delimiterString = "\\n")
	@SuppressWarnings({"unused"})
	void readPersonCorrect(String person) {
		System.out.println(person);
		Person p = PersonList.StringToPerson(person);
	}
	
	/**
	 * Test method for {@link net.tfobz.relationship.PersonList#PersonList()}.
	 */
	@Test
	@Disabled
	void testPersonList() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link net.tfobz.relationship.PersonList#PersonList(java.io.BufferedReader)}.
	 */
	@Test
	@Disabled
	void testPersonListBufferedReader() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link net.tfobz.relationship.PersonList#PersonList(java.lang.String)}.
	 */
	@Test
	@Disabled
	void testPersonListString() {
		fail("Not yet implemented");
	}

}
