package comp245lab1.ex3;

import comp245lab1.CloneableLinkedList;
import comp245lab1.LinkedList;
import linkedlists.CircularlyLinkedList;

/**
 * A circularly linked list implementation of the {@link LinkedList} interface
 * that supports cloning. This implementation extends the
 * {@link CircularlyLinkedList} class given in Lesson3Examples. It emulates the
 * behavior of the {@link LinkedList} using methods inherited from the original
 * {@link CircularlyLinkedList} class, such as first(), last(), addFirst(),
 * addLast(), removeFirst(), and rotate().
 * This class supports the cloning operation by implementing the
 * {@link CloneableLinkedList} interface.
 */
public class CloneableCircularlyLinkedList<E> extends CircularlyLinkedList<E>
        implements LinkedList<E>, CloneableLinkedList<E> {

    /**
     * Exercise 3: Method which returns a deep copy of this linked list. By
     * "deep copy", it means that the linked list returned by this method
     * contains new nodes with the same element values. However, the element
     * values are simply copied. Therefore, if the element values are mutable,
     * the linked list returned by this method is considered to be shallow to
     * some extent. However, if the element values are immutable, for example,
     * String, or primitive wrapper classes, the linked list returned by this
     * method can be considered a deep copy of this linked list.
     *
     * @return a deep copy of this linked list
     */
    @Override
    public CloneableCircularlyLinkedList<E> clone() {
        // Create a new empty list of this class
        CloneableCircularlyLinkedList<E> other = new CloneableCircularlyLinkedList<>();

        // By rotating this list one step at a time, each element can be
        // accessed at the front of the list, and is added to the end of the
        // other list.
        for (int i = 0; i < size(); i++) {
            other.addLast(first());
            rotate();
        }
        // When the loop terminates, this list is rotated back to its original
        // position leaving it as if nothing has happened.

        // Return the other list which is a deep copy of this list.
        return other;
    }

    /**
     * Constructs an empty circularly linked list.
     */
    public CloneableCircularlyLinkedList() {
        super();
    }

    /**
     * Checks whether the specified index is valid.
     *
     * @param index the index to check
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    private void checkIndex(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        int size = size();
        // rotate the list so that the element at the specified index is at the
        // front of the list
        for (int i = 0; i < index; i++) {
            rotate();
        }
        // get the element at the front of the list, which is the element at the
        // specified index
        E element = first();

        // rotate the list back to its original position
        for (int i = 0; i < size - index; i++) {
            rotate();
        }
        return element;
    }

    @Override
    public void add(int index, E element) {
        int size = size();
        if (index != size) {
            checkIndex(index);
        }
        // rotate the list so that the element at the specified index is at the
        // front of the list
        for (int i = 0; i < index; i++) {
            rotate();
        }
        // add the element at the front of the list. This is the same as adding the element at the
        // specified index.
        addFirst(element);

        // rotate the list back to its original position
        for (int i = 0; i < size - index + 1; i++) {
            rotate();
        }
    }

    @Override
    public E removeLast() {
        if (isEmpty()) {
            return null;
        }
        int size = size();
        // rotate the list so that the last element is at the front of the list
        for (int i = 0; i < size - 1; i++) {
            rotate();
        }
        // remove the element at the front of the list, which is the last element
        return removeFirst();
    }

    @Override
    public E remove(int index) {
        checkIndex(index);
        int size = size();
        // rotate the list so that the element at the specified index is at the
        // front of the list
        for (int i = 0; i < index; i++) {
            rotate();
        }
        // remove the element at the front of the list, which is the element at the
        // specified index
        E element = removeFirst();

        // rotate the list back to its original position
        for (int i = 0; i < size - index - 1; i++) {
            rotate();
        }
        // return the element that was removed
        return element;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
