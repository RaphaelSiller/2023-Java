package net.tfobz.relationship;

import java.util.ArrayList;

public class Person {
	public enum Gender {
		FEMALE, MALE
	};

	protected Gender gender = null;
	protected String name = null;
	protected Person mother = null;
	protected Person father = null;
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected ArrayList<Person> children = new ArrayList();

	public Person(String name, Gender gender, Person mother, Person father, ArrayList<Person> children) {
		this.setGender(gender);
		this.setName(name);
		this.setMother(mother);
		this.setFather(father);
		this.children = children;
	}

	public Person(String name, Gender gender) throws IllegalArgumentException {
		if (name == null || gender == null)
			throw new IllegalArgumentException();
		this.name = name;
		this.gender = gender;

	}

	public Person() {
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		if (gender == null)
			throw (new IllegalArgumentException());
		this.gender = gender;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		if (this.name == null || name.length() == 0)
			throw new IllegalArgumentException();

	}

	public Person getMother() {
		return mother;
	}

	/**
	 * Setzt die Mutter auf übergebene Person. Außerdem wird bei der Mutter diese
	 * Person aus den Kindern gelöscht und bei neuer Mutter eingefügt
	 * 
	 * @param mother
	 * @throws IllegalArgumentException - nicht weiblich ist 
	 * 									- mother gleich sich selbst ist
	 * 									- mother ein Nachfahre ist
	 */
	public void setMother(Person mother) throws IllegalArgumentException {
		//Wenn mother == null braucht es keine Überprufung auf Illegale Argumente machen
		if (mother != null && (mother.gender != Gender.FEMALE || this.equals(mother) || this.getDescendants().contains(mother)))
			throw new IllegalArgumentException();

		// Entfernen des Kindes bei alter Mutter
		if (this.mother != null)
			this.mother.children.remove(this);

		this.mother = mother;
		// Hinzuügen des Kindes bei neuer Mutter
		if (this.mother != null)
			this.mother.children.add(this);
	}

	public Person getFather() {
		return father;
	}

	/**
	 * Setzt den Vater auf übergebene Person. Außerdem wird beim Vater diese Person
	 * aus den Kindern gelöscht und bei neuem Vater eingefügt
	 * 
	 * @param father
	 * @throws IllegalArgumentException - nicht männlich ist 
	 * 									- father gleich sich selbst ist
	 * 									- father ein Nachfahre ist
	 */
	public void setFather(Person father) throws IllegalArgumentException {
		//Wenn father == null braucht es keine Überprufung auf Illegale Argumente machen
		if (father != null && (father.gender != Gender.MALE || this.equals(father) || this.getDescendants().contains(father)))
			throw new IllegalArgumentException();

		// Entfernen des Kindes bei altem Vater
		if (this.father != null)
			this.father.children.remove(this);

		this.father = father;

		// Hinzufügen des Kindes bei neuem Vater
		if (this.father != null)
			this.father.children.add(this);
	}

	/**
	 * Gibt alle Kinder zurück. Wenn keine Kinder existieren, wird ein leeres
	 * ArrayList<Person> zurückgegeben
	 * 
	 * @return alle Kinder
	 */
	public ArrayList<Person> getChildren() {
		return children;
	}

	/**
	 * Gibt alle Söhne zurück. Wenn keine Söhne existieren, wird ein leeres
	 * ArrayList<Person> zurückgegeben
	 * 
	 * @return alle Söhne
	 */
	public ArrayList<Person> getSons() {
		ArrayList<Person> sons = new ArrayList<Person>();
		children.forEach(child -> {
			if (child.getGender() == Gender.MALE)
				sons.add(child);
		});
		return sons;
	}

	/**
	 * Gibt alle Töchter zurück. Wenn keine Töchter existieren, wird ein leeres
	 * ArrayList<Person> zurückgegeben
	 * 
	 * @return alle Töchter
	 */
	public ArrayList<Person> getDaughters() {
		ArrayList<Person> daughters = new ArrayList<Person>();
		children.forEach(child -> {
			if (child.getGender() == Gender.FEMALE)
				daughters.add(child);
		});
		return daughters;
	}

