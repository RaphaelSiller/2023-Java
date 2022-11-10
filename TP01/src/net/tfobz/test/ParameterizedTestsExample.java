package net.tfobz.test;




import java.util.Arrays;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import static org.junit.Assert.assertEquals;

public class ParameterizedTestsExample {
	
	@Parameters
	public static Iterable<Object[]> data() {
		return Arrays.asList(new Object[][] {
			{0,1,1},
			{1,1,2},
			{3,2,5},
			{4,3,7},
		});
	}
	@Parameter(0)
	public int firstSummand;
	@Parameter(1)
	public int secondSummand;
	@Parameter(2)
	public int result;
	
//	public ParameterizedTestsExample(int a, int b, int c) {
//		firstSummand= a;
//		secondSummand = b;
//		result = c;
//	}
	
	@Test
	@DisplayName("einfacher Vergleich")
	public void Test123() {
		System.out.println(result + "\t" + firstSummand+ "\t" + secondSummand);
		assertEquals(result, firstSummand + secondSummand);
	}
}
