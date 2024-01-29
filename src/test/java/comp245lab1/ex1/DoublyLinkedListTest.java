package comp245lab1.ex1;

import comp245lab1.*;
import comp245lab1.ex1.DoublyLinkedList;
import org.junit.jupiter.api.BeforeEach;

import static comp245lab1.LinkedListTest.assertListEmpty;

/**
 * All unit test for LinkedList with {@link DoublyLinkedList} as
 * implementation.
 */
public class DoublyLinkedListTest implements
        LinkedListTest,
        ConcatenatableLinkedListTest,
        SwappableLinkedListTest,
        CloneableLinkedListTest {

    @Override
    public DoublyLinkedList<String> createLinkedList() {
        return new DoublyLinkedList<>();
    }

    @Override
    public <E> Object getNode(LinkedList<E> linkedList, int index) {
        return ((DoublyLinkedList<E>) linkedList).getNode(index);
    }
}
