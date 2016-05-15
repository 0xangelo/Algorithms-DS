/*******************************************************************************
 * Nome: Ângelo Gregório Lovatto
 * Número USP: 9293435
 *
 * Compilação: javac-algs4 WordDAG.java
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.*;

public class WordDAG {
    private  Digraph G;
    private  IndexSET<String> words;
    private  int[] topologicalOrder; //guarda a posição de cada vértice na ordem topologica
    private  int[] reversePostOrder; //quarda os vértices em ordem topológica
    
    public WordDAG(String[] strings) {
        G = new Digraph(strings.length);
        words = new IndexSET<String>();
        topologicalOrder = new int[strings.length];
        reversePostOrder = new int[strings.length];
        int W = 0;
        for(String word : strings) {
            words.add(word);
            if (word.length() > W) W = word.length();
        }
        
        MSD.sort(strings);
        
        for (int i = 0; i < strings.length -1; i++) {
            String word1 = strings[i];
            for (int j = i +1; j < strings.length; j++) {
                String word2 = strings[j];
                if (isNeighbor(word1, word2)) {
                    G.addEdge(words.indexOf(word2), words.indexOf(word1));
                }
                else if (Math.abs(word2.length() - word1.length()) < 2) break;
            }
        }
        
        // Shifting the words
        
        for (int k = 1; k < W; k++) {
            shiftArray(strings);
            MSD.sort(strings);
        
            for (int i = 0; i < strings.length -1; i++) {
                String word1 = shiftLeft(strings[i], k);
                for (int j = i +1; j < strings.length; j++) {
                    String word2 = shiftLeft(strings[j], k);
                    if (isNeighbor(word1, word2)) {
                        G.addEdge(words.indexOf(word2), words.indexOf(word1));
                    }
                    else break;
                }
            }
        }
        
        Topological sort = new Topological(G);
        int i = 0;
        for (int v : sort.order()) {
            reversePostOrder[i] = v;
            topologicalOrder[v] = i++;
        }
    }

    public void PrintDAG() {
        Out out = new Out("saida.txt");
        for (int from = 0; from < G.V(); from++) {
            for (int v : G.adj(from))
                out.println(words.keyOf(from) + " " + words.keyOf(v));
        }
    }

    public void PrintPathCount(String a, String b) {
        Out out = new Out("saida.txt");
        int word1 = words.indexOf(a);
        int word2 = words.indexOf(b);
        int count = numberOfPaths(word1, word2);

        out.println(count);        
    }

    private int numberOfPaths(int v1, int v2) {
        if (topologicalOrder[v1] > topologicalOrder[v2]) return 0;
        int num = 0;
        for (int X : G.adj(v1)) {
            if (X == v2) num++;
            else num += numberOfPaths(X, v2);
        }
        return num;
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

    // return true if two strings differ in exactly one letter
    private static boolean isNeighbor(String a, String b) {
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
    

