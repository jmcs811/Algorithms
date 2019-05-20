import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Percolation {
    private int n;
    private WeightedQuickUnion union;
    private byte[][] grid;
    private int num;

    // init grid.
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Invalid Input: n");
        }

        this.n = n;
        union = new WeightedQuickUnion(this.n * this.n);
        grid = new byte[this.n][this.n];
    }

    // ensure that input is in valid range
    private void validate(int row, int col) {
        if (row < 0 || row >= n) {
            throw new IllegalArgumentException("Invalid Input: row");
        }
        if (col < 0 || col >= n) {
            throw new IllegalArgumentException("Invalid Input: col");
        }
    }

    public void open(int row, int col) {
        // check input range
        validate(row, col);

        // check if open
        if (isOpen(row, col)) { return; }

        grid[row][col] = 1;
        num++;

        if (row == n - 1) {
            grid[row][col] = 2;
        }

        if (row == 1) {
            union.union(0, row*n+col);

            if (grid[row][col] == 2) {
                grid[0][0] = 2;
            }
        }

        // above cell
        if (row - 1 > 0 && isOpen(row - 1, col)) {
            update(row - 1, col, row, col);
        }

        // below cell
        if (row + 1 < n && isOpen(row + 1, col)) {
            update(row + 1, col , row, col);
        }

        // right cell
        if (col + 1 < n && isOpen(row, col + 1)) {
            update(row, col + 1, row, col);
        }

        // left cell
        if (col - 1 > 0 && isOpen(row, col-1)) {
            update(row, col-1, row, col);
        }
    }

    private void update(int row1, int col1, int row2, int col2) {
        int p = union.find(row1 * n + col1);
        int q = union.find((row2 * n + col2));
        union.union(row1 *n + col1, row2 *n + col2);

        if (grid[p / n][p % n] == 2 || grid[q / n][q % n] == 2) {
            int t = union.find(row2 * n + col2);
            grid[t / n][t % n] = 2;
        }
    }

    private boolean isOpen(int row, int col) {
        validate(row, col);
        return grid[row][col] > 0;
    }

    public boolean isFull(int row, int col) {
        validate(row, col);
        return grid[row][col] > 0 && union.connected(0, row * n + col);
    }

    public int numberOfOpenSites() {
        return num - 1;
    }

    private boolean percolates() {
        int root = union.find(0);
        return grid[root / n][root % n] == 2;
    }


    public static void main(String[] args) {
        int number = StdIn.readInt();
        Percolation percolation = new Percolation(number);
        boolean isPercolated = false;
        int count = 0;

        while (!StdIn.isEmpty()) {
            int row = StdIn.readInt();
            int col = StdIn.readInt();

            if (!percolation.isOpen(row, col)) {
                count++;
            }

            percolation.open(row, col);
            isPercolated = percolation.percolates();
            if (isPercolated) {
                break;
            }
        }

        StdOut.println(count + " open sites");
        if (isPercolated) {
            StdOut.println("percolates");
        } else {
            StdOut.println("no percolates");
        }

    }
}
