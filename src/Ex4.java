import java.util.Random;

public class Ex4 {
	/*
Exercise 4. Write an input generator that takes as input an integer n and a seed seed on
standard input and outputs a list of n random 32-bit integers from a random integer generator
using seed seed.

Describe and run an experiment that gives a graphical representation of the
connection of m and the estimation error. A recommended representation is a histogram plot
over the distinct element count reported by the algorithm. Try at least 3 different values of m.

Report in a table for each m and n the fraction of runs that reported a value in n(1 +- theta) and
n(1 +- 2theta) for theta = 1.04/sqrt(m)
	 */

	/*
	 * Generates a file where to be used as a input design of experiments exercise
	 */
	private int[] numbers;

	public static void main(String[] args) {
		// args: n seed a b
		int n = Integer.parseInt(args[0]);
		long seed = Long.parseLong(args[1]);

		// Lower bound
		int a = Integer.parseInt(args[2]);

		// Upper bound
		int b = Integer.parseInt(args[3]);

		System.out.println(-Integer.MIN_VALUE+Integer.MAX_VALUE+1);
	}

	public Ex4(int n, long seed, int a, int b) {
		numbers = new int[n];

		Random random = new Random(seed);

		for (int i = 0; i < n; i++) numbers[i] = a + random.nextInt(-a + b + 1);

		for (int i = 0; i < (n - 1); i++) {
			int num = a + random.nextInt(-a + b + 1);
			// Returns a pseudorandom, uniformly distributed int value between 0 (inclusive)
			// and the specified value
			// (exclusive), drawn from this random number generator's sequence.
			System.out.print(num + " ");
		}
		System.out.print(a + random.nextInt(-a + b + 1));
	}

	public Ex4(int n, long seed) { this(n, seed, Integer.MIN_VALUE, Integer.MAX_VALUE); }

	public Ex4(int n, int a, int b) { this(n, 42, a, b); }

	public Ex4(int n) { this(n, 42, Integer.MIN_VALUE, Integer.MAX_VALUE); }

	public int[] getNumbers() { return numbers; }

	public void print() {
		for (int i = 0; i < (numbers.length - 1); i++) {
			System.out.println(numbers[i] + " ");
		}
		System.out.print(numbers[numbers.length - 1]);
	}
}
