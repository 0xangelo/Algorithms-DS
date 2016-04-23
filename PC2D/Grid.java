/******************************************************************************
 *  Compilation:  javac Grid.java
 *  Execution:    java Grid N d
 *  Dependencies: Queue.java
 *  
 *  Generate N random Euclidean points in unit box (coordinates
 *  between 0 and 1) and print out all pairs that are at
 *  distance <= d.
 * 
 ******************************************************************************/

import edu.princeton.cs.algs4.*;

public class Grid {

    public static void main(String[] args) {
        int N    = Integer.parseInt(args[0]);
        double d = Double.parseDouble(args[1]);

        int G = (int) (Math.ceil(1.0 / d));    // rows and columns in grid

        // initialize data structure
        Queue<Point2D>[][] grid = (Queue<Point2D>[][]) new Queue[G+2][G+2];
        for (int i = 0; i <= G+1; i++)
            for (int j = 0; j <= G+1; j++)
                grid[i][j] = new Queue<Point2D>();

        // generate random points and check if any previous point <= d
        for (int n = 0; n < N; n++) {
            double x = StdRandom.uniform();
            double y = StdRandom.uniform();
            Point2D p  = new Point2D(x, y);
            int row = 1 + (int) (x * G);
            int col = 1 + (int) (y * G);
            for (int i = row-1; i <= row+1; i++) {
                for (int j = col-1; j <= col+1; j++) {
                    for (Point2D q : grid[i][j])
                        if (p.distanceTo(q) <= d)
                            StdOut.println(p + " <--> " + q);
                }
            }
            grid[row][col].enqueue(p);
        }
    }   
}
 
