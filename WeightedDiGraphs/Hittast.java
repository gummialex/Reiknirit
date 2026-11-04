import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.DijkstraUndirectedSP;

public class Hittast {
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);

        int n = io.getInt(); // No. of vertacies (locations)
        int m = io.getInt(); // No. of edges between each v. (no. of travel options betw. each loc.)

        EdgeWeightedGraph netA = new EdgeWeightedGraph(n); // Graph for Alfur
        EdgeWeightedGraph netB = new EdgeWeightedGraph(n); // Graph for Benni

        int[] lodgingCost = new int[n]; // array to hold onto the lodging cost at each location

        for (int i = 0; i < n; i++) {
            lodgingCost[i] = io.getInt(); // The cost of each location (vertex)
        }

        for (int i = 0; i < m; i++) {
            int u = io.getInt() - 1; // You can travel from u to v in both directions
            int v = io.getInt() - 1;

            int a = io.getInt(); // The cost for Alfur
            int b = io.getInt(); // The cost for Benni

            netA.addEdge(new Edge(u, v, a)); // Adding edges to respective graph with corr. weights
            netB.addEdge(new Edge(u, v, b));

        }

        DijkstraUndirectedSP distA = new DijkstraUndirectedSP(netA, 0);
        DijkstraUndirectedSP distB = new DijkstraUndirectedSP(netB, n - 1);

        long bestDist = Long.MAX_VALUE;
        for (int d = 0; d < n; d++) {
            long travelA = (long) distA.distTo(d);
            long travelB = (long) distB.distTo(d);

            long total = travelA + travelB + lodgingCost[d];

            if (total < bestDist) {
                bestDist = total;
            }
        }
        io.println(bestDist);
        io.close();
    }
}