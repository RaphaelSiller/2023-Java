package net.tfobz.dynamicTest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestFactory;

import net.tfobz.relationship.Person;
import net.tfobz.relationship.Person.Gender;

public class DynamicTestPerson {
	ArrayList<Person> personenGruppe = new ArrayList<Person>();
	
	@BeforeAll
	public void setup() {
		personenGruppe.add(new Person("Herr Rudi", Gender.MALE));
		personenGruppe.add(new Person("Frau Rudi", Gender.FEMALE));
		personenGruppe.add(new Person("Herr Franz", Gender.MALE));
		personenGruppe.add(new Person("Frau Franz", Gender.FEMALE));
		personenGruppe.add(new Person("Herr Sepp", Gender.MALE));
		personenGruppe.add(new Person("Frau Sepp", Gender.FEMALE));
		personenGruppe.add(new Person("Herr Pla", Gender.MALE));
		personenGruppe.add(new Person("Frau Pla", Gender.FEMALE));
		personenGruppe.add(new Person("Herr Tia", Gender.MALE));
		personenGruppe.add(new Person("Frau Tia", Gender.FEMALE));
	}
	@TestFactory
	public 
}
