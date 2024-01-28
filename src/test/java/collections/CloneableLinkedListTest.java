package collections;

import org.junit.jupiter.api.Test;

import static collections.LinkedListTest.assertListEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

/**
 * Unit tests for the clone method of {@link LinkedList} (Exercise 3).
 */
public interface CloneableLinkedListTest extends LinkedListTest {

    /**
     * Returns the LinkedList implementation to be tested. Subclasses must
     * override this method to return the linked list to be tested.
     *
     * @return the linked list to be tested.
     */
    @Override
    CloneableLinkedList<String> linkedList();

    @Test
    default void testClone() {
        linkedList().addLast("A");
        linkedList().addLast("B");
        linkedList().addLast("C");
        LinkedList<String> clone = linkedList().clone();
        assertNotSame(linkedList(), clone);
        assertListEquals(clone, "A", "B", "C");
        linkedList().addLast("D");
        assertListEquals(linkedList(), "A", "B", "C", "D");
        assertListEquals(clone, "A", "B", "C");
    }
}
