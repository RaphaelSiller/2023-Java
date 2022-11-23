package net.tfobz.dynamicTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.function.Executable;

public class Beispiel_DynamicTests {
	
	
	public static ArrayList<Integer> intList = new ArrayList<>();
	public static DynamicTest test1 = DynamicTest.dynamicTest("test1", () -> assertEquals(4, Math.decrementExact(5)));
	public static Executable exe = new Executable() {
		
		@Override
		public void execute() throws Throwable { 
			assertTrue(true);
		}
	};
	public static DynamicTest test2 = DynamicTest.dynamicTest("test2", exe);
	public static ArrayList<DynamicTest> dynamicList = new ArrayList<>();
	
	@BeforeAll
	public static void setup() {
		for(int i =0; i <50;i++) {
			intList.add(i);
		}
		dynamicList.add(test1);
		dynamicList.add(test2);
	}
	
	/*
	@BeforeEach
	public static void setupEach() {
		intList.add(90);
	}
	*/
	/*
	@TestFactory
	Collection<DynamicTest> ausfuehrungVonDynamischenTests() {
		return dynamicList;
	}
	
	@TestFactory
	Collection<DynamicTest> dynamicTestsWithCollection() {
	    return Arrays.asList(
	      DynamicTest.dynamicTest("Add test",() -> assertEquals(2, Math.addExact(1, 1))),
	      DynamicTest.dynamicTest("Multiply Test",() -> assertEquals(4, Math.multiplyExact(2, 2))));
	}*/
	
	@TestFactory
	Stream<DynamicTest> mehrereDynamischeTests(){
		return intList.stream()
					.map((element)->DynamicTest.dynamicTest("test mit "+element, ()->assertTrue(element%3==0)));
	}
	/*
	//Kein Functional Interface - sondern eine Instanz die in der Lage ist jedes Element abzuarbeiten
	public Iterator<Integer> inputGenerator = intList.iterator();
	//Functional Interface - uses input to generate output // the output is used for the displayName of the Test
	public Function<Integer,String> displayNameGenerator = (n)->"Generic Test with value: ".concat(String.valueOf(n));
	//Consumer Interface that is able to throw exceptions
	public ThrowingConsumer<Integer> testExecutor = (n)-> assertTrue(n%3==0); 
	
	@TestFactory
	Stream<DynamicTest> DynamischeTestsGenerieren(){
		return DynamicTest.stream(inputGenerator, displayNameGenerator, testExecutor);
	}*/

}