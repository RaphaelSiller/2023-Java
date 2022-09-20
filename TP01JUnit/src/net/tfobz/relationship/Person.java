package net.tfobz.relationship;

import java.util.ArrayList;

public class Person
{
	public enum Gender { FEMALE, MALE };
	
	protected Gender gender = null;
	protected String name = null;
	protected Person mother = null;
	protected Person father = null;
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected ArrayList<Person> children = new ArrayList();

	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Person getMother() {
		return mother;
	}
	public void setMother(Person mother) {
		this.mother = mother;
	}
	
	public Person getFather() {
		return father;
	}
	public void setFather(Person father) {
		this.father = father;
	}
	
	public ArrayList<Person> getChildren() {
		return children;
	}
}
