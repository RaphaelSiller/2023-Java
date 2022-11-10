package net.tfobz.test;

import java.util.ArrayList;
import java.util.function.Consumer;

public class Lambda {

	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<>();
		list.add("Lorem");
		list.add("Ipsum");
		list.add("Dolor");
		Consumer<String> c = s -> {
			s += "!";
			System.out.println(s);
		};
		list.forEach(c);
		System.out.println("Nach Lambda: " + list.get(0));
		
		System.out.print("\n\n\n");
		
		//Mit Interface
		TestInterface t = new TestInterface() {
			@Override
			public String makeItAggro(String a, int n) {
				a = a.toUpperCase();
				for (int i = 0; i < n; i++)
					a = a.concat("!");
				return a;
			}
		};
		testMakeItAggro(t);
		System.out.println(t.makeItAggro("Tschüss Welt", 5));
		//Mit Lambda
		t = (a, n) -> {
			a = a.toUpperCase();
			for (int i = 0; i < n; i++)
				a = a.concat("!");
			return a;
		};
		testMakeItAggro(t);
		System.out.println(t.makeItAggro("Tschüss Welt", 5));
	}

	public static void testMakeItAggro(TestInterface t) {
		System.out.println(t.makeItAggro("Hallo Welt", 3));
	}

}