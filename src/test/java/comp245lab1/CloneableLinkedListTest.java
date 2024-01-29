package comp245lab1;

import org.junit.jupiter.api.Test;

import static comp245lab1.LinkedListTest.assertListEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

/**
 * Unit tests for the clone method of {@link LinkedList} (Exercise 3).
 */
public interface CloneableLinkedListTest extends LinkedListTest {

    CloneableLinkedList<String> createLinkedList();

    @Test
    default void testClone() {
        CloneableLinkedList<String> linkedList = createLinkedList();
        // Add some elements to the linked list
        linkedList.addLast("A");
        linkedList.addLast("B");
        linkedList.addLast("C");

        // Call the clone method to perform a deep copy of the linked list
        LinkedList<String> clone = linkedList.clone();

        // Assert that the clone is a different object from the linked list
        assertNotSame(linkedList, clone);

        // Assert that the clone has the same elements as the linked list
        assertListEquals(clone, "A", "B", "C");

        // Add an element to the linked list, and assert that the
        // clone is not affected
        linkedList.addLast("D");
        assertListEquals(linkedList, "A", "B", "C", "D");

        // Assert that the clone is not affected
        assertListEquals(clone, "A", "B", "C");
    }
}
