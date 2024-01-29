package comp245lab1;

/**
 * A generic interface representing a linked list. Concrete classes should
 * implement this interface to create specific implementations of linked lists.
 *
 * @param <E> the type of elements in the linked list
 */
public interface LinkedList<E> {

    /**
     * @return the number of elements in the linked list
     */
    int size();

    /**
     * @return the first element of the linked list or null if empty
     */
    E first();

    /**
     * @return the last element of the linked list or null if empty
     */
    E last();

    /**
     * Returns the element at the specified position in the linked list.
     *
     * @param index the index of the element to return
     * @return the element at the specified position in the linked list
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    E get(int index);

    /**
     * Tests whether the linked list is empty.
     *
     * @return true if the linked list is empty, false otherwise
     */
    boolean isEmpty();

    /**
     * Adds the specified element to the front of the linked list.
     *
     * @param element the element to add
     */
    void addFirst(E element);

    /**
     * Adds the specified element to the end of the linked list.
     *
     * @param element the element to add
     */
    void addLast(E element);

    /**
     * Adds the specified element at the specified position in the linked list.
     *
     * @param index the index at which the specified element is to be
     *         inserted
     * @param element the element to add
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    void add(int index, E element);

    /**
     * Removes and returns the first element of the linked list or null if
     * empty.
     *
     * @return the first element of the linked list or null if empty
     */
    E removeFirst();

    /**
     * Removes and returns the last element of the linked list or null if
     * empty.
     *
     * @return the last element of the linked list or null if empty
     */
    E removeLast();

    /**
     * Removes and returns the element at the specified position in the linked
     * list.
     *
     * @param index the index of the element to be removed
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    E remove(int index);

    /**
     * Static method to return a string representation of the specified linked
     * list.
     *
     * @param list the linked list to be converted to a string
     * @return a string representation of the specified linked list
     */
    static <E> String toString(LinkedList<E> list) {
        StringBuilder builder = new StringBuilder();
        builder.append("(");
        int size = list.size();
        for (int i = 0; i < size; i++) {
            builder.append(list.get(i));
            if (i < size - 1) {
                builder.append(", ");
            }
        }
        builder.append(")");
        return builder.toString();
    }
}
