package collections;

import org.junit.jupiter.api.Test;

import static collections.LinkedListTest.assertListEquals;
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

    @Override
    SwappableLinkedList<String> linkedList();

    @Test
    default void testSwap() {
        linkedList().addLast("A");
        linkedList().addLast("B");
        linkedList().addLast("C");
        linkedList().addLast("D");
        linkedList().addLast("E");
        Object node1Before = getNode(linkedList(), 1);
        Object node3Before = getNode(linkedList(), 3);
        linkedList().swapNodes(1, 3);
        Object node1After = getNode(linkedList(), 1);
        Object node3After = getNode(linkedList(), 3);
        assertListEquals(linkedList(), "A", "D", "C", "B", "E");
        assertSame(node1Before, node3After);
        assertSame(node3Before, node1After);
    }

    @Test
    default void testSwapHeadWithTail() {
        linkedList().addLast("A");
        linkedList().addLast("B");
        linkedList().addLast("C");
        linkedList().addLast("D");
        linkedList().addLast("E");
        Object node0Before = getNode(linkedList(), 0);
        Object node4Before = getNode(linkedList(), 4);
        linkedList().swapNodes(0, 4);
        Object node0After = getNode(linkedList(), 0);
        Object node4After = getNode(linkedList(), 4);
        assertListEquals(linkedList(), "E", "B", "C", "D", "A");
        assertSame(node0Before, node4After);
        assertSame(node4Before, node0After);
    }

    @Test
    default void testSwapHeadWithNonTail() {
        linkedList().addLast("A");
        linkedList().addLast("B");
        linkedList().addLast("C");
        linkedList().addLast("D");
        linkedList().addLast("E");
        Object node0Before = getNode(linkedList(), 0);
        Object node3Before = getNode(linkedList(), 3);
        linkedList().swapNodes(0, 3);
        Object node0After = getNode(linkedList(), 0);
        Object node3After = getNode(linkedList(), 3);
        assertListEquals(linkedList(), "D", "B", "C", "A", "E");
        assertSame(node0Before, node3After);
        assertSame(node3Before, node0After);
    }

    @Test
    default void testSwapNonHeadWithTail() {
        linkedList().addLast("A");
        linkedList().addLast("B");
        linkedList().addLast("C");
        linkedList().addLast("D");
        linkedList().addLast("E");
        Object node2Before = getNode(linkedList(), 2);
        Object node4Before = getNode(linkedList(), 4);
        linkedList().swapNodes(2, 4);
        Object node2After = getNode(linkedList(), 2);
        Object node4After = getNode(linkedList(), 4);
        assertListEquals(linkedList(), "A", "B", "E", "D", "C");
        assertSame(node2Before, node4After);
        assertSame(node4Before, node2After);
    }

    @Test
    default void testSwapSameIndex() {
        linkedList().addLast("A");
        linkedList().addLast("B");
        linkedList().addLast("C");
        Object node1Before = getNode(linkedList(), 1);
        linkedList().swapNodes(1, 1);
        Object node1After = getNode(linkedList(), 1);
        assertListEquals(linkedList(), "A", "B", "C");
        assertSame(node1Before, node1After);
    }

    @Test
    default void testSwapInvalidIndex() {
        linkedList().addLast("A");
        linkedList().addLast("B");
        linkedList().addLast("C");
        assertThrows(IndexOutOfBoundsException.class, () -> linkedList().swapNodes(-1, 1));
        assertThrows(IndexOutOfBoundsException.class, () -> linkedList().swapNodes(1, -1));
        assertThrows(IndexOutOfBoundsException.class, () -> linkedList().swapNodes(3, 1));
        assertThrows(IndexOutOfBoundsException.class, () -> linkedList().swapNodes(1, 3));
    }

    @Test
    default void testSwapEmptyList() {
        assertThrows(IndexOutOfBoundsException.class, () -> linkedList().swapNodes(0, 0));
    }

    @Test
    default void testSwapSingleElementList() {
        linkedList().addLast("A");
        Object node0Before = getNode(linkedList(), 0);
        linkedList().swapNodes(0, 0);
        Object node0After = getNode(linkedList(), 0);
        assertListEquals(linkedList(), "A");
        assertSame(node0Before, node0After);
    }

}