	/**
	 * Gibt alle Brüder zurück, d.h. alle Söhne vom Vater, welche auch Söhne von der
	 * Mutter sind. Wenn ein Elternteil fehlt, wird ein leeres ArrayList<Person>
	 * zurückgegeben.
	 * 
	 * @return alle Brüder
	 */
	public ArrayList<Person> getBrothers() {

		// List of all sons from Father which are also sons from Mother
		ArrayList<Person> brothers = new ArrayList<Person>();

		if (this.getFather() == null || this.getMother() == null)
			return brothers;

		// Wenn ein Elternteil fehlt, wird leeres ArrayList zurückgegeben
		ArrayList<Person> sonsOfFather = this.getFather().getSons();
		ArrayList<Person> sonsOfMother = this.getMother().getSons();

		// Fasse alle Söhne von Vater zusammen, welche auch Sohn von Mutter sind
		sonsOfFather.forEach((son) -> {
			if (sonsOfMother.contains(son))
				brothers.add(son);
		});

		// Ich glaube nicht dass man sein eigener Bruder ist, oder?
		brothers.remove(this);

		return brothers;
	}

	/**
	 * Gibt alle Schwestern zurück, d.h. alle Töchter vom Vater, welche auch Töchter
	 * von der Mutter sind. Wenn ein Elternteil fehlt, wird ein leeres
	 * ArrayList<Person> zurückgegeben.
	 * 
	 * @return alle Schwestern
	 */
	public ArrayList<Person> getSisters() {
		// List of all sons from Father which are also sons from Mother
		ArrayList<Person> daughters = new ArrayList<Person>();

		// Wenn ein Elternteil fehlt, wird leeres ArrayList zurückgegeben
		if (this.getFather() == null || this.getMother() == null)
			return daughters;

		ArrayList<Person> daughtersOfFather = this.getFather().getDaughters();
		ArrayList<Person> daughtersOfMother = this.getMother().getDaughters();

		// Fasse alle Töchter von Vater zusammen, welche auch Tochter von Mutter sind
		daughtersOfFather.forEach((daughter) -> {
			if (daughtersOfMother.contains(daughter))
				daughters.add(daughter);
		});

		// Ich glaube nicht dass man seine eigene Schwester ist, oder?
		daughters.remove(this);

		return daughters;
	}

	public ArrayList<Person> getDescendantsPlusSelf() {
		// Bekomme alle Kinder rekursiv
		if (children.isEmpty()) {
			ArrayList<Person> ret = new ArrayList<Person>();
			ret.add(this);
			return ret;
		}
		ArrayList<Person> descendantsDuplicated = new ArrayList<Person>();
		children.forEach((child) -> {
			descendantsDuplicated.addAll(child.getDescendantsPlusSelf());
		});

		ArrayList<Person> descendants = new ArrayList<Person>();

		// Remove Duplicates
		descendantsDuplicated.forEach((descendant) -> {
			if (!descendants.contains(descendant))
				descendants.add(descendant);
		});
		descendants.add(this);
		return descendants;
	}

	public ArrayList<Person> getDescendants() {
		ArrayList<Person> descendants = this.getDescendantsPlusSelf();
		descendants.remove(this);
		return descendants;
	}

	public boolean equals(Person pers1) throws IllegalArgumentException {
		if (pers1 == null)
			throw new IllegalArgumentException();
		if (!(pers1 instanceof Person))
			return false;
		/*
		 * if (this.gender == pers1.gender && this.name == pers1.name && (this.mother !=
		 * null && pers1.mother != null) && this.mother.equals(pers1.mother) &&
		 * (this.father != null && pers1.father != null) &&
		 * this.father.equals(pers1.father) && (this.children != null && pers1.children
		 * != null) && this.children.equals(pers1.children)) return true; else return
		 * false;
		 */
		if (this.gender == pers1.gender && this.name == pers1.name && this.mother == pers1.mother
				&& this.father == pers1.father)
			return true;
		else
			return false;
	}
	
	public String toString() {
		return this.name;
	}
}
