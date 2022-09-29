package net.tfobz.relationship;

import java.util.ArrayList;

public class Person
{
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

	public void setMother(Person mother) throws IllegalArgumentException {
		if (mother != null && mother.gender != Gender.FEMALE)
			throw new IllegalArgumentException();
		
		//Entfernen des Kindes bei alter Mutter
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

	public void setFather(Person father) throws IllegalArgumentException {
		if (father != null && father.gender != Gender.MALE)
			throw new IllegalArgumentException();

		// Entfernen des Kindes bei altem Vater
		if (this.father != null)
			this.father.children.remove(this);

		this.father = father;

		// Hinzufügen des Kindes bei neuem Vater
		if (this.father != null)
			this.father.children.add(this);
	}

	public ArrayList<Person> getChildren() {
		return children;
	}
	
	public ArrayList<Person> getSons() {
		ArrayList<Person> sons = new ArrayList<Person>();
		children.forEach(child -> {
			if(child.getGender() == Gender.MALE)
				sons.add(child);
		});
		return sons;
	}
	
	public ArrayList<Person> getDaughters() {
		ArrayList<Person> daughters = new ArrayList<Person>();
		children.forEach(child -> {
			if(child.getGender() == Gender.MALE)
				daughters.add(child);
		});
		return daughters;
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
}
