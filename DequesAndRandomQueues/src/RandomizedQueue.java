import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] itemArray;
    private int n;

    public RandomizedQueue() {
        itemArray = (Item[]) new Object[2];
    }

    public boolean isEmpty() { return (this.n == 0); }

    public int size() { return n; }

    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        expandArray();
        itemArray[n++] = item;
    }

    public Item dequeue() {

        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        int randomIndex = randomNum();
        Item item = itemArray[randomIndex];
        itemArray[randomIndex] = itemArray[n-1];
        itemArray[n-1] = null;
        n--;
        shrinkArray();

        return item;
    }

    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        return itemArray[randomNum()];
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            copy[i] = itemArray[i];
        }
        itemArray = copy;
    }

    private void expandArray() {
        if (n == itemArray.length) {
            resize(2*itemArray.length);
        }
    }

    private void shrinkArray() {
        if (n > 0 && n == itemArray.length/4) {
            resize(itemArray.length);
        }
    }

    private int randomNum() {
        return StdRandom.uniform(0, n);
    }

    @Override
    public Iterator<Item> iterator() {
        return new ItemIterator();
    }

    private class ItemIterator implements Iterator<Item> {

        private Item[] array;
        private int i;

        public ItemIterator() {
            copyArray();
            StdRandom.shuffle(array);
        }

        private void copyArray() {
            array = (Item[]) new Object[n];
            for (int i = 0; i < n; i++) {
                array[i] = itemArray[i];
            }
        }

        @Override
        public boolean hasNext() {
            return i < n;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            return array[i++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

//    public static void main(String[] args) {
//
//    }
}
