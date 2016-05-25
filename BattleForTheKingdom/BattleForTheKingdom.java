/*******************************************************************************
 * Nome: Ângelo Gregório Lovatto
 * Número USP: 9293435
 *
 * Compilação: javac-algs4 BattleForTheKingdom.java
 * Execução:   java-algs4 BattleForTheKingdom < in.txt
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.*;
import java.util.HashMap;

public class BattleForTheKingdom {
    public static void main(String[] args) {
        int N = StdIn.readInt();
        int M = StdIn.readInt();
        int K = StdIn.readInt();
        HashMap<String, Integer> vertex = new HashMap<String, Integer>();
        HashMap<Integer, String> city = new HashMap<Integer, String>();

        int num = 0;
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(N);
        for (int i = 0; i < M; i++) {
            String A = StdIn.readString();
            String B = StdIn.readString();
            int T = StdIn.readInt();

            if (!vertex.containsKey(A)) {
                vertex.put(A, num);
                city.put(num++, A);
            }
            if (!vertex.containsKey(B)) {
                vertex.put(B, num);
                city.put(num++, B);
            }
            
            int v = vertex.get(A);
            int w = vertex.get(B);
            DirectedEdge e = new DirectedEdge(v, w, (double)T);
            G.addEdge(e);
        }

        double[] times = new double[N];
        for (int i = 0; i < K; i++) {
            String word  = StdIn.readString();
            int s = vertex.get(word);
            DijkstraSP sp = new DijkstraSP(G, s);
            for (int j = 0; j < N; j++)
                if (sp.distTo(j) > times[j]) times[j] = sp.distTo(j);
        }
        
        String word = StdIn.readString();
        int init = vertex.get(word);
        DijkstraSP sorcerer = new DijkstraSP(G, init);
        Bag<String> safe = new Bag<String>();
        for (int i = 0; i < N; i++) {
            if (times[i] < sorcerer.distTo(i))
                safe.add(city.get(i));
        }

        if (safe.isEmpty())
            StdOut.println("DEMISE OF THE KINGDOM");
        else {
            StdOut.println("VICTORY AND HAPPY EVER AFTER");
            StdOut.println(safe.size());
            for (String location : safe)
                StdOut.print(location + " ");
            StdOut.println();
        }
            
    }
}
