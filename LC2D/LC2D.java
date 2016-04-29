/*******************************************************************************
 * Nome: Ângelo Gregório Lovatto
 * Número USP: 9293435
 *
 * Compilação: javac-algs4 LC2D.java
 * Execução:   java-algs4 LC2D d T S(opcional)
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.*;

public class LC2D {

    public static void main(String[] args) {
        double d = Double.parseDouble(args[0]);
        int T = Integer.parseInt(args[1]);
        int G = (int) (Math.ceil(1.0 / d));    // rows and columns in grid
        double[] testResult = new double[T];
        if (args.length == 3)
            StdRandom.setSeed(Integer.parseInt(args[2]));

        for (int t = 0; t < T; t++) {
            // initialize data structure
            Queue<Point2D>[][] grid = (Queue<Point2D>[][]) new Queue[G+2][G+2];
            for (int i = 0; i <= G+1; i++)
                for (int j = 0; j <= G+1; j++)
                    grid[i][j] = new Queue<Point2D>();
        
            DynamicWeightedQuickUnionUF classes = new DynamicWeightedQuickUnionUF();
            
            // generate random points and check if any previous point <= d
            for (int n = 0; classes.count() != 1; n++) {
                double x = StdRandom.uniform();
                double y = StdRandom.uniform();
                Point2D p  = new Point2D(x, y);
                p.index = n;
                int row = 1 + (int) (x * G);
                int col = 1 + (int) (y * G);
                for (int i = row-1; i <= row+1; i++) {
                    for (int j = col-1; j <= col+1; j++) {
                        for (Point2D q : grid[i][j])
                            if (p.distanceTo(q) <= d)
                                classes.union(p.index, q.index);
                    }
                }
                grid[row][col].enqueue(p);
            }

            testResult[t] = (double) classes.sites();
        }

        Statistics results = new Statistics(testResult);

        StdOut.printf("%f %f\n", results.getMean(), results.getVariance());
    }   
}
 
