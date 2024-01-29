package comp245lab1.ex2;

import comp245lab1.*;
import comp245lab1.ex2.SinglyLinkedList;
import org.junit.jupiter.api.BeforeEach;

import static comp245lab1.LinkedListTest.assertListEmpty;

/**
 * All unit tests for LinkedList with {@link SinglyLinkedList} as
 * implementation.
 */
public class SinglyLinkedListTest implements
        LinkedListTest,
        ConcatenatableLinkedListTest,
        SwappableLinkedListTest,
        CloneableLinkedListTest {

    @Override
    public SinglyLinkedList<String> createLinkedList() {
        return new SinglyLinkedList<>();
    }

    @Override
    public <E> Object getNode(LinkedList<E> linkedList, int index) {
        return ((SinglyLinkedList<E>) linkedList).getNode(index);
    }

}
