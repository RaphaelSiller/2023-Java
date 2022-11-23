package net.tfobz.dynamicTest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.function.ThrowingConsumer;

import net.tfobz.relationship.Person;

public class PersonDynamicTest {

	public static ArrayList<Person> personenListe;

	@BeforeAll
	public static void setup() {
		personenListe = new ArrayList<Person>();
		personenListe.add(new Person("Hans", Person.Gender.FEMALE));
		personenListe.add(new Person("Tim", Person.Gender.MALE));
		personenListe.add(new Person("Peter", Person.Gender.MALE));
		personenListe.add(new Person("Maja", Person.Gender.FEMALE));
		personenListe.add(new Person("Lisa", Person.Gender.FEMALE));
		personenListe.add(new Person("Ronald", Person.Gender.MALE));

//		personenListe.stream().map((ele) -> {
//			// Inverse Gender
//			ele.setGender((ele.getGender() == Person.Gender.MALE) ? Person.Gender.MALE : Person.Gender.FEMALE);
//			return ele;
//		}).forEach(System.out::println);
	}

	//Functional Interface - uses input to generate output 
	// the output is used for the displayName of the Test
	public Function<Person,String> displayNameGenerator = (n)->"Generic Test with person: " + n.toString();
	
	public ThrowingConsumer<Person> testExecutable = (person)->assertThrows(IllegalArgumentException.class, ()->{person.setGender(null);});
	
	@TestFactory
	public Stream<DynamicTest> test1() {
		return personenListe.stream().map((ele) -> DynamicTest.dynamicTest("Ist " + ele.getName() + " weiblich",
				() -> assertTrue(ele.getGender() == Person.Gender.FEMALE)));
	}
	
	@TestFactory
	public Stream<DynamicTest> test2() {
		return DynamicTest.stream(personenListe.iterator(), displayNameGenerator, testExecutable);
	}
}
