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
     * Private helper method :Resizes the array to the target newSize.
     * (Actually it create a new array.)
     */
    private void resize(int newSize) {
        T[] temp = (T[]) new Object[newSize];
        System.arraycopy(items, nextFirst + 1, temp, newSize / 4, size);
        items = temp;
        nextFirst = newSize / 4 - 1;
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
     */
    public T removeLast() {
        T toRemove = items[nextLast - 1];
        items[nextLast - 1] = null;
        size--;
        nextLast--;
        return  toRemove;
    }

    /**
     * Remove and return the first item of array.
     */
    public T removeFirst() {
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



