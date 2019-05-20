public class QuickFind {
    private int[] id;

    // linear
    private QuickFind(int n) {
        id = new int[n];
        for (int i = 0; i < id.length; i++) {
            id[i] = i;
        }
    }

    // constant
    private int find(int p) {
        return id[p];
    }

    // constant time
    private boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    // quadratic
    private void union(int p, int q) {
        int pid = id[p];
        int qid = id[q];

        for(int i = 0; i < id.length; i++) {
            if (id[i] == pid) {
                id[i] = qid;
            }
        }
    }


    public static void main(String[] args) {
        int n = StdIn.readInt();
        QuickFind quickFind = new QuickFind(n);

        while(!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (!quickFind.connected(p, q)) {
                quickFind.union(p, q);
                StdOut.println(p + " " + q);
            }
        }
        StdOut.println(quickFind.connected(0,1));
    }
}
