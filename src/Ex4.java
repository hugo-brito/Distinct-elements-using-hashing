import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Ex4 {
	/*
Exercise 4. Write an input generator that takes as input an integer n and a seed seed on
standard input and outputs a list of n random distinct  32-bit integers from a random integer
generator using seed seed.

Describe and run an experiment that gives a graphical representation of the
connection of m and the estimation error. A recommended representation is a histogram plot
over the distinct element count reported by the algorithm. Try at least 3 different values of m.

Report in a table for each m and n the fraction of runs that reported a value in n(1 +- theta) and
n(1 +- 2sigma) for sigma = 1.04/sqrt(m)
	 */

	private final int n;
	private final int a;
	private final int b;

	private final Set<Integer> integers;
	private final Random random;

	private int max;
	private int min;

	public static void main(String[] args) {

		// args: n seed a b
		int n = Integer.parseInt(args[0]);

		long seed = Long.parseLong(args[1]);

//		// Lower bound
//		int a = Integer.parseInt(args[2]);
//
//		// Upper bound
//		int b = Integer.parseInt(args[3]);

		Ex4 ex4 = new Ex4(n, seed);

//		ex4.reportMinMax();

		for (Integer i : ex4.getIntegers()) System.out.println(i);

	}

	public Ex4(int n, long seed, int a, int b) {

		if (n > Integer.MAX_VALUE) throw new InstantiationError(n + " is too large.\nn must be smaller that Integer.MAX_VALUE");

		this.n = n;
		this.a = a;
		this.b = b;

		integers = new HashSet<>();
		max = Integer.MIN_VALUE;
		min = Integer.MAX_VALUE;

		random = new Random(seed);

		generate();

	}

	public Ex4(int n, int a, int b) { this(n, 42, a, b); }

	public Ex4(int n) { this(n, 42, Integer.MIN_VALUE/2, (Integer.MAX_VALUE/2) - 1); }

	public Ex4(int n, long seed) { this(n, seed, Integer.MIN_VALUE/2, (Integer.MAX_VALUE/2) - 1); }

	private void generate() {

		do {
			int i = a + random.nextInt(-a + b + 1);
			if (i != 0) { // zero cannot be used because it has no leading zeros and cannot be hashed
				integers.add(i);
				if (i > max) max = i;
				if (i < min) min = i;
			}			
		} while (integers.size() < n);
	}

	public Set<Integer> getIntegers() { return integers; }

	@Override
	public String toString() { return integers.toString(); }

	public void print() {
		System.out.println(toString());
		reportMinMax();
	}

	public void reportMinMax() {
		System.out.println("Min value: " + min + " (" + a + ")" + "\nMax value: " + max + " (" + b + ")");
	}

}
