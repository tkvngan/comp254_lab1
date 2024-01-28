package collections.implementations;

import collections.*;
import org.junit.jupiter.api.BeforeEach;

import static collections.LinkedListTest.assertListEmpty;

/**
 * All unit test for LinkedList with {@link DoublyLinkedList} as
 * implementation.
 */
public class DoublyLinkedListTest implements
        LinkedListTest,
        ConcatenatableLinkedListTest,
        SwappableLinkedListTest,
        CloneableLinkedListTest {

    private DoublyLinkedList<String> linkedList;

    @Override
    public <E> DoublyLinkedList<E> createLinkedList() {
        return new DoublyLinkedList<>();
    }

    @Override
    public <E> Object getNode(LinkedList<E> linkedList, int index) {
        return ((DoublyLinkedList<E>) linkedList).getNode(index);
    }

    @Override
    public DoublyLinkedList<String> linkedList() {
        return linkedList;
    }

    @BeforeEach
    void setUp() {
        linkedList = createLinkedList();
        assertListEmpty(linkedList);
    }
}
