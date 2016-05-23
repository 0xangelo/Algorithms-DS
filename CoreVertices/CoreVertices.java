/*******************************************************************************
 * Nome: Ângelo Gregório Lovatto
 * Número USP: 9293435
 *
 * Compilação: javac-algs4 CoreVertices.java
 * Execução:   java-algs4 CoreVertices < entrada.txt
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.*;

public class CoreVertices {

    public static void main(String[] args) {
        int N = StdIn.readInt();
        int M = StdIn.readInt();
        Digraph G = new Digraph(N);
        
        for (int i = 0; i < M; i++) {
            int v1 = StdIn.readInt();
            int v2 = StdIn.readInt();
            G.addEdge(v1, v2);
        }

        KosarajuSharirSCC scc = new KosarajuSharirSCC(G);

        for (int v = 0; v < scc.count(); v++) {
            DirectedDFS reach = new DirectedDFS(G, v);
            if (reach.count() == N) {
                for (int w = 0; w < N; w++) {
                    if (scc.stronglyConnected(v, w))
                        StdOut.print(w + " ");
                }
                StdOut.println();
                break;
            }
        }
    }

}

