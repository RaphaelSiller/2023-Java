package net.tfobz.relationship;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;
import net.tfobz.relationship.Person.Gender;

/**
 * Tested die Person Klasse
 * 
 * @author unico
 *
 */
@SuppressWarnings({"unused", "static-access"})
public class PersonTest
{
	public static Person p1 = new Person("Gott", Gender.FEMALE);
	public static Person mother = new Person("Mutter Gretchen", Gender.FEMALE);
	public static Person father = new Person("Vater Faust", Gender.MALE);
	public static Person children = new Person("Kind Mephisto", Gender.MALE);
	public static Person daughter= new Person("Tochter Mephisto", Gender.FEMALE);
	public static Person son = new Person("Sohn Mephisto", Gender.MALE);
	
	@BeforeAll
	public static void setup() {
		p1 = new Person("Gott", Gender.FEMALE);
		mother = new Person("Mutter Gretchen", Gender.FEMALE);
		father = new Person("Vater Faust", Gender.MALE);
		children = new Person("Kind Mephisto", Gender.MALE);
		daughter= new Person("Tochter Mephisto", Gender.FEMALE);
		son = new Person("Sohn Mephisto", Gender.MALE);
	}
	
//	@BeforeEach
//	void setUp() throws Exception {
//		p1 = new Person("Gott", Gender.FEMALE);
//		mother = new Person("Mutter Gretchen", Gender.FEMALE);
//		father = new Person("Vater Faust", Gender.MALE);
//		children = new Person("Kind Mephisto", Gender.MALE);
//		daughter= new Person("Tochter Mephisto", Gender.FEMALE);
//		son = new Person("Sohn Mephisto", Gender.MALE);
//	}
	
	/**
	 * Tested ob bei Konstruktor die Parameter richtig gesetzt werden
	 * 
	 */
	@Test
	public void creation() {
		Person p = new Person("Sepp", Gender.MALE);
		assertEquals(p.getName(), "Sepp");
		assertEquals(p.getGender(), Gender.MALE);
	}

	/**
	 * Tested ob name und Gender leer sind.
	 */
	@Test
	public void nameGenderEmpty() {
		assertThrows(IllegalArgumentException.class, () -> {
			p1.setName(null);
			p1.setGender(Gender.MALE);
		});
	}

	/**
	 * Tested ob nur name leer ist.
	 */
	@Test
	public void nameEmpty() {
		assertThrows(IllegalArgumentException.class, () -> {
			p1.setName(null);
			p1.setName("");
		});
	}

	/**
	 * Tested ob nur Gender leer ist.
	 */
	@Test
	public void GenderEmpty() {
		assertThrows(IllegalArgumentException.class, () -> {
			p1.setGender(null);
		});
	}

	@Test
	public void creationNameGenderEmpty() {
		assertThrows(IllegalArgumentException.class, () -> {
			Person p = new Person(null, null);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			Person p = new Person("", Gender.MALE);
		});
	}

	@Test
	public void creationNameEmpty() {
		assertThrows(IllegalArgumentException.class, () -> {
			Person p = new Person("", null);
		});
	}

	@Test
	public void creationGenderEmpty() {
		assertThrows(IllegalArgumentException.class, () -> {
			Person p = new Person("Sepp", null);
		});
	}

	@Test
	public void equals() {
		Person p1RealOne = new Person("Sepp", Gender.MALE);
		Person p1Clone = new Person("Sepp", Gender.MALE);
		Person p2 = new Person("Frieda", Gender.FEMALE);
		assertTrue(!p1RealOne.equals(p2));
		assertTrue(p1RealOne.equals(p1Clone));
	}

