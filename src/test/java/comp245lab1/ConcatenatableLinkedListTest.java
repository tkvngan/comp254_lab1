package comp245lab1;

import org.junit.jupiter.api.Test;

import static comp245lab1.LinkedListTest.assertListEmpty;
import static comp245lab1.LinkedListTest.assertListEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for the concatenation method of {@link LinkedList} (Exercise 1).
 */
public interface ConcatenatableLinkedListTest extends LinkedListTest {

    ConcatenatableLinkedList<String> createLinkedList();

    /**
     * This method tests the concatenate method of the linked list. It tests
     * all the major sunny day scenarios of the method.
     */
    @Test
    default void testConcatenate() {
        // Create a linked list and add some elements to it
        ConcatenatableLinkedList<String> linkedList = createLinkedList();
        linkedList.add(0, "A");
        linkedList.add(1, "B");

        // Create a new linked list and add some elements to it
        LinkedList<String> otherList = createLinkedList();
        otherList.add(0, "C");
        otherList.add(1, "D");

        // Concatenate the other list to the linked list
        LinkedList<String> result = linkedList.concatenate(otherList);

        // Assert that the result is the same object as the linked list
        assertSame(result, linkedList);

        // Assert that the result has the elements of both lists being
        // concatenated in the correct order
        assertListEquals(result, "A", "B", "C", "D");
        assertListEquals(linkedList, "A", "B", "C", "D");

        // Assert that otherList is emptied, i.e. all its nodes are moved to
        // linkedList
        assertListEmpty(otherList);
    }

    /**
     * This method tests if the concatenate method throws a NullPointerException
     * when the specified list to concatenate is null.
     */
    @Test
    default void testConcatenateWithNullList() {
        ConcatenatableLinkedList<String> linkedList = createLinkedList();
        // Assert that the method throws a NullPointerException when the
        // specified list is null
        assertThrows(NullPointerException.class, () -> linkedList.concatenate(null));

        // Assert that the linked list is not affected with the above operation
        assertListEmpty(linkedList);

        // Add an element to the linked list to test the method again with a
        // non-empty linked list
        linkedList.addFirst("A");

        // Assert that the method throws a NullPointerException when the
        // specified list is null
        assertThrows(NullPointerException.class, () -> linkedList.concatenate(null));

        // Assert that the linked list is not affected with the above operation,
        // and it still has "A" as its only element
        assertListEquals(linkedList, "A");
    }

    /**
     * This method test if the concatenate method functions correctly when the
     * specified list to concatenate is empty, i.e. has no elements.
     */
    @Test
    default void testConcatenateEmptyList() {
        // Add some elements to the linked list
        ConcatenatableLinkedList<String> linkedList = createLinkedList();
        linkedList.addLast("A");
        linkedList.addLast("B");

        // Create an empty linked list
        LinkedList<String> otherList = createLinkedList();
        // Concatenate the empty list to the linked list
        LinkedList<String> result = linkedList.concatenate(otherList);

        // Assert that the result is the same object as the linked list
        assertSame(result, linkedList);

        // Assert that the result has the elements of both lists containing
        // the elements of the linked list in the correct order
        assertListEquals(result, "A", "B");

        // Assert that the other list is still empty
        assertListEmpty(otherList);
    }

    /**
     * This method tests if the concatenate method functions correctly when the
     * list to be concatenated is an empty list, i.e. has no elements.
     */
    @Test
    default void testConcatenateToAnEmptyList() {
        // Create an empty linked list
        ConcatenatableLinkedList<String> linkedList = createLinkedList();

        // Create a new linked list and add some elements to it
        LinkedList<String> otherList = createLinkedList();
        otherList.addLast("A");
        otherList.addLast("B");

        // Concatenate the other list to the linked list which is empty
        LinkedList<String> result = linkedList.concatenate(otherList);

        // Assert that the result is the same object as the linked list
        assertSame(result, linkedList);

        // Assert that the result has the elements of both lists containing
        // the elements of the other list in the correct order
        assertListEquals(result, "A", "B");
        assertListEquals(linkedList, "A", "B");

        // Assert that the other list is emptied, i.e. all its nodes are moved to
        // linkedList
        assertListEmpty(otherList);
    }

    /**
     * This method tests if the concatenate method throws an
     * IllegalArgumentException when the specified list to concatenate is not
     * compatible with the linked list, i.e. the specified list is not an
     * instance of the same class as the linked list.
     */
    @Test
    default void testConcatenateWithIncompatibleList() {
        // Create an empty linked list
        ConcatenatableLinkedList<String> linkedList = createLinkedList();
        // Create a new linked list of type UnknownLinkedList.
        LinkedList<String> otherList = new UnknownLinkedList<>();

        // Assert that the method throws an IllegalArgumentException when the
        // specified list to concatenate is not compatible with the linked list
        assertThrows(IllegalArgumentException.class, () -> linkedList.concatenate(otherList));

        // Assert that the linked list is not affected with the above operation,
        // and it is still empty
        assertListEmpty(linkedList);

        // Add an element to the linked list to test the method again with a
        // non-empty linked list
        linkedList.addFirst("A");

        // Assert that the method throws an IllegalArgumentException when the
        // specified list to concatenate is not compatible with the linked list
        assertThrows(IllegalArgumentException.class, () -> linkedList.concatenate(otherList));

        // Assert that the linked list is not affected with the above operation,
        assertListEquals(linkedList, "A");
    }

    /**
     * This is a dummy class used to test if the concat method throws an
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
