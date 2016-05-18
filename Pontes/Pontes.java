/*******************************************************************************
 * Nome: Ângelo Gregório Lovatto
 * Número USP: 9293435
 *
 * Compilação: javac-algs4 Pontes.java
 * Execução:   java-algs4 Pontes 
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.*;
public class Pontes {

    public static void main(String[] args) {
        int N = StdIn.readInt();
        int M = StdIn.readInt();
        Graph G = new Graph(N);
        boolean[] marked = new boolean[N];
        
        for (int i = 0; i < M; i++) {
            int v1 = StdIn.readInt();
            int v2 = StdIn.readInt();
            G.addEdge(v1, v2);
        }

        Digraph D = new Digraph(N);
        dfs(0);

        
    }
 
    private void dfs(int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            D.addEdge(v, w);
            if (!marked[w]) {
                dfs(w);
            }
        }
    }
}
