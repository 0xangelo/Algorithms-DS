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
    static boolean[] marked;
    static int counter;
    static int[] preorder;
    static int[] low;
 

    public static void main(String[] args) {
        int N = StdIn.readInt();
        int M = StdIn.readInt();
        Graph G = new Graph(N);
        marked = new boolean[N];
        counter = 0;
        preorder = new int[N];
        low = new int[N];
        
        for (int i = 0; i < M; i++) {
            int v1 = StdIn.readInt();
            int v2 = StdIn.readInt();
            G.addEdge(v1, v2);
        }

        dfs(G, 0, 0);
    }
 
    private static void dfs(Graph G, int v, int p) {
        marked[v] = true;
        preorder[v] = counter++;
        low[v] = preorder[v];
        
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w, v);
                low[v] = Math.min(low[v], low[w]);
                if (low[w] == preorder[w])
                    StdOut.println(v + " " + w);
            }
            else if (w != p)
                low[v] = Math.min(low[v], low[w]);
            
        }
    }
}
