package net.tfobz.test;

import java.util.ArrayList;
import java.util.List;

public class StreamIntList {
	public static void main(String[] args) {
		List<Integer> intList = new ArrayList<>();
		intList.add(2);
		intList.add(4);
		intList.add(6);
		intList.stream().map((item)->{return item*2;}).forEach(System.out::println);
//		intList.stream().forEach(System.out::println);
	}
}
