/*******************************************************************************
 * Nome: Ângelo Gregório Lovatto
 * Número USP: 9293435
 *
 * Compilação: javac-algs4 Brute.java
 * Execução:   java-algs4 Brute
 *
 * Exercício na linha 49.
 ******************************************************************************/
/******************************************************************************
 *  Compilation:  javac Brute.java
 *  Execution:    java Brute pattern text
 *  Dependencies: StdOut.java
 *
 *  Reads in two strings, the pattern and the input text, and
 *  searches for the pattern in the input text using brute force.
 *
 *  % java Brute 
 *  % abracadabra abacadabrabracabracadabrabrabracad
 *  text:    abacadabrabracabracadabrabrabracad 
 *  pattern:               abracadabra          
 *
 *  % java Brute 
 *  % rab abacadabrabracabracadabrabrabracad
 *  text:    abacadabrabracabracadabrabrabracad 
 *  pattern:         rab                         
 * 
 *  % java Brute 
 *  % rabrabracad abacadabrabracabracadabrabrabracad
 *  text:    abacadabrabracabracadabrabrabracad
 *  pattern:                        rabrabracad
 *
 *  % java Brute
 *  % bcara abacadabrabracabracadabrabrabracad
 *  text:    abacadabrabracabracadabrabrabracad 
 *  pattern:                                   bcara
 * 
 *  % java Brute 
 *  % abacad abacadabrabracabracadabrabrabracad
 *  text:    abacadabrabracabracadabrabrabracad
 *  pattern: abacad
 *
 ******************************************************************************/
import edu.princeton.cs.algs4.*;
public class Brute {
    
   /***************************************************************************
    *  String versions.
    ***************************************************************************/
    public static Iterable<Integer> findAll(String txt, String pat) {
        int M = pat.length();
        int N = txt.length();
        Queue<Integer> queue = new Queue<Integer>();

        for (int i = 0; i <= N - M; i++) {
            int j;
            for (j = 0; j < M; j++) {
                if (txt.charAt(i+j) != pat.charAt(j))
                    break;
            }
            if (j == M) queue.enqueue(i-1);
        }
        return queue;
    }
    
    // return offset of first match or N if no match
    public static int search1(String pat, String txt) {
        int M = pat.length();
        int N = txt.length();

        for (int i = 0; i <= N - M; i++) {
            int j;
            for (j = 0; j < M; j++) {
                if (txt.charAt(i+j) != pat.charAt(j))
                    break;
            }
            if (j == M) return i;            // found at offset i
        }
        return N;                            // not found
    }

    // return offset of first match or N if no match
    public static int search2(String pat, String txt) {
        int M = pat.length();
        int N = txt.length();
        int i, j;
        for (i = 0, j = 0; i < N && j < M; i++) {
            if (txt.charAt(i) == pat.charAt(j)) j++;
            else {
                i -= j;
                j = 0;
            }
        }
        if (j == M) return i - M;    // found
        else        return N;        // not found
    }


   /***************************************************************************
    *  char[] array versions.
    ***************************************************************************/

    // return offset of first match or N if no match
    public static int search1(char[] pattern, char[] text) {
        int M = pattern.length;
        int N = text.length;

        for (int i = 0; i <= N - M; i++) {
            int j;
            for (j = 0; j < M; j++) {
                if (text[i+j] != pattern[j])
                    break;
            }
            if (j == M) return i;            // found at offset i
        }
        return N;                            // not found
    }

    // return offset of first match or N if no match
    public static int search2(char[] pattern, char[] text) { 
        int M = pattern.length;
        int N = text.length;
        int i, j;
        for (i = 0, j = 0; i < N && j < M; i++) {
            if (text[i] == pattern[j]) j++;
            else {
                i -= j;
                j = 0;
            }
        }
        if (j == M) return i - M;    // found
        else        return N;        // not found
    } 


    /** 
     * Takes a pattern string and an input string as command-line arguments;
     * searches for the pattern string in the text string; and prints
     * the first occurrence of the pattern string in the text string.
     */
    public static void main(String[] args) {
        String pat = StdIn.readString();
        String txt = StdIn.readAll();

        for(int i : findAll(txt, pat))
            StdOut.print(i + " ");
        StdOut.println();
    }
}
