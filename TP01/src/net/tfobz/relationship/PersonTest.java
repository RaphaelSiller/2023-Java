package net.tfobz.relationship;

import org.junit.Test;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import net.tfobz.relationship.Person.Gender;

/**
 * Tested die Person Klasse
 * 
 * @author unico
 *
 */
@SuppressWarnings("unused")
public class PersonTest
{
	Person p1 = new Person("Gott", Gender.FEMALE);
	Person mother = new Person("Mutter Gretchen", Gender.FEMALE);
	Person father = new Person("Vater Faust", Gender.MALE);
	Person children = new Person("Kind Mephisto", Gender.MALE);
	Person daughter= new Person("Tochter Mephisto", Gender.FEMALE);
	Person son = new Person("Sohn Mephisto", Gender.MALE);
	
	@BeforeEach
	void setUp() throws Exception {
		p1 = new Person("Gott", Gender.FEMALE);
		mother = new Person("Mutter Gretchen", Gender.FEMALE);
		father = new Person("Vater Faust", Gender.MALE);
		children = new Person("Kind Mephisto", Gender.MALE);
		daughter= new Person("Tochter Mephisto", Gender.FEMALE);
		son = new Person("Sohn Mephisto", Gender.MALE);
	}
	
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
		
		//Wenn es nicht ein instanziiertes Array wäre, würde es einen anderen Fehler werden
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
		daughter.setFather(father);
		daughter.setMother(mother);
		son.setFather(father);
		son.setMother(mother);
		assertTrue(son.getSisters().contains(daughter));
		assertTrue(daughter.getBrothers().contains(son));
	}
	
	@Test
	public void descendants() {
		Person firstGenFather = new Person("", Gender.MALE);
		Person firstGenMother = new Person("", Gender.MALE);
		Person secondGenFather = new Person("", Gender.MALE);
		Person secondGenMother = new Person("", Gender.MALE);
		Person thirdGenFather = new Person("", Gender.MALE);
		Person thirdGenMother = new Person("", Gender.MALE);
		
		secondGenFather.setFather(firstGenFather);
		secondGenFather.setMother(firstGenMother);
		secondGenMother.setFather(firstGenFather);
		secondGenMother.setMother(firstGenMother);

		thirdGenFather.setFather(secondGenFather);
		thirdGenFather.setMother(secondGenMother);
		thirdGenMother.setFather(secondGenFather);
		thirdGenMother.setMother(secondGenMother);
		
		//TODO add Asserts
	}
	
	
}