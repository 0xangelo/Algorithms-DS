/*******************************************************************************
 * Nome: Ângelo Gregório Lovatto
 * Número USP: 9293435
 *
 * Compilação: javac-algs4 PC2D.java
 * Execução:   java-algs4 PC2D 
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.*;
public class PC2D {

    public static void main(String[] args) {
        int N    = StdIn.readInt();
        double d = StdIn.readDouble();

        int G = (int) (Math.ceil(1.0 / d));    // rows and columns in grid

        // initialize data structure
        Queue<Point2D>[][] grid = (Queue<Point2D>[][]) new Queue[G+2][G+2];
        for (int i = 0; i <= G+1; i++)
            for (int j = 0; j <= G+1; j++)
                grid[i][j] = new Queue<Point2D>();

        WeightedQuickUnionPathCompressionUF classes = new WeightedQuickUnionPathCompressionUF(N);
        
        // read points and check if any previous point <= d
        for (int n = 0; n < N; n++) {
            double x = StdIn.readDouble();
            double y = StdIn.readDouble();
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

        if (classes.count() == 1)
            StdOut.println("Sim");
        else
            StdOut.println("Nao");
    }   
}
 
