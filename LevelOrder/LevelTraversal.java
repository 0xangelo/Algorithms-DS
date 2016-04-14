/*******************************************************************************
 * Nome: Ângelo Gregório Lovatto
 * Número USP: 9293435
 *
 * Compilação: javac-algs4 LevelTransversal.java
 * Execução:   java-algs4 LevelTransversal < entrada.txt
 *
 * Essa classe constrói uma árvore binária de busca não balanceada com as chaves
 * da entrada padrão. Em seguida, faz uso do método printLevel para imprimir
 * todas as chaves de uma subárvore (dada a chave de sua raiz) em ordem de 
 * altura e da esquerda para a direita.
 ******************************************************************************/

import edu.princeton.cs.algs4.*;
public class LevelTransversal {

    public static void main(String[] args) {
        int N = StdIn.readInt();
        int M = StdIn.readInt();
        BST<Integer, Integer> tree = new BST<Integer, Integer>();
        
        for (int i = 0; i < N; i++) {
            int in = StdIn.readInt();
            tree.put(in, in);
        }

        for (int i = 0; i < M; i++) {
            int subTree = StdIn.readInt();
            tree.printLevel(subTree);
            StdOut.println();
        }

    }
    
}
