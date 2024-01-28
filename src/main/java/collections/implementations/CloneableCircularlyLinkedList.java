package collections.implementations;

import collections.CloneableLinkedList;
import collections.LinkedList;
import linkedlists.CircularlyLinkedList;

/**
 * A circularly linked list implementation of the {@link LinkedList} interface
 * that supports cloning. This implementation extends the
 * {@link CircularlyLinkedList} class given in Lesson3Examples. It emulates the
 * behavior of the {@link LinkedList} using methods inherited from the
 * {@link CircularlyLinkedList} class, such as first(), last(), addFirst(),
 * addLast(), removeFirst(), and rotate().
 */
public class CloneableCircularlyLinkedList<E> extends CircularlyLinkedList<E> implements CloneableLinkedList<E> {

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

    /**
     * This is the solution to Exercise 3.
     */
    @Override
    public CloneableCircularlyLinkedList<E> clone() {
        // create a new empty list
        CloneableCircularlyLinkedList<E> other = new CloneableCircularlyLinkedList<>();
        int size = size();
        // for each element in this list, add it to the end of the other list and then
        // rotate this list so that the next element is at the front of the list
        for (int i = 0; i < size; i++) {
            other.addLast(first());
            rotate();
        }
        return other;
    }

}
