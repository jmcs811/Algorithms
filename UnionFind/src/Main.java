
public class Main {

    public static void main(String[] args) {
        int n = StdIn.readInt();
        WeightedQuickUnion union = new WeightedQuickUnion(n);

        while(!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (!union.connected(p, q)) {
                union.union(p, q);
                StdOut.println(p + " " + q);
            }
        }
        StdOut.println(union.connected(10,40));
    }
}
