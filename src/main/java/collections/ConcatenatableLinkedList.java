package collections;

/**
 * A cloneable linked list implementation of the {@link LinkedList} interface
 * that supports concatenation.
 */
public interface ConcatenatableLinkedList<E> extends LinkedList<E> {

    /**
     * Concatenates the specified linked list to the end of this linked list,
     * and returns this linked list. The specified list is emptied (size is 0)
     * as a result of this operation. The reason is that nodes of one list
     * cannot be shared with another list; otherwise, updating one of the lists
     * will corrupt the other list.
     *
     * @param list the linked list to concatenate to the end of this
     *         linked list. This list is emptied (size is 0) as a result of this
     *         operation.
     * @return this linked list
     * @throws NullPointerException if the specified list is null
     * @throws IllegalArgumentException if the specified list is not
     *         compatible with this list. It is usually the case when the
     *         specified list is not an instance of the same class as this
     *         list.
     */
    LinkedList<E> concatenate(LinkedList<E> list);
}
