import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private static final double CONST_196 = 1.96;
    private final int numOfExperiments;
    private final double[] fractions;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("Both args must be greater than 0");
        }

        numOfExperiments = trials;
        fractions = new double[numOfExperiments];
        for (int i = 0; i < numOfExperiments; i++) {
            Percolation percolation = new Percolation(n);
            int openSites = 0;
            while (!percolation.percolates()) {
                int p = StdRandom.uniform(1, n + 1);
                int q = StdRandom.uniform(1, n + 1);
                if (!percolation.isOpen(p, q)) {
                    percolation.open(p, q);
                    openSites++;
                }
            }
            double test = (double) openSites / (n * n);
            fractions[i] = test;
        }
    }

    public double mean() {
        return StdStats.mean(fractions);
    }

    public double stddev() {
        return StdStats.stddev(fractions);
    }

    public double confidenceLo() {
        return mean() - (CONST_196*(stddev()) / Math.sqrt(numOfExperiments));
    }

    public double confidenceHi() {
        return mean() + (CONST_196*(stddev()) / Math.sqrt(numOfExperiments));
    }

    public static void main(String[] args) {
        PercolationStats stats = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        StdOut.println("mean                    = " + stats.mean());
        StdOut.println("stddev                  = " + stats.stddev());
        StdOut.println("95% confidence interval = [" + stats.confidenceLo() + ", " + stats.confidenceHi() + "]");
    }
}
