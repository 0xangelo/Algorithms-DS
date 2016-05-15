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
        if (Math.abs(b.length() - a.length()) > 1) return false;
        int differ = 0;
        if (a.length() != b.length()) differ++;
        for (int i = 0; i < Math.min(a.length(), b.length()); i++) {
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
        String[] wordsA = in.readAllStrings();
        for(String word : wordsA)
            words.add(word);
        

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
        int W = wordsA[0].length();
        /*****************************************************************/
        MSD.sort(wordsA);
        
        for (int i = 0; i < wordsA.length -1; i++) {
            String word1 = wordsA[i];
            if (word1.length() > W) W = word1.length();
            for (int j = i +1; j < wordsA.length; j++) {
                String word2 = wordsA[j];
                if (isNeighbor(word1, word2)) {
                    G.addEdge(words.indexOf(word1), words.indexOf(word2));
                }
                else break;
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

            /*
            for (int v : G.adj(words.indexOf(from)))
                StdOut.print(words.keyOf(v) + " ");
            StdOut.println();
            */
        }
    }

    /***************************************************************************
     * Auxiliary functions.
     **************************************************************************/
    
    private static void shiftArray(String[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = shiftRight(array[i]);
        }
    }

    private static String shiftLeft(String S, int offset) {
        offset = offset % S.length();
        for (int i = 0; i < offset; i++)
            S = shiftLeft(S);
        return S;
    }
    
    private static String shiftLeft(String S) {
        char[] arr = S.toCharArray();

        char temp;
        int len = S.length();
               
        temp = arr[0];
        for(int j = 0; j < len-1; j++) {
            arr[j] = arr[j+1];
        }
        arr[len-1] = temp;
        
        String X = new String(arr);
        return X;
    }    

    private static String shiftRight(String S) {
        char[] arr = S.toCharArray();

        char temp;
        int len = S.length();

        temp = arr[len-1];
        for(int j = len-1; j > 0; j--) {
            arr[j] = arr[j-1];
        }
        arr[0] = temp;

        String X = new String(arr);
        return X;
    }

}
