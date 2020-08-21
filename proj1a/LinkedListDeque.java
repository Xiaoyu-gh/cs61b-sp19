/**
 * Implementation of LinkedListDeque.
 *
 * @author zxy
 */
public class LinkedListDeque<T> {

    /**
     * Nested class TNode whit type T.
     */
    private class TNode {
        private T item;
        private TNode prev;
        private TNode next;

        /**
         * constructor of private TNode.
         */
        TNode(TNode p, T i, TNode n) {
            prev = p;
            item = i;
            next = n;
        }
    }

    private TNode sentinel;
    private int size;

    /**
     * Create a empty Deque.
     */
    public LinkedListDeque() {
        sentinel = new TNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

//    /**
//     * Create a Deque copy from other.
//     */
//    public LinkedListDeque(LinkedListDeque other) {
//        /* First create an empty deque. */
//        sentinel = new TNode(null, null, null);
//        sentinel.prev = sentinel;
//        sentinel.next = sentinel;
//        size = 0;
//
//        for (int i = 0; i < other.size; i++) {
//            addLast((T) other.get(i));
//        }
//    }

    /**
     * Adds an item of type T to the front of the deque.
     */
    public void addFirst(T i) {
        TNode temp = sentinel.next;
        sentinel.next = new TNode(sentinel, i, temp);
        temp.prev = sentinel.next;
        size++;
    }

    /**
     * Adds an item of type T to the back of the deque.
     */
    public void addLast(T i) {
        TNode temp = sentinel.prev;
        sentinel.prev = new TNode(temp, i, sentinel);
        temp.next = sentinel.prev;
        size++;
    }

    /**
     * Returns true if deque is empty, false otherwise.
     */
    public boolean isEmpty() {
        return (sentinel.prev == sentinel && sentinel.next == sentinel);
    }

    /**
     * Returns the number of items in the deque.
     */
    public int size() {
        return size;
    }

    /**
     * Prints the items in the deque from first to last.
     */
    public void printDeque() {
        TNode p = sentinel.next;
        while (p != sentinel) {
            System.out.print(p.item);
            System.out.print(' ');
            p = p.next;
        }
        System.out.println();
    }

    /**
     * Removes and returns the item at the front of the deque.
     * If no such item exists, returns null.
     */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }

        TNode temp = sentinel.next.next;
        T removedItem = sentinel.next.item;
        sentinel.next = null;
        sentinel.next = temp;
        temp.prev = sentinel;
        size--;
        return removedItem;
    }

    /**
     * Removes and returns the item at the back of the deque.
     * If no such item exists, returns null.
     */
    public T removeLast() {
        if (size == 0) {
            return null;
        }

        TNode temp = sentinel.prev.prev;
        T removedItem = sentinel.prev.item;
        sentinel.prev = null;
        sentinel.prev = temp;
        temp.next = sentinel;
        size--;
        return removedItem;
    }

    /**
     * Gets the item at the given index, where 0 is the front.
     * If no such item exists, returns null.
     */
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        int i = 0;
        TNode p = sentinel.next;
        while (i < index) {
            p = p.next;
            i++;
        }
        return p.item;
    }

    /**
     * Private helper method for getRecursive.
     * Return item at index position starting at start TNode.
     * Note: start should NOT be sentinel!!
     */
    private T getRecursive(int index, TNode start) {
        if (index == 0) {
            return start.item;
        }
        return getRecursive(index - 1, start.next);
    }

    /**
     * Implements get in recursive way.
     */
    public T getRecursive(int index) {
        return getRecursive(index, sentinel.next);
    }

}
