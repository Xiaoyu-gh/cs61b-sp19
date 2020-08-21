/**
 * Implementation of ArrayDeque.
 * @author zxy
 */
public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    /**
     * Constructor: create a empty ArrayDeque.
     */
    public ArrayDeque() {
        size = 0;
        items = (T[]) new Object[8];
        nextFirst = 3;
        nextLast = 4;
    }

//    /**
//     * Create a copy from other.
//     */
//    public ArrayDeque(ArrayDeque other) {
//        size = other.size;
//        nextFirst = other.nextFirst;
//        nextLast = other.nextLast;
//        items = (T[]) new Object[other.items.length];
//        System.arraycopy(other.items, 0, items, 0, items.length);
//    }

    /**
     * Return the size of the ArrayDeque.
     */
    public int size() {
        return size;
    }

    /**
     * Private helper method: resizes the array to the target capacity.
     * (Actually it create a new array.)
     */
    private void resize(int capacity) {
        T[] temp = (T[]) new Object[capacity];
        System.arraycopy(items, nextFirst + 1, temp, capacity / 4, size);
        items = temp;
        nextFirst = capacity / 4 - 1;
        nextLast = nextFirst + size + 1;
    }

    /**
     * Private helper method: downSizes the array to half capacity.
     */
    private void downSize() {
        int newLength = items.length / 2;
        T[] temp = (T[]) new Object[newLength];
        System.arraycopy(items, nextFirst + 1, temp, newLength / 4, size);
        items = temp;
        nextFirst = newLength / 4 - 1;
        nextLast = nextFirst + size + 1;
    }


    /**
     * Add an item to the back of the ArrayDeque.
     */
    public void addLast(T value) {
        if (nextLast >= items.length) {
            resize(items.length * 2);
        }
        items[nextLast] = value;
        nextLast++;
        size++;
    }

    /**
     * Add an item to the front of the ArrayDeque.
     */
    public void addFirst(T value) {
        if (nextFirst < 0) {
            resize(items.length * 2);
        }
        items[nextFirst] = value;
        nextFirst--;
        size++;
    }

    /**
     * Returns true if the array is empty, false otherwise.
     */
    public boolean isEmpty() {
        return (size == 0);
    }

    /**
     * Get the item at the given index, starting at 0.
     * If no such item exists, return null.
     */
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        return items[nextFirst + index + 1];
    }

    /**
     * Remove and return the last item of array.
     * Downsize the array when memory usage less than 1/4.
     */
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        if (size <= items.length / 4) {
            downSize();
        }
        T toRemove = items[nextLast - 1];
        items[nextLast - 1] = null;
        size--;
        nextLast--;
        return  toRemove;
    }

    /**
     * Remove and return the first item of array.
     * Downsize the array when memory usage less than 1/4.
     */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        if (size <= items.length / 4) {
            downSize();
        }
        T toRemove = items[nextFirst + 1];
        items[nextFirst + 1] = null;
        size--;
        nextFirst++;
        return toRemove;
    }

    /**
     * Prints the items in the deque from first to last.
     */
    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(this.get(i));
            System.out.print(" ");
        }
        System.out.println();
    }

}



