import java.util.Arrays;

import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.KruskalMST;
import edu.princeton.cs.algs4.EdgeWeightedGraph;

public class LandlineTelephoneNetwork {

    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);

        int n = io.getInt(); // The no. of buildings
        int m = io.getInt(); // The no. of possible connections between a pair of buildings
        int p = io.getInt(); // The no. of insecure buildings

        EdgeWeightedGraph city = new EdgeWeightedGraph(n);

        boolean[] insecureB = new boolean[n];
        for (int j = 0; j < p; j++) {
            insecureB[io.getInt() - 1] = true;
        }

        int[] cheapest = new int[n];
        Arrays.fill(cheapest, Integer.MAX_VALUE);

        int bothInsecure = Integer.MAX_VALUE;

        for (int i = 0; i < m; i++) {
            int x = io.getInt() - 1; // x and y are distinct building the line connects
            int y = io.getInt() - 1;
            int l = io.getInt(); // l is the est. cost of connecting those buildings

            if (!insecureB[x] && !insecureB[y]) {
                city.addEdge(new Edge(x, y, l));
            }

            else if (insecureB[x] && !insecureB[y]) {
                cheapest[x] = Math.min(cheapest[x], l);
            }

            else if (insecureB[y] && !insecureB[x]) {
                cheapest[y] = Math.min(cheapest[y], l);
            }

            else {
                bothInsecure = Math.min(bothInsecure, l);
            }

        }

        if (p == n) {
            if (n == 1) {
                io.println(0);
            } else if (n == 2 && bothInsecure != Integer.MAX_VALUE) {
                io.println(bothInsecure);
            } else {
                io.println("impossible");
            }
            io.close();
            return;
        }

        KruskalMST mst = new KruskalMST(city);

        boolean impossible = false;
        long insecureCost = 0;
        for (int b = 0; b < n; b++) {
            if (!insecureB[b]) {
                continue;
            }
            if (cheapest[b] == Integer.MAX_VALUE) {
                impossible = true;
            } else {
                insecureCost += cheapest[b];
            }
        }

        int mstSize = 0;
        for (Edge e : mst.edges()) {
            mstSize += 1;
        }

        if (mstSize != (n - p) - 1) { // If the edges in the mst are not the same as all secure vertacies -1
            impossible = true;
        }

        if (impossible) {
            io.println("impossible");
        } else {
            long total = Math.round(mst.weight() + insecureCost);
            io.println(total);
        }
        io.close();

    }
}
