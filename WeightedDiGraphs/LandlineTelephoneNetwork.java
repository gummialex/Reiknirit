import java.util.Arrays;

import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.KruskalMST;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.Iterables;

public class LandlineTelephoneNetwork {

    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);

        int n = io.getInt(); // The no. of buildings
        int m = io.getInt(); // The no. of possible connections between a pair of buildings
        int p = io.getInt(); // The no. of insecure buildings

        EdgeWeightedGraph city = new EdgeWeightedGraph(n);

        boolean[] insecureB = new boolean[n];
        for (int j = 0; j < p + 1; j++) {
            insecureB[io.getInt() - 1] = true;
        }

        int[] cheapest = new int[p];
        Arrays.fill(cheapest, Integer.MAX_VALUE);

        int secureCount = 0;
        for (int i = 0; i < m; i++) {
            int x = io.getInt(); // x and y are distinct building the line connects
            int y = io.getInt();
            int l = io.getInt(); // l is the est. cost of connecting those buildings

            if (!insecureB[x] && !insecureB[y]) {
                city.addEdge(new Edge(x, y, l));
                secureCount += 1;
            }

            else if (insecureB[x] && !insecureB[y]) {
                cheapest[x] = Math.min(cheapest[x], l);
            }

            else if (insecureB[y] && !insecureB[x]) {
                cheapest[y] = Math.min(cheapest[y], l);
            }

        }
        KruskalMST mst = new KruskalMST(city);
        double totalW = mst.weight();

        int insecureCheapesSize = 0;
        for (int i = 0; i < p; i++) {
            insecureCheapesSize += 1;
        }

        int mstSize = 0;
        for (Edge e : mst.edges()) {
            mstSize += 1;
        }
        if (secureCount - 1 != mstSize) {
            io.println("impossible");
            io.flush();
        } else {
            io.println(totalW + insecureCheapesSize);
        }
        io.close();

    }
}
