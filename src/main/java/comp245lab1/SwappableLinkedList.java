package comp245lab1;

/**
 * A linked list implementation of the {@link LinkedList} interface that
 * supports swapping elements.
 *
 * @param <E> the type of elements in the linked list
 */
public interface SwappableLinkedList<E> extends LinkedList<E> {

    /**
     * Swaps the elements at the specified positions in the linked list. If the
     * specified index positions are the same, invoking this method leaves the
     * linked list unchanged.
     *
     * @param index1 the index of one element to be swapped
     * @param index2 the index of the other element to be swapped
     * @throws IndexOutOfBoundsException if either index is out of range
     */
    void swapNodes(int index1, int index2);
}
