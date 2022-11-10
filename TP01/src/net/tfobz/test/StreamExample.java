package net.tfobz.test;

import java.util.ArrayList;

public class StreamExample {
	public static void main(String[] args) {
		ArrayList<String> liste = new ArrayList<>();
		liste.add("Item1");
		liste.add("Item2");
		liste.add("Item3");
		
//		liste.stream().map(item->{
//			System.out.println(item); 
//			return item.toUpperCase();
//		});
		
		//oder
		
		liste.stream().map(String::toUpperCase).forEach(System.out::println);
	}
}
