public class WeightedQuickUnion {
    private int[] id;
    private int[] size;

    // init array
    // linear O(n)
    public WeightedQuickUnion(int n) {
        id = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
            size[i] = 1;
        }
    }

    // chase the root
    // linear O(n)
    private int find(int i) {
        while(i != id[i]) {
            id[i] = id[id[i]]; // path compression
            i = id[i];
        }
        return i;
    }

    // true if roots are equal
    // constant O(lg n)
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    // O(lg n)
    public void union(int p, int q) {
        // get the roots of the args
        // set the root of p to root of q
        int i = find(p);
        int j = find(q);
        if (i == j) { return; }
        if (size[i] < size[j]) {
            id[i] = j;
            size[j] += size[i];
        } else {
            id[j] = i;
            size[i] += size[j];
        }
    }


    public static void main(String[] args) {
        int n = StdIn.readInt();
        WeightedQuickUnion weightedQuickUnion = new WeightedQuickUnion(n);

        while(!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (!weightedQuickUnion.connected(p, q)) {
                weightedQuickUnion.union(p, q);
                StdOut.println(p + " " + q);
            }
        }
        StdOut.println(weightedQuickUnion.connected(10,40));
    }
}
