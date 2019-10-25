import java.util.InputMismatchException;
import java.util.Scanner;

public class Ex3 {

	private static final int[] A = {
	// Needed for the hashing function
			0x21ae4036,
			0x32435171,
			0xac3338cf,
			0xea97b40c,
			0x0e504b22,
			0x9ff9a4ef,
			0x111d014d,
			0x934f3787,
			0x6cd079bf,
			0x69db5c31,
			0xdf3c28ed,
			0x40daf2ad,
			0x82a5891c,
			0x4659c7b0,
			0x73dc0ca8,
			0xdad3aca2,
			0x00c74c7e,
			0x9a2521e2,
			0xf38eb6aa,
			0x64711ab6,
			0x5823150a,
			0xd13a3a9a,
			0x30a5aa04,
			0x0fb9a1da,
			0xef785119,
			0xc9f0b067,
			0x1e7dde42,
			0xdda4a7b2,
			0x1a1c2640,
			0x297c0633,
			0x744edb48,
			0x19adce93};

	// m = the number of buckets = size of M
	private final int m;
	private int M[];

	// V = number of empty buckets in M
	private int V;

	// Z = the harmonic mean
	private double Z;

	Ex3 (int m) {
		if (!isPowerOfTwo(m)) throw new InputMismatchException("m: " + m + "\nThe provided m is not a power of 2!");
		this.m = m;
		// the size of m is a parameter of the algorithm
		// the larger the more precise

		this.V = m; // # of empty buckets

		//	for i:=0 to m-1 do M[i]:=0
		this.M = new int[m];
	}

	Ex3 () {
		this(1024);
	}

	private static int h (int x) {
	// The hashing function
		int res = 0;
		for (int i = 0; i < A.length; i++) res += (Integer.bitCount(A[i] & x) % 2) << (31-i);
		//                                                 ^^^^^^^^^^^^^^^^^^^^          ^^^^
		// how many bits do I have when multiply the given number by a given row in A     ^^
		//                                                          put the resulting bit the right position
		return res;
	}

	private static int rho (int x) {
		if (x == 0) throw new InputMismatchException("Zero has no leading zeroes.");
		return Integer.numberOfLeadingZeros(x) + 1;
	}

	private int f (int y) {
		return ((y*0xbc164501) & 0x7fe00000) >> 21;
		// 21 should be a function of the size of m.
		// 1024 -> 21
	}

	public void addToM(int x) {
		// adds another integer to the matrix M

		// j := f(y[i])
		int j = f(x);

		//	V := |{i | M[i]=0}|
		if (M[j] == 0) V--;

		//	    M[j] := max(M[j],rho(h(y[i])))
		M[j] = Math.max(M[j], rho(h(x)));
	}

	private void calculateZ () {
		// Calculates the Harmonic Mean and saves it to the local field Z
		double res = 0.0;

		//	Z := 1/(2^(-M[0])+...+2^(-M[m-1]))
		for (int i = 0; i < m; i++) res = res + Math.pow(2.0,-M[i]);
		Z = 1.0/res;
	}

	private int naiveV () {
		// Naive calculation # of empty buckets
		// Not really used.
		int V = 0;
		for (int i = 0; i < m; i++) if (M[i] == 0) V++;
		return V;
	}

	public double E () {
		// Estimation of the number of distinct elements
		calculateZ();

		//	E := m*m*Z*0.7213/(1 + 1.079/m)
		double E = (m * m * Z * 0.7213)/(1.0 + (1.079/m));

		//		if (E < 2.5*m and V > 0) then E:= m * ln(m/V)
		if ((E < (2.5 * m)) && (V > 0)) E = ((double) m) * Math.log(((double)m)/((double)V));

		return E;
	}

	static boolean isPowerOfTwo (int n) {
		if (n==0) return false;
		return (int)(Math.ceil((Math.log(n) / Math.log(2)))) == (int)(Math.floor(((Math.log(n) / Math.log(2)))));
	}

	public static void main(String[] args) {
		Ex3 ex3 = new Ex3();

		Scanner sc = new Scanner(System.in);
		int threshold = sc.nextInt();
		while (sc.hasNext()) {
			ex3.addToM(sc.nextInt());
		}

		System.err.println(ex3.V == ex3.naiveV());
		double E = ex3.E();
		System.err.println(E);

		System.out.println(E > threshold ? "above" : "below");

	}
}
