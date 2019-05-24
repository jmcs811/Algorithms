import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first;
    private Node last;
    private int size;

    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    public Deque(){}

    public boolean isEmpty() { return this.size == 0; }

    public int size() { return this.size; }

    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }

        size++;

        Node newFirstNode = new Node();
        newFirstNode.item = item;

        // if size is 1 first and last are the same
        if (size == 1) {
            this.first = newFirstNode;
            this.last = newFirstNode;

        } else {
            // set up new first
            Node oldFirst = this.first;
            this.first = newFirstNode;
            newFirstNode.next = oldFirst;
            newFirstNode.prev = null;

            // make old first point to new first
            oldFirst.prev = newFirstNode;
        }
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }

        size++;

        Node newLastNode = new Node();
        newLastNode.item = item;

        if (size == 1) {
            this.last = newLastNode;
            this.first = newLastNode;

        } else {
            Node oldLast = this.last;
            this.last = newLastNode;
            newLastNode.next = null;
            newLastNode.prev = oldLast;

            oldLast.next = newLastNode;
        }
    }

    public Item removeFirst() {

        Item returnItem = this.first.item;
        size--;

        this.first = this.first.next;

        if (size() == 0) {
            this.last = null;
        } else {
            this.first.prev = null;
        }

        return returnItem;
    }

    public Item removeLast() {

        Item returnItem = this.last.item;
        size--;

        this.last = this.last.prev;

        if (size() == 0) {
            this.first = null;
        } else {
            this.last.next = null;
        }

        return returnItem;
    }

    @Override
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {

        private Node current;

        public DequeIterator() {
            this.current = first;
        }

        @Override
        public boolean hasNext() {
            return (current != null);
        }

        @Override
        public Item next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException("No Such Element");
            }

            Node oldCurrent = current;
            current = current.next;
            return oldCurrent.item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Cannot call remove");
        }
    }
//
//    public static void main(String[] args) {
//
//    }
}
