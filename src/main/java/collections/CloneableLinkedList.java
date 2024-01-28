package collections;

/**
 * A cloneable linked list implementation of the {@link LinkedList} interface
 * that supports clone operation.
 */
public interface CloneableLinkedList<E> extends LinkedList<E>, Cloneable {

    /**
     * Returns a deep copy of this linked list. By "deep copy", it means that
     * the linked list returned by this method contains new nodes with the same
     * element values. However, the element values are simply copied and not
     * cloned. Therefore, if the element values are mutable, the linked list
     * returned by this method is in fact a shallow copy of this linked list.
     *
     * @return a deep copy of this linked list
     */
    LinkedList<E> clone();
}
