
public class FinallyTest {
	public static void main(String[] args) {
		int i = 4;
		try {
			if (i == 2)
				return;
			else if (i == 3)
				throw new NullPointerException();
			else if (i == 4)
				throw new RuntimeException();
		} catch (NullPointerException e) {
			System.out.println("catch");
		} finally {
			System.out.println("finally");
		}
		System.out.println("done");
	}
}
