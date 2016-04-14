/***********************************************************************************
 * Nome: Ângelo Gregório Lovatto
 * Número USP: 9293435
 *
 * Compilação: javac-algs4 DynamicWeightedQuickUnionUF.java
 * Execução:   java-algs4 DynamicWeightedQuickUnionUF < dados.txt
 *
 * Implementa uma UF com compressão de caminhos e com suporte para a adição de novos
 * sites.
 *
 **********************************************************************************/

import edu.princeton.cs.algs4.*;
public class DynamicWeightedQuickUnionUF {
    private int[] parent;   // parent[i] = parent of i
    private int[] size;     // size[i] = number of sites in subtree rooted at i
    private int count;      // number of components

    public DynamicWeightedQuickUnionUF() {
        count = 2;
        parent = new int[2];
        parent[0] = 0; parent[1] = 1;
        size = new int[2];
        size[0] = size[1] = 1;
    }

    public int count() {
        return count;
    }

    public int sites() {
        return parent.length;
    }

    public int find(int p) {
        validate(p);
        int root = p;
        while (root != parent[root])
            root = parent[root];
        while (p != root) {
            int newp = parent[p];
            parent[p] = root;
            p = newp;
        }
        return root;
    }

    private void validate(int p) {
        int N = parent.length;
        if (p < 0 )
            throw new IndexOutOfBoundsException("index " + p + " is less than 0");
        if (p >= N)
            while (p > this.newSite()) {}
    }

    public int newSite() {
        resize(parent.length + 1);
        return parent.length - 1;
    }

    private void resize(int max) {
        assert max >= parent.length;
        count += max - parent.length;
        int[] temp = new int[max];
        int[] aux = new int[max];

        for (int i = 0; i < parent.length; i++) {
            temp[i] = parent[i];
            aux[i] = size[i];
        }
        temp[parent.length] = parent.length;
        aux[parent.length] = 1;

        parent = temp;
        size = aux;
    }
    
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;

        if (size[rootP] < size[rootQ]) {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        }
        else {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
        }
        count--;
    }

    public static void main(String[] args) {
        DynamicWeightedQuickUnionUF uf = new DynamicWeightedQuickUnionUF();
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (uf.connected(p, q)) continue;
            uf.union(p, q);
        }
        
        for (int i = 0; i < uf.sites(); i++) {
            int root = uf.find(i);
            if (i != root)
                StdOut.printf("%d %d\n", i, root);
        }
    }

}

