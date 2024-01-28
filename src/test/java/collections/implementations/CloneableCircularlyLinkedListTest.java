package collections.implementations;

import collections.CloneableLinkedListTest;
import collections.LinkedListTest;
import org.junit.jupiter.api.BeforeEach;

import static collections.LinkedListTest.assertListEmpty;

public class CloneableCircularlyLinkedListTest implements LinkedListTest, CloneableLinkedListTest {

    private CloneableCircularlyLinkedList<String> linkedList;

    @Override
    public CloneableCircularlyLinkedList<String> linkedList() {
        return linkedList;
    }

    @Override
    public <E> CloneableCircularlyLinkedList<E> createLinkedList() {
        return new CloneableCircularlyLinkedList<>();
    }

    @BeforeEach
    void setUp() {
        linkedList = createLinkedList();
        assertListEmpty(linkedList);
    }
}
