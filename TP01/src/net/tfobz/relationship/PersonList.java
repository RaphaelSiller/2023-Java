package net.tfobz.relationship;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import net.tfobz.relationship.Person.Gender;

@SuppressWarnings("serial")
public class PersonList extends ArrayList<Person> {
	public PersonList() {
	}

	public PersonList(BufferedReader reader) throws IOException, IllegalArgumentException {
		readPersons(reader);
	}

	public PersonList(String filename) throws FileNotFoundException, IOException, IllegalArgumentException {

	}

	private void readPersons(BufferedReader reader) throws IOException, IllegalArgumentException {
		// Liest die Ganze Datei in einen String
		String readWhole = "", readLine;
		while ((readLine = reader.readLine()) != null)
			readWhole += readLine + "\n";

		// splittet diesen in ein Array auf.
		String[] PersonsAsStrings = readWhole.split("\n");

		// Wandelt jedes Element in eine Person um und fügt Sie der Liste hinzu.
		for (String person : PersonsAsStrings)
			this.add(StringToPerson(person));
	}

	public static Person StringToPerson(String person) {
		/**
		 * Teilt den übergebenen String in 4 Teile auf:
		 * args[0] = Name der Person
		 * args[1] = Gender als String
		 * args[2] = Name der Mutter
		 * args[3] = Name des Vaters
		 */
		String[] args = person.split(";");
		// Kontrolle ob alle Argumente gegeben sind
		if (args.length < 4)
			throw new IllegalArgumentException("Zu wenig Argumente übergeben");
		if (args.length > 4)
			throw new IllegalArgumentException("Zu viele Argumente übergeben");

		if (!(args[1].equals("MALE") || args[1].equals("FEMALE")))
			throw new IllegalArgumentException("Sorry, mein Auftragsgeber ist LGBTQ+ Hasser");
		String name   = args[0];
		Gender gender = args[1] == "MALE" ? Gender.MALE : Gender.FEMALE;
		Person mother = new Person(args[2], Gender.FEMALE), father = new Person(args[3], Gender.MALE);

		return new Person(name, gender, mother, father, new ArrayList<>());
	}

}
