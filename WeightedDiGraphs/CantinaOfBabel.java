import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.KosarajuSharirSCC;

public class CantinaOfBabel {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt(); // No. of characters in the Cantina

        Map<Integer, String[]> allInCantina = new HashMap<>();
        Digraph cantina = new Digraph(n);

        for (int i = 0; i < n; i++) {
            String name = scanner.next();
            String[] langs = scanner.nextLine().trim().split(" ");

            allInCantina.put(i, langs);
        }

        for (int i = 0; i < n; i++) {
            String check = allInCantina.get(i)[1];
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                String[] understoodbyJ = allInCantina.get(j);
                for (String lang : understoodbyJ) {
                    if (lang.equals(check)) {
                        cantina.addEdge(i, j);
                        break;
                    }
                }
            }
        }

        KosarajuSharirSCC scc = new KosarajuSharirSCC(cantina);

        int totalComponents = scc.count();

        Map<Integer, Integer> counteachComponent = new HashMap<>(totalComponents);

        for (int i = 0; i < totalComponents; i++) {
            counteachComponent.put(i, 0);
        }

        for (int i = 0; i < n; i++) {
            int id = scc.id(i);
            counteachComponent.put(id, counteachComponent.get(id) + 1);
        }

        int biggestId = Collections.max(counteachComponent.values());
        System.out.println(n - biggestId);
        scanner.close();

    }
}
