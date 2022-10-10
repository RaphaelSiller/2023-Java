package net.tfobz.relationship;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class PersonList extends ArrayList<Person>
{
	public PersonList() {
	}
	
	public PersonList(BufferedReader reader) throws IOException, IllegalArgumentException {
		readPersons(reader);
	}
	
	public PersonList(String filename) throws FileNotFoundException, IOException, IllegalArgumentException {
		FileInputStream in = new FileInputStream(filename);
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		readPersons(reader);
	}
	
	private void readPersons(BufferedReader reader) throws IOException, IllegalArgumentException{
		//TODO
		String readPerson;
		while((readPerson = reader.readLine()) != null) {
			Person newPerson = new Person();
			
			
		}
	}

}
