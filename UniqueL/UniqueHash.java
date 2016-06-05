/*******************************************************************************
 * Nome: Ângelo Gregório Lovatto
 * Número USP: 9293435
 *
 * Compilação: javac-algs4 UniqueL.java
 * Execução:   java-algs4 UniqueL L < words.txt
 *             java-algs4 UniqueL L N
 *
 ******************************************************************************/
import edu.princeton.cs.algs4.*;
import java.util.HashMap;
public class UniqueHash {
    private static String in;


    
    private static int uniques(int L) {
        HashMap<String,Integer> st = new HashMap<String,Integer>();

        for (int i = 0; i < in.length() - L; i++) {
            String subString = in.substring(i, i + L);

            if (!st.containsKey(subString))
                st.put(subString, i);
        }

        return st.size();
    }

    private static int highestL() {
        int L = 1;
        while (uniques(L) == Math.pow(10, L)) L++;
        return L-1;
    }

    private static void randomIn(int N) {
        in = new String("");
        for (int i = 0; i < N; i++) {
            in += (char) (StdRandom.uniform(10) + '0');
        }
    }

    public static void main(String[] args) {
        int L = Integer.parseInt(args[0]);

        if (args.length == 1) {
            in = StdIn.readString();
            if (L > 0) StdOut.println(uniques(L));
            else       StdOut.println(highestL());
        }

        if (args.length == 2) {
            int N = Integer.parseInt(args[1]);
            randomIn(N);
            StdOut.println(highestL());
        }
    }
    
}
