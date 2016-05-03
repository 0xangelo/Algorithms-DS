/*******************************************************************************
 * Nome: Ângelo Gregório Lovatto
 * Número USP: 9293435
 *
 * Compilação: javac-algs4 
 * Execução:   java-algs4 
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.*;

public class List<Item> implements Iterable<Item> {
    private RedBlackBST<Double, Item> listOrder;
    private RedBlackBST<Item, Double> listItem;

    public List() {
        listOrder = new RedBlackBST<Double, Item>();
        listItem = new RedBlackBST<Item, Double>();
    }

    public void addFront(Item item) {
        double x;
        if (isEmpty())
            x = 0.5;
        else
            x = listOrder.min()/2;
        listOrder.put(x, item);
        listItem.put(item, x);
    }

    public void addBack(Item item) {
        double x;
        if (isEmpty())
            x = 0.5;
        else
            x = (listOrder.max() + 1)/2;
        listOrder.put(x, item);
        listItem.put(item, x);
    }

    public Item deleteFront() {
        Item x = listOrder.get(listOrder.min());
        listOrder.deleteMin();
        listItem.delete(x);
        return x;
    }

    public Item deleteBack() {
        Item x = listOrder.get(listOrder.max());
        listOrder.deleteMax();
        listItem.delete(x);
        return x;
    }

    public void delete(Item item) {
        double x = listItem.get(item);
        listItem.delete(item);
        listOrder.delete(x);
    }

    public void add(int i, Item item) {
        double key = (listOrder.select(i-1) + listOrder.select(i))/2.0;
        listOrder.put(key, item);
        listItem.put(item, key);
    }

    public Item delete(int i) {
        double key = listOrder.select(i);
        Item x = listOrder.get(key);
        listOrder.delete(key);
        listItem.delete(x);
        return x;
    }

    public boolean contains(Item item) {
        return listItem.contains(item);
    }

    public boolean isEmpty() {
        return listOrder.isEmpty();
    }

    public int size() {
        return listOrder.size();
    }

}
