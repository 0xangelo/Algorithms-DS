/*******************************************************************************
 * Nome: Ângelo Gregório Lovatto
 * Número USP: 9293435
 *
 * Compilação: javac-algs4 
 * Execução:   java-algs4 
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

        
    }

}

