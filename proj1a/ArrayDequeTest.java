import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test ArrayDeque methods.
 */
public class ArrayDequeTest {

    /**
     * Test constructor, isEmpty(), size(), get(),
     * addFirst() and addLast() methods.
     */
    @Test
    public void testSizeAddLast() {
        ArrayDeque<Integer> A = new ArrayDeque<>();
        assertTrue(A.isEmpty());
        A.addLast(10);
        A.addLast(15);
        A.addFirst(5);
        assertEquals(3, A.size());
        assertEquals(10, (int) A.get(1));
        assertEquals(15, (int) A.get(2));
        assertNull(A.get(4));

        A.printDeque();
    }

    /**
     * Test removeFirst and removeLast methods.
     */
    @Test
    public void testRemove() {
        ArrayDeque<String> A = new ArrayDeque<>();
        A.addFirst("me");
        A.addFirst("fly");
        A.addLast("to");
        A.addLast("the");
        A.addLast("moon");
        assertEquals("fly", A.removeFirst());
        assertEquals(4, A.size());
        assertEquals("me", A.get(0));
        assertEquals("moon", A.removeLast());
        assertEquals(3, A.size());
        assertEquals("the", A.get(2));

        A.printDeque();
    }

    /**
     * Test edge case when resize() is called,
     * addLast and add First respectively.
     */
    @Test
    public void testResize() {
        ArrayDeque<Integer> A = new ArrayDeque<>();
        A.addLast(5);
        A.addLast(10);
        A.addLast(15);
        A.addLast(20);
        A.addLast(25);
        assertEquals(5, A.size());
        assertEquals(5, (int) A.get(0));
        assertEquals(15, (int) A.get(2));
        A.removeLast();
        assertEquals(20, (int) A.get(3));


        ArrayDeque<Integer> B = new ArrayDeque<>();
        B.addFirst(25);
        B.addFirst(20);
        B.addFirst(15);
        B.addFirst(10);
        B.addFirst(5);
        assertEquals(5, B.size());
        assertEquals(5, (int) B.get(0));
        assertEquals(15, (int) B.get(2));
        A.removeLast();
        assertEquals(20, (int) B.get(3));
    }

}

