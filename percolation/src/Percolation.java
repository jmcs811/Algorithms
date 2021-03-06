import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final int n;
    private final WeightedQuickUnionUF union;
    private byte[][] grid;
    private int num;

    // init grid.
    public Percolation(int n) {
        // n must be greater than zero
        if (n <= 0) {
            throw new IllegalArgumentException("Invalid Input: n");
        }

        this.n = n + 1;

        // create a new union obj with size of n*n (2d array)
        union = new WeightedQuickUnionUF(this.n * this.n);

        // 2d byte array to hold open sites
        grid = new byte[this.n][this.n];
    }

    // ensure that input is in valid range
    private void validate(int row, int col) {
        if (row < 0 || row > n) {
            throw new IllegalArgumentException("Invalid Input: row");
        }
        if (col < 0 || col > n) {
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
        aboveCell(row, col);

        // below cell
        belowCell(row, col);

        // right cell
        rightCell(row, col);

        // left cell
        leftCell(row, col);
    }

    private void leftCell(int row, int col) {
        if (col - 1 > 0 && isOpen(row, col-1)) {
            update(row, col-1, row, col);
        }
    }

    private void rightCell(int row, int col) {
        if (col + 1 < n && isOpen(row, col + 1)) {
            update(row, col + 1, row, col);
        }
    }

    private void belowCell(int row, int col) {
        if (row + 1 < n && isOpen(row + 1, col)) {
            update(row + 1, col, row, col);
        }
    }

    private void aboveCell(int row, int col) {
        if (row - 1 > 0 && isOpen(row - 1, col)) {
            update(row - 1, col, row, col);
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

    public boolean isOpen(int row, int col) {
        validate(row, col);
        return grid[row][col] > 0;
    }

    public boolean isFull(int row, int col) {
        validate(row, col);
        return grid[row][col] > 0 && union.connected(0, row * n + col);
    }

    public int numberOfOpenSites() {
        return num;
    }

    public boolean percolates() {
        int root = union.find(0);
        return grid[root / n][root % n] == 2;
    }

//    private void printGrid() {
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                StdOut.print(grid[i][j]);
//            }
//            StdOut.println();
//        }
//    }

//    public static void main(String[] args) {
//        int number = StdIn.readInt();
//        Percolation percolation = new Percolation(number);
//        boolean isPercolated = false;
//        int count = 0;
//
//        while (!StdIn.isEmpty()) {
//            int row = StdIn.readInt();
//            int col = StdIn.readInt();
//
//            if (!percolation.isOpen(row, col)) {
//                count++;
//            }
//
//            percolation.open(row, col);
//            isPercolated = percolation.percolates();
//            if (isPercolated) {
//                break;
//            }
//        }
//
//        StdOut.println(count + " open sites");
//        if (isPercolated) {
//            StdOut.println("percolates");
//        } else {
//            StdOut.println("no percolates");
//        }
//        //percolation.printGrid();
//    }
}
