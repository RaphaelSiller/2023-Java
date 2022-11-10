package net.tfobz.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class ArrayButNoList {

	public static void main(String[] args) {
		// Für primitive Liste
		int[] liste = new int[20];
		for (int i = 0; i < liste.length; i++) {
			liste[i] = i;
		}
		Arrays.stream(liste);

		// Für ArrayList
		ArrayList<String> liste2 = new ArrayList<>();
		liste2.stream();
		
		// Festdefinierte Streams
		// limit
		Stream.of("Lorem", "Ipsum", "Dolor", "Sit", "Amet").limit(4).forEach(System.out::println);
		System.out.println();
		// filter
		Stream.of("Lorem", "Ipsum", "Dolor", "Sit", "Amet").filter((s) -> s.contains("o")).forEach(System.out::println);
		System.out.println();
		// peek
		Stream.of("Lorem", "Ipsum", "Dolor", "Sit", "Amet").filter((s) -> s.contains("o")).peek(System.out::println)
				.filter((s) -> s.contains("m")).forEach(System.out::println);
	}

}
