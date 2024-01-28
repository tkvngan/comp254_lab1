package collections.implementations;

import collections.*;
import org.junit.jupiter.api.BeforeEach;

import static collections.LinkedListTest.assertListEmpty;

/**
 * All unit tests for LinkedList with {@link SinglyLinkedList} as
 * implementation.
 */
public class SinglyLinkedListTest implements
        LinkedListTest,
        ConcatenatableLinkedListTest,
        SwappableLinkedListTest,
        CloneableLinkedListTest {

    private SinglyLinkedList<String> linkedList;

    @Override
    public SinglyLinkedList<String> linkedList() {
        return linkedList;
    }

    @Override
    public <E> SinglyLinkedList<E> createLinkedList() {
        return new SinglyLinkedList<>();
    }

    @Override
    public <E> Object getNode(LinkedList<E> linkedList, int index) {
        return ((SinglyLinkedList<E>) linkedList).getNode(index);
    }

    @BeforeEach
    void setUp() {
        linkedList = createLinkedList();
        assertListEmpty(linkedList);
    }
}
