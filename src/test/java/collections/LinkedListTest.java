package collections;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Base Unit tests for {@link LinkedList}.
 */
public interface LinkedListTest {

    /**
     * Utility method to assert that the specified linked list is equal to the
     * specified elements.
     *
     * @param list the linked list to be tested
     * @param expected the expected elements
     */
    static void assertListEquals(LinkedList<String> list, String... expected) {
        if (expected.length == 0) {
            assertTrue(list.isEmpty());
            assertEquals(0, list.size());
            assertNull(list.first());
            assertNull(list.last());
            assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
        } else {
            int size = list.size();
            assertFalse(list.isEmpty());
            assertEquals(expected.length, size);
            assertEquals(expected[0], list.first());
            assertEquals(expected[expected.length - 1], list.last());
            for (int i = 0; i < expected.length; i++) {
                assertEquals(expected[i], list.get(i));
            }
            assertThrows(IndexOutOfBoundsException.class, () -> list.get(expected.length));
        }
    }

    /**
     * Utility to assert that the specified linked list is empty.
     *
     * @param list the linked list to be tested
     */
    static void assertListEmpty(LinkedList<String> list) {
        assertListEquals(list);
    }

    /**
     * Returns a new instance of the LinkedList implementation to be tested.
     * Subclasses must override this method to create specific implementations
     * of LinkedList.
     *
     * @param <E> the type of elements in the linked list
     * @return a new instance of the LinkedList implementation to be tested
     */
    <E> LinkedList<E> createLinkedList();

    /**
     * Returns the LinkedList implementation to be tested. Subclasses must
     * override this method to return the linked list to be tested.
     *
     * @return the linked list to be tested.
     */
    LinkedList<String> linkedList();

    @Test
    default void testAddFirst() {
        linkedList().addFirst("A");
        assertListEquals(linkedList(), "A");
        linkedList().addFirst("B");
        assertListEquals(linkedList(), "B", "A");
    }

    @Test
    default void testAddLast() {
        linkedList().addLast("A");
        assertListEquals(linkedList(), "A");
        linkedList().addLast("B");
        assertListEquals(linkedList(), "A", "B");
    }

    @Test
    default void testAdd() {
        linkedList().add(0, "A");
        assertListEquals(linkedList(), "A");
        linkedList().add(1, "B");
        assertListEquals(linkedList(), "A", "B");
        linkedList().add(2, "C");
        assertListEquals(linkedList(), "A", "B", "C");
        linkedList().add(1, "D");
        assertListEquals(linkedList(), "A", "D", "B", "C");
    }

    @Test
    default void testRemoveFirst() {
        linkedList().addLast("A");
        linkedList().addLast("B");
        assertListEquals(linkedList(), "A", "B");
        assertEquals("A", linkedList().removeFirst());
        assertListEquals(linkedList(), "B");
        assertEquals("B", linkedList().removeFirst());
        assertListEmpty(linkedList());
    }

    @Test
    default void testRemoveLast() {
        linkedList().addLast("A");
        linkedList().addLast("B");
        linkedList().addLast("C");
        assertListEquals(linkedList(), "A", "B", "C");
        assertEquals("C", linkedList().removeLast());
        assertListEquals(linkedList(), "A", "B");
        assertEquals("B", linkedList().removeLast());
        assertListEquals(linkedList(), "A");
        assertEquals("A", linkedList().removeLast());
        assertListEmpty(linkedList());
    }

    @Test
    default void testRemove() {
        linkedList().add(0, "A");
        linkedList().add(1, "B");
        linkedList().add(2, "C");
        assertListEquals(linkedList(), "A", "B", "C");
        assertEquals("B", linkedList().remove(1));
        assertListEquals(linkedList(), "A", "C");
        assertEquals("A", linkedList().remove(0));
        assertListEquals(linkedList(), "C");
        assertEquals("C", linkedList().remove(0));
        assertListEmpty(linkedList());
    }

    @Test
    default void testRemoveFirstEmptyList() {
        assertNull(linkedList().removeFirst());
        assertListEmpty(linkedList());
    }

    @Test
    default void testRemoveLastEmptyList() {
        assertNull(linkedList().removeLast());
        assertListEmpty(linkedList());
    }

    @Test
    default void testRemoveAtIndex0() {
        linkedList().add(0, "A");
        linkedList().add(1, "B");
        linkedList().add(2, "C");
        assertEquals("A", linkedList().remove(0));
        assertListEquals(linkedList(), "B", "C");
    }

    @Test
    default void testRemoveAtLastIndex() {
        linkedList().add(0, "A");
        linkedList().add(1, "B");
        linkedList().add(2, "C");
        assertEquals("C", linkedList().remove(2));
        assertListEquals(linkedList(), "A", "B");
    }

    @Test
    default void testRemoveAtIndexOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> linkedList().remove(0));
        assertListEmpty(linkedList());
        linkedList().addLast("A");
        assertListEquals(linkedList(), "A");
        assertThrows(IndexOutOfBoundsException.class, () -> linkedList().remove(1));
        assertListEquals(linkedList(), "A");
    }

    @Test
    default void testAddAtIndexOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> linkedList().add(1, "A"));
        linkedList().addLast("B");
        assertThrows(IndexOutOfBoundsException.class, () -> linkedList().add(2, "A"));
    }

    @Test
    default void testGetInvalidIndex() {
        linkedList().addFirst("A");
        assertListEquals(linkedList(), "A");
        assertThrows(IndexOutOfBoundsException.class, () -> linkedList().get(1));
        assertThrows(IndexOutOfBoundsException.class, () -> linkedList().get(-1));
    }

    @Test
    default void testToString() {
        assertEquals("()", linkedList().toString());
        linkedList().addLast("A");
        assertEquals("(A)", linkedList().toString());
        linkedList().addLast("B");
        assertEquals("(A, B)", linkedList().toString());
    }
}
