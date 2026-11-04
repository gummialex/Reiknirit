import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.DijkstraUndirectedSP;

public class LandlineTelephoneNetwork {

    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);

        int n = io.getInt(); // The no. of buildings
        int m = io.getInt(); // The no. of possible connections between a pair of buildings
        int p = io.getInt(); // The no. of insecure buildings

        for (int i = 0; i < m; i++) {
            int x = io.getInt(); // x and y are distinct building the line connects
            int y = io.getInt();
            int l = io.getInt(); // l is the est. cost of connecting those buildings
        }

    }
}
