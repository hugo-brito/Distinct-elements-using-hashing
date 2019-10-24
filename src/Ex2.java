import java.util.InputMismatchException;

public class Ex2 {
	public static int rho (int x) {
		if (x == 0) throw new InputMismatchException("Zero has no leading zeroes.");
		return Integer.numberOfLeadingZeros(x) + 1;
	}
}