	@Test
	public void equalsNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			this.p1.equals(null);
		});
	}

	@Test
	public void parent() {

		// Mutter
		p1.setMother(this.mother);
		assertTrue(p1.getMother().equals(this.mother));
		p1.setMother(null);
		assertNull(p1.getMother());
		// Vater
		p1.setFather(this.father);
		assertTrue(p1.getFather().equals(this.father));
		p1.setFather(null);
		assertNull(p1.getFather());
	}

	@Test
	public void setMother() {
		p1.setMother(this.mother);
		assertTrue(p1.getMother().equals(this.mother));
		p1.setMother(null);
		assertNull(p1.getMother());
	}

	@Test
	public void setFather() {
		p1.setFather(this.father);
		assertTrue(p1.getFather().equals(this.father));
		p1.setFather(null);
		assertNull(p1.getFather());
	}

	@Test
	public void parentIncorrectGender() {
		assertThrows(IllegalArgumentException.class, () -> {
			p1.setFather(this.mother);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			p1.setMother(this.father);
		});
	}
	
	@Test
	public void children() {
		//KIND IST NOCH NICHT GESETZT
		assertTrue(!father.getChildren().contains(children));
		assertTrue(!mother.getChildren().contains(children));
		
		//Kind wird gesetzt
		children.setFather(father);
		children.setMother(mother);
		//Eltern wurden richtig gesetzt
		assertEquals(father, children.getFather());
		assertEquals(mother, children.getMother());
		//Kind wurde richtig gesetzt
		assertTrue(father.getChildren().contains(children));
		assertTrue(mother.getChildren().contains(children));
		//Eltern entfernen
		children.setFather(null);
		children.setMother(null);
		//Eltern wurden richtig entfernt
		assertTrue(!father.getChildren().contains(children));
		assertTrue(!mother.getChildren().contains(children));
	}
	
	@Test
	public void daughtersSons() {
		Person p = new Person("Kal El", Gender.MALE);
		
		//Wenn es nicht ein instanziiertes Array wäre, würde es einen anderen Fehler werfen
		assertThrows(IndexOutOfBoundsException.class,() -> p.getSons().get(0));
		assertThrows(IndexOutOfBoundsException.class,() -> p.getDaughters().get(0));
		
		
		son.setFather(father);
		assertTrue(father.getSons().contains(son));
		daughter.setFather(father);
		assertTrue(father.getDaughters().contains(daughter));
		
		son.setMother(mother);
		assertTrue(mother.getSons().contains(son));
		daughter.setMother(mother);
		assertTrue(mother.getDaughters().contains(daughter));
	}
	
	@Test
	public void sistersBrothers() {
		//Normale Arbeitsweise (keine Edge-Cases)
		daughter.setFather(father);
		daughter.setMother(mother);
		son.setFather(father);
		son.setMother(mother);
		assertTrue(son.getSisters().contains(daughter));
		assertTrue(daughter.getBrothers().contains(son));
		
		//Half-siblings shouldn't be returned
		daughter.setFather(null);
		assertFalse(son.getSisters().contains(daughter));
		
		//If one parent is missing, method needs to return empty ArrayList
		assertTrue(daughter.getBrothers().isEmpty());
		assertTrue(daughter.getSisters().isEmpty());
	}
	
	@Test
	public void descendants() {
		//Setup
		Person firstGenFather = new Person("firstGenFather", Gender.MALE);
		Person firstGenMother = new Person("firstGenMother", Gender.FEMALE);
		Person secondGenFather = new Person("secondGenFather", Gender.MALE);
		Person secondGenMother = new Person("secondGenMother", Gender.FEMALE);
		Person thirdGenFather = new Person("thirdGenFather", Gender.MALE);
		Person thirdGenMother = new Person("thirdGenMother", Gender.FEMALE);
		
		secondGenFather.setFather(firstGenFather);
		secondGenFather.setMother(firstGenMother);
		secondGenMother.setFather(firstGenFather);
		secondGenMother.setMother(firstGenMother);

		thirdGenFather.setFather(secondGenFather);
		thirdGenFather.setMother(secondGenMother);
		thirdGenMother.setFather(secondGenFather);
		thirdGenMother.setMother(secondGenMother);
		
		//Normale Arbeitsweise (keine Edge-Cases)
		ArrayList<Person> descendantsOfFirstGenMother = firstGenMother.getDescendants();
		System.out.println(descendantsOfFirstGenMother);
		assertTrue(descendantsOfFirstGenMother.contains(thirdGenMother));
		assertTrue(descendantsOfFirstGenMother.contains(secondGenFather));
		
		//Du bist nicht dein eigener Nachfahre
		assertFalse(descendantsOfFirstGenMother.contains(firstGenMother));
		
		//Erstelle ein Set (A collection that contains no duplicate elements)
		// Wenn das Set kleiner ist, heißt dass, das die Liste Duplikate enthält
		Set<Person> setDescendantsOfFirstGenMother = new HashSet<Person>(descendantsOfFirstGenMother);
		assertFalse(setDescendantsOfFirstGenMother.size() < descendantsOfFirstGenMother.size());
	}
	
	@Test
	//Du darfst nicht dein eigenes Elternteil sein
	public void parentOfHimself() {
		//Normale Arbeitsweise (keine Edge-Cases)
		assertThrows(IllegalArgumentException.class, () -> {
			father.setFather(father);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			mother.setMother(mother);
		});
	}
	
	@Test
	//Time Travel Shenanigans are not allowed
	//Deine Eltern dürfen nicht deine Nachfahren sein
	public void parentIsDescendant() {
		//Normale Arbeitsweise (keine Edge-Cases)
		son.setFather(father);
		daughter.setMother(mother);
		assertThrows(IllegalArgumentException.class, () -> {
			father.setFather(son);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			mother.setMother(daughter);
		});
	}
}