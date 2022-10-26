l/**
 * 
 */
package net.tfobz.relationship;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import net.tfobz.relationship.Person.Gender;

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
	
	@Test
	public void readPersonsFromFile() {
		try {
			PersonList liste = new PersonList("relationship.txt");
			liste.contains(new Person("Sepp", Gender.MALE));
			liste.contains(new Person("Rosi", Gender.FEMALE));
			
			Person mutter = new Person("Rosi", Gender.FEMALE);
			Person vater = new Person("Sepp", Gender.MALE);
			liste.contains(new Person("Rudi", Gender.MALE, mutter, vater, new ArrayList<>()));
			liste.contains(new Person("Sepp", Gender.MALE, mutter, vater, new ArrayList<>()));
		} catch (IOException e) {
			fail("Exception");
		}
	}

}
