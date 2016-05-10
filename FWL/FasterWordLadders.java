/*******************************************************************************
 * Nome: Ângelo Gregório Lovatto
 * Número USP: 9293435
 *
 * Compilação: javac-algs4 
 * Execução:   java-algs4 
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.*;
public class FasterWordLadders {

    // return true if two strings differ in exactly one letter
    public static boolean isNeighbor(String a, String b) {
        assert  Math.abs(b.length() - a.length()) <= 1;
        int differ = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) differ++;
            if (differ > 1) return false;
        }
        return true;
    }

    public static void main(String[] args) {

       /*******************************************************************
        *  Read a list of strings, all of the same length.
        *******************************************************************/
        In in = new In(args[0]);
        IndexSET<String> words = new IndexSET<String>();
        while (!in.isEmpty()) {
            String word = in.readString();
            words.add(word);
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
        String[] wordsA = new String[words.size()];
        int index = 0;
        
        for (String palavra : words.keys())
            wordsA[index++] = palavra;
        MSD.sort(wordsA);
        
        for (index = 0; index < wordsA.length - 1; index++) {
            String word1 = wordsA[index];
            String word2 = wordsA[index+1];

            if (word1.compareTo(word2) < 0 && isNeighbor(word1, word2)) {
                G.addEdge(words.indexOf(word1), words.indexOf(word2));
            }
        }
        

       /*******************************************************************
        *  Run breadth first search
        *******************************************************************/
        while (!StdIn.isEmpty()) {
            String from = StdIn.readString();
            String to   = StdIn.readString();
            if (!words.contains(from)) throw new RuntimeException(from + " is not in word list");
            if (!words.contains(to))   throw new RuntimeException(to   + " is not in word list");

            BreadthFirstPaths bfs = new BreadthFirstPaths(G, words.indexOf(from));
            if (bfs.hasPathTo(words.indexOf(to))) {

                for (int v : bfs.pathTo(words.indexOf(to))) {
                    StdOut.println(words.keyOf(v));
                }
            }
            else StdOut.println("NOT CONNECTED");
            StdOut.println();
        }
    }

    private static shiftRight(String a, int offset) {
        String first = a.substring(a.length() - offset);
        String last = a.substring(0, a.lenght() - offset);
        return first.concat(last);
     }

    private static shifLeft(String a, int offset) {
        String first = a.substring(offset);
        String last = a.substring(0, offset);
        return first.concat(last);
    }

}
