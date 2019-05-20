public class QuickUnion {
    private int[] id;

    // init array
    // linear O(n)
    private QuickUnion(int n) {
        id = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
        }
    }

    // chase the root
    // linear O(n)
    private int root(int i) {
        while(i != id[i]) {
            i = id[i];
        }
        return i;
    }

    // true if roots are equal
    // constant O(1)
    private boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    // O(n)
    private void union(int p, int q) {
        // get the roots of the args
        // set the root of p to root of q
        int i = root(p);
        int j = root(q);
        id[i] = j;
    }


    public static void main(String[] args) {
        int n = StdIn.readInt();
        QuickUnion quickUnion = new QuickUnion(n);

        while(!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (!quickUnion.connected(p, q)) {
                quickUnion.union(p, q);
                StdOut.println(p + " " + q);
            }
        }
        StdOut.println(quickUnion.connected(10,40));
    }
}
