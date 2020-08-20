import static org.junit.Assert.*;
import org.junit.Test;

/** Performs some basic linked list tests. */
public class LinkedListDequeTest {

	/**
	 * Also, git() is tested.
	 */
	@Test
	public void testAddFirst() {
		LinkedListDeque<Integer> A = new LinkedListDeque<>();
		A.addFirst(15);
		A.addFirst(10);
		A.addFirst(5);
		assertEquals(5, (int)A.get(0));
		assertEquals(10, (int)A.get(1));

	}

	@Test
	public void testAddLast() {
		LinkedListDeque<Integer> A = new LinkedListDeque<>();
		A.addLast(5);
		A.addLast(10);
		A.addLast(15);
		assertEquals(10, (int)A.get(1));
		assertEquals(15, (int)A.get(2));

	}

	/**
	 * Test size() and copy constructor.
	 * */
	@Test
	public void testSizeCopy() {
		LinkedListDeque<String> A = new LinkedListDeque<>();
		assertEquals(0, A.size());
		A.addLast("i");
		A.addLast("love");
		A.addLast("you");
		assertEquals(3, A.size());
		LinkedListDeque<String> B = new LinkedListDeque<>(A);
		B.addFirst("darling");
		assertEquals(4, B.size());
	}

	@Test
	public void testIsEmpty() {
		LinkedListDeque<Integer> A = new LinkedListDeque<>();
		assertTrue(A.isEmpty());
	}

	@Test
	public void testRemoveFirst() {
		LinkedListDeque<Integer> A = new LinkedListDeque<>();
		A.addLast(5);
		A.addLast(10);
		A.addLast(15);

		assertEquals(5, (int)A.removeFirst());
		assertEquals(2, A.size());
		assertEquals(10, (int)A.get(0));
	}

	@Test
	public void testRemoveLast() {
		LinkedListDeque<Integer> A = new LinkedListDeque<>();
		A.addLast(5);
		A.addLast(10);
		A.addLast(15);
		assertEquals(15, (int)A.removeLast());
		assertEquals(2, A.size());
		assertEquals(10, (int)A.get(1));
	}

	@Test
	public void testGet() {
		LinkedListDeque<String> A = new LinkedListDeque<>();
		A.addLast("fly");
		A.addLast("me");
		A.addLast("to");
		A.addLast("the");
		A.addLast("moon");

		assertEquals(5, A.size());
		assertEquals("fly", A.get(0));
		assertEquals("to", A.get(2));
		assertEquals("moon", A.get(4));
	}

	/**
	 * Test edge cases which should return null.
	 */
	@Test
	public void testEdgeCase() {
		LinkedListDeque<String> A = new LinkedListDeque<>();
		assertNull(A.removeFirst());
		assertNull(A.removeLast());
		assertNull(A.get(0));
		assertNull(A.get(2));
	}

}