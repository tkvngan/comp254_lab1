package collections;

import org.junit.jupiter.api.Test;

import static collections.LinkedListTest.assertListEmpty;
import static collections.LinkedListTest.assertListEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for the concatenation method of {@link LinkedList} (Exercise 1).
 */
public interface ConcatenatableLinkedListTest extends LinkedListTest {

    @Override
    ConcatenatableLinkedList<String> linkedList();

    @Test
    default void testConcatenate() {
        LinkedList<String> otherList = createLinkedList();
        linkedList().add(0, "A");
        linkedList().add(1, "B");
        otherList.add(0, "C");
        otherList.add(1, "D");
        LinkedList<String> result = linkedList().concatenate(otherList);
        assertSame(result, linkedList());
        assertListEquals(result, "A", "B", "C", "D");
        // assert that otherList is emptied, i.e. all its nodes are moved to linkedList()
        assertListEmpty(otherList);
    }

    @Test
    default void testConcatenateWithNullList() {
        assertThrows(NullPointerException.class, () -> linkedList().concatenate(null));
        assertListEmpty(linkedList());
        linkedList().addFirst("A");
        assertThrows(NullPointerException.class, () -> linkedList().concatenate(null));
        assertListEquals(linkedList(), "A");
    }

    @Test
    default void testConcatenateEmptyList() {
        LinkedList<String> otherList = createLinkedList();
        linkedList().addLast("A");
        linkedList().addLast("B");
        LinkedList<String> result = linkedList().concatenate(otherList);
        assertSame(result, linkedList());
        assertListEquals(result, "A", "B");
        assertListEmpty(otherList);
    }

    @Test
    default void testConcatenateToAnEmptyList() {
        LinkedList<String> otherList = createLinkedList();
        otherList.addLast("A");
        otherList.addLast("B");
        LinkedList<String> result = linkedList().concatenate(otherList);
        assertSame(result, linkedList());
        assertListEquals(result, "A", "B");
        assertListEmpty(otherList);
    }

    @Test
    default void testConcatenateWithIncompatibleList() {
        LinkedList<String> otherList = new UnknownLinkedList<>();
        assertThrows(IllegalArgumentException.class, () -> linkedList().concatenate(otherList));
        assertListEmpty(linkedList());
        linkedList().addFirst("A");
        assertThrows(IllegalArgumentException.class, () -> linkedList().concatenate(otherList));
        assertListEquals(linkedList(), "A");
    }

    /**
     * This class is used to test if the concat method throws an
     * IllegalArgumentException when the specified list is not a compatible
     * linked list.
     */
    class UnknownLinkedList<E> implements ConcatenatableLinkedList<E> {

        @Override
        public int size() {
            return 0;
        }

        @Override
        public E first() {
            return null;
        }

        @Override
        public E last() {
            return null;
        }

        @Override
        public E get(int index) {
            return null;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public void addFirst(E element) {
        }

        @Override
        public void addLast(E element) {
        }

        @Override
        public void add(int index, E element) {
        }

        @Override
        public E removeFirst() {
            return null;
        }

        @Override
        public E removeLast() {
            return null;
        }

        @Override
        public E remove(int index) {
            return null;
        }

        @Override
        public ConcatenatableLinkedList<E> concatenate(LinkedList<E> list) {
            return null;
        }

    }
}
