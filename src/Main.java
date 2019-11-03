import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Main {

	public static void main(String[] args) {

		// the real number of distinct integers
		int n = 1_000_000;

		// the number of different
		int t = 100_000;

		// the seed used to generate the random used to generate the sets of integers, for reproducibility
		long seed = 42;

		// the random used to generate the seed to be used in the integer generator
		Random rand = new Random(seed);

		Set<Long> usedSeeds = new HashSet<>();

		printHeader();

		for (int i = 1; i < t; i++) {

			// each iteration uses a different seed to generate distinct integers
			long intGeneratorSeed = rand.nextLong();

			// avoid repeated seeds and therefore repeated sets of integers
			while (usedSeeds.contains(intGeneratorSeed)) { intGeneratorSeed = rand.nextLong(); }

			usedSeeds.add(intGeneratorSeed);

			Ex4 ex4 = new Ex4(n, intGeneratorSeed);

			Experiment m16 = new Experiment(16, ex4.getIntegers());
			reportLine(i, 16, n, m16);

			Experiment m256 = new Experiment(256, ex4.getIntegers());
			reportLine(i, 256, n, m256);

			Experiment m1024 = new Experiment(1024, ex4.getIntegers());
			reportLine(i, 1024, n, m1024);

		}

	}

	private static void printHeader() {
		System.out.println("Iteration (t),m,Real number of distinct Integers (n),Estimation,Within 1 StDev range?," +
				"Within 2 StDev range?,Sigma,1 StDev lower bound,1 StDev upper bound,2 StDev lower bound,2 StDev upper bound");
	}

	private static void reportLine(int iteration, int m, int n, Experiment experiment) {
		System.out.println(iteration + "," + m + "," + n + "," + experiment.getEstimation() + "," + experiment.oneStDev() + "," +
				experiment.twoStDev() + "," + experiment.getSigma() + "," + experiment.getLowerBound1StDev() + "," +
				experiment.getUpperBound1StDev() + "," + experiment.getLowerBound2StDev() + "," + experiment.getUpperBound2StDev()
		);
	}

}
