import edu.princeton.cs.algs4.EdgeWeightedGraph;

public class Hittast {
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);

        int n = io.getInt(); // No. of vertacies (locations)
        int m = io.getInt(); // No. of edges between each v. (no. of travel options betw. each loc.)

        EdgeWeightedGraph net = new EdgeWeightedGraph(n, m);

        int[] lodgingCost = new int[n]; // array to hold onto the lodging cost at each location

        for (int i = 0; i < n; i++) {
            lodgingCost[i] = io.getInt(); // The cost of each location (vertex)
        }

        for (int i = 0; i < m; i++) {
            int u = io.getInt(); // making edges
            int v = io.getInt();

        }
    }
}