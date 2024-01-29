package comp245lab1.ex3;

import comp245lab1.CloneableLinkedListTest;
import comp245lab1.LinkedListTest;
import comp245lab1.ex3.CloneableCircularlyLinkedList;
import org.junit.jupiter.api.BeforeEach;

import static comp245lab1.LinkedListTest.assertListEmpty;

/**
 * All unit tests for LinkedList with {@link CloneableCircularlyLinkedList} as
 * implementation.
 */
public class CloneableCircularlyLinkedListTest implements LinkedListTest, CloneableLinkedListTest {

    @Override
    public CloneableCircularlyLinkedList<String> createLinkedList() {
        return new CloneableCircularlyLinkedList<>();
    }

}
