/*******************************************************************************
 * Nome: Ângelo Gregório Lovatto
 * Número USP: 9293435
 *
 * Compilação: javac-algs4 UniqueLRK.java
 * Execução:   java-algs4 UniqueLRK L < words.txt
 *             java-algs4 UniqueLRK L N
 *
 ******************************************************************************/
import edu.princeton.cs.algs4.*;
public class UniqueLRK {
    private static String in;

    private static int uniques(int L) {
        int max = (int) Math.pow(10, L);
        int count = 0;
        String format = "%0" + Integer.toString(L) + "d";
        for (int i = 0; i < max; i++) {
            String sequence = String.format(format, i);
            RabinKarp seq = new RabinKarp(sequence);
            if (seq.search(in) != in.length())
                count++;
        }

        return count;
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
            in = StdIn.readLine();
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
