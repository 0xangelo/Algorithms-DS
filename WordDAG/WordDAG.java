/*******************************************************************************
 * Nome: Ângelo Gregório Lovatto
 * Número USP: 9293435
 *
 * Compilação: javac-algs4 
 * Execução:   java-algs4 
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.*;

public class WordDAG {
    private final Digraph G;
    private final IndexSET<String> words;
    private final Iterable<Integer> topologicalOrder;
    
    public WordDAG(String[] strings) {
        G = new Digraph(strings.length);
        words = new IndexSET<String>();
        int W = 0;
        for(String word : strings) {
            words.add(word);
            if (word.length() > W) W = word.length();
        }

        /*******************************************************************
         *  Insert connections between neighboring words into graph.
         *  This construction process can be improved from LN^2 in the worst
         *  case to L^2 N in the worst case by L radix sorts where
         *  N = number of strings and L = length of each words.
         *
         *  We avoid inserting two copies of each edge by checking if
         *  word1.compareTo(word2) < 0
         *
         *******************************************************************/
        Graph G = new Graph(words.size());
        
        /*****************************************************************/
        MSD.sort(wordsA);
        
        for (int i = 0; i < wordsA.length -1; i++) {
            String word1 = wordsA[i];
            for (int j = i +1; j < wordsA.length; j++) {
                String word2 = wordsA[j];
                if (isNeighbor(word1, word2)) {
                    G.addEdge(words.indexOf(word1), words.indexOf(word2));
                }
                else if (Math.abs(word2.length() - word1.length()) < 2) break;
            }
        }
        /*****************************************************************/        
        
        for (int k = 1; k < W; k++) {
            shiftArray(wordsA);
            MSD.sort(wordsA);
        
            for (int i = 0; i < wordsA.length -1; i++) {
                String word1 = shiftLeft(wordsA[i], k);
                for (int j = i +1; j < wordsA.length; j++) {
                    String word2 = shiftLeft(wordsA[j], k);
                    if (isNeighbor(word1, word2)) {
                        G.addEdge(words.indexOf(word1), words.indexOf(word2));
                    }
                    else break;
                }
            }
        }
        StdOut.println("Finished Graph");
        Topological sort = new Topological(G);
        topologicalOrder = sort.order();
    }

    public void PrintDAG() {
        Out out = new Out("saida.txt");
        for (int i = 0; i < G.V(); i++) {
            for (int v : G.adj(words.indexOf(from)))
                Out.println(words.keyOf(i) + " " + words.keyOf(v));
        }
    }

    public void PrintPathCount(String a, String b) {
        Out out = new Out("saida.txt");
        int word1 = words.indexOf(a);
        int word2 = words.indexOf(b);

        for (int v : topologicalOrder)
            
        
    }

    private numberOfPaths(int v1, int v2) {
        
    }

    // return true if two strings differ in exactly one letter
    public static boolean isNeighbor(String a, String b) {
        if (Math.abs(b.length() - a.length()) > 1) return false;
        int differ = 0;
        if (a.length() != b.length()) differ++;
        for (int i = 0; i < Math.min(a.length(), b.length()); i++) {
            if (a.charAt(i) != b.charAt(i)) differ++;
            if (differ > 1) return false;
        }
        return true;
    }
}
    

