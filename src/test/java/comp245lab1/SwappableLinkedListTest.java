package comp245lab1;

import org.junit.jupiter.api.Test;

import static comp245lab1.LinkedListTest.assertListEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for the swap method of {@link LinkedList} (Exercise 2).
 */
public interface SwappableLinkedListTest extends LinkedListTest {

    /**
     * Returns the node at the specified index in the linked list. Subclasses
     * must override this method to return the node object at the specified
     * index in the linked list.
     *
     * @return the node object at the specified index in the linked list.
     */
    <E> Object getNode(LinkedList<E> linkedList, int index);

    SwappableLinkedList<String> createLinkedList();

    /**
     * This method tests the swapNodes method of the linked list. It tests all
     * the major sunny day scenarios of the method.
     */
    @Test
    default void testSwap() {
        // Create and add some elements to the linked list
        SwappableLinkedList<String> linkedList = createLinkedList();
        linkedList.addLast("A");
        linkedList.addLast("B");
        linkedList.addLast("C");
        linkedList.addLast("D");
        linkedList.addLast("E");

        // Obtain the nodes at the specified indices
        Object node1Before = getNode(linkedList, 1);
        Object node3Before = getNode(linkedList, 3);

        // Swap the nodes at the specified indices
        linkedList.swapNodes(1, 3);

        // Obtain the nodes at the specified indices
        Object node1After = getNode(linkedList, 1);
        Object node3After = getNode(linkedList, 3);

        // Assert that the nodes at the specified indices have been swapped
        // correctly
        assertListEquals(linkedList, "A", "D", "C", "B", "E");

        // Assert that the nodes at the specified indices are the same objects
        // before and after the swap
        assertSame(node1Before, node3After);
        assertSame(node3Before, node1After);
    }

    /**
     * This method tests if the swapNodes method works correctly when the head
     * and tail nodes are swapped.
     */
    @Test
    default void testSwapHeadWithTail() {
        SwappableLinkedList<String> linkedList = createLinkedList();
        linkedList.addLast("A");
        linkedList.addLast("B");
        linkedList.addLast("C");
        linkedList.addLast("D");
        linkedList.addLast("E");
        Object node0Before = getNode(linkedList, 0);
        Object node4Before = getNode(linkedList, 4);
        linkedList.swapNodes(0, 4);
        Object node0After = getNode(linkedList, 0);
        Object node4After = getNode(linkedList, 4);
        assertListEquals(linkedList, "E", "B", "C", "D", "A");
        assertSame(node0Before, node4After);
        assertSame(node4Before, node0After);
    }

    /**
     * This method tests if the swapNodes method works correctly when the head
     * node is swapped with a non-tail node.
     */
    @Test
    default void testSwapHeadWithNonTail() {
        SwappableLinkedList<String> linkedList = createLinkedList();
        linkedList.addLast("A");
        linkedList.addLast("B");
        linkedList.addLast("C");
        linkedList.addLast("D");
        linkedList.addLast("E");
        Object node0Before = getNode(linkedList, 0);
        Object node3Before = getNode(linkedList, 3);
        linkedList.swapNodes(0, 3);
        Object node0After = getNode(linkedList, 0);
        Object node3After = getNode(linkedList, 3);
        assertListEquals(linkedList, "D", "B", "C", "A", "E");
        assertSame(node0Before, node3After);
        assertSame(node3Before, node0After);
    }

    /**
     * This method tests if the swapNodes method works correctly when a non-head
     * node is swapped with the tail node.
     */
    @Test
    default void testSwapNonHeadWithTail() {
        SwappableLinkedList<String> linkedList = createLinkedList();
        linkedList.addLast("A");
        linkedList.addLast("B");
        linkedList.addLast("C");
        linkedList.addLast("D");
        linkedList.addLast("E");
        Object node2Before = getNode(linkedList, 2);
        Object node4Before = getNode(linkedList, 4);
        linkedList.swapNodes(2, 4);
        Object node2After = getNode(linkedList, 2);
        Object node4After = getNode(linkedList, 4);
        assertListEquals(linkedList, "A", "B", "E", "D", "C");
        assertSame(node2Before, node4After);
        assertSame(node4Before, node2After);
    }

    /**
     * This method tests if the swapNodes does not change the linked list when
     * the same index is specified for both indices.
     */
    @Test
    default void testSwapSameIndex() {
        SwappableLinkedList<String> linkedList = createLinkedList();
        linkedList.addLast("A");
        linkedList.addLast("B");
        linkedList.addLast("C");
        Object node1Before = getNode(linkedList, 1);
        linkedList.swapNodes(1, 1);
        Object node1After = getNode(linkedList, 1);
        assertListEquals(linkedList, "A", "B", "C");
        assertSame(node1Before, node1After);
    }

    /**
     * This method tests if the swapNodes method throws an
     * IndexOutOfBoundsException when an invalid index is specified.
     */
    @Test
    default void testSwapInvalidIndex() {
        SwappableLinkedList<String> linkedList = createLinkedList();
        linkedList.addLast("A");
        linkedList.addLast("B");
        linkedList.addLast("C");
        assertThrows(IndexOutOfBoundsException.class, () -> linkedList.swapNodes(-1, 1));
        assertThrows(IndexOutOfBoundsException.class, () -> linkedList.swapNodes(1, -1));
        assertThrows(IndexOutOfBoundsException.class, () -> linkedList.swapNodes(3, 1));
        assertThrows(IndexOutOfBoundsException.class, () -> linkedList.swapNodes(1, 3));
    }

    /**
     * This method tests if the swapNodes method throws an
     * IndexOutOfBoundsException when an invalid index is specified for an empty
     * linked list.
     */
    @Test
    default void testSwapEmptyList() {
        SwappableLinkedList<String> linkedList = createLinkedList();
        assertThrows(IndexOutOfBoundsException.class, () -> linkedList.swapNodes(0, 0));
    }

    /**
     * This method tests if the swapNodes method does not change the linked list
     * when a single element list is specified.
     */
    @Test
    default void testSwapSingleElementList() {
        SwappableLinkedList<String> linkedList = createLinkedList();
        linkedList.addLast("A");
        Object node0Before = getNode(linkedList, 0);
        linkedList.swapNodes(0, 0);
        Object node0After = getNode(linkedList, 0);
        assertListEquals(linkedList, "A");
        assertSame(node0Before, node0After);
    }

}
