import java.util.Arrays;
import java.util.Set;

public class Experiment {
	// doesn't print, only returns values

	private final int m;
	private final double sigma;
	private final double estimation;

	// These arrays specify the bounds within the estimation should be
	private final double[] oneStDev;
	private final double[] twoStDev;


	public static void main(String[] args) {

		int n = 1_000_000;
		int m = 1024;
		long seed = 42;

		Ex4 ex4 = new Ex4(n, seed);

		Experiment ex = new Experiment(m, ex4.getIntegers());
		// 100 000 different seeds
			// per seed:
			// m_1 =   16
			// m_2 =  256
			// m_3 = 1024

		System.out.println("Size (m): " + m);
		System.out.println("Seed: " + seed);
		System.out.println("Estimation: " + ex.estimation);
		System.out.println("Actual value: " + n);
		System.out.println("StDev: " + ex.sigma);

		System.out.println("1StDev range: " + Arrays.toString(ex.oneStDev));
		System.out.println("Within 1 StDev?: " + ex.oneStDev());
		System.out.println("2StDev range: " + Arrays.toString(ex.twoStDev));
		System.out.print(ex.oneStDev() ? "Within 2 StDev?: " + true : "Within 2 StDev?: " + ex.twoStDev());
	}

	public Experiment(int m, Set<Integer> distinctIntegers) {

		int n = distinctIntegers.size();
		this.m = m;
		this.sigma = sigma();
		this.oneStDev = new double[]{(n * (1 - sigma)), (n * (1 + sigma))};
		this.twoStDev = new double[]{(n * (1 - (2 * sigma))), (n * (1 + (2 * sigma)))};

		// The estimation
		Ex3 ex3 = new Ex3(m);
		for (int i : distinctIntegers) { ex3.addToM(i); }
		this.estimation = ex3.E();

	}

	public boolean oneStDev() { return estimation > oneStDev[0] && estimation < oneStDev[1]; }

	public boolean twoStDev() { return estimation > twoStDev[0] && estimation < twoStDev[1]; }

	private double sigma() { return 1.04/Math.sqrt(m);	}

	public double getSigma() { return sigma; }

	public double getLowerBound1StDev() { return oneStDev[0]; }

	public double getUpperBound1StDev() { return oneStDev[1]; }

	public double getLowerBound2StDev() { return twoStDev[0]; }

	public double getUpperBound2StDev() { return twoStDev[1]; }

	public double getEstimation() { return estimation; }

	public int getM() { return m; }

}
