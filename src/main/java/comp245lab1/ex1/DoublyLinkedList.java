package comp245lab1.ex1;

import comp245lab1.CloneableLinkedList;
import comp245lab1.ConcatenatableLinkedList;
import comp245lab1.LinkedList;
import comp245lab1.SwappableLinkedList;

/**
 * A doubly linked list implementation of the {@link LinkedList} interface. This
 * class supports concatenation, swapping, and cloning.
 */
public class DoublyLinkedList<E> implements LinkedList<E>,
        ConcatenatableLinkedList<E>,
        SwappableLinkedList<E>,
        CloneableLinkedList<E> {

    /**
     * Exercise 1: Method to concatenate the specified linked list to the end of
     * this linked list, and returns this linked list. The specified list is
     * emptied (size is 0) as a result of this operation. The reason is that
     * nodes of one list cannot be shared with another list; otherwise, updating
     * one of the lists will corrupt the other list.
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
    @Override
    public DoublyLinkedList<E> concatenate(LinkedList<E> list) {
        // Ensure that the specified list is not null
        if (list == null) {
            throw new NullPointerException();
        }
        // Ensure that the specified list is an instance of DoublyLinkedList
        if (!(list instanceof DoublyLinkedList)) {
            throw new IllegalArgumentException();
        }

        // Cast the specified list to DoublyLinkedList
        DoublyLinkedList<E> other = (DoublyLinkedList<E>) list;

        // If the specified list is empty, return this list as its return value
        if (other.isEmpty()) {
            return this;
        }

        // If this list is empty, set this list's head to the specified list's
        // head.
        if (this.head == null) {
            this.head = other.head;
        } else {
            // Otherwise, concatenate the specified list to the end of this
            // list by setting this list's tail.next to the specified list's
            // head, and setting the specified list's head.prev to this list's
            // tail.
            this.tail.next = other.head;
            other.head.prev = this.tail;
        }

        // Set this list's tail to the specified list's tail, and update the
        // size of this list
        this.tail = other.tail;
        this.size += other.size;

        // Set the specified list's head and tail to null, and size to 0. This
        // is necessary because nodes of one list cannot be shared with another
        // list; otherwise, updating one of the lists will corrupt the other.
        other.head = null;
        other.tail = null;
        other.size = 0;

        // Return this list as its return value
        return this;
    }

    /**
     * A node in a doubly linked list. It has package visibility for testing
     * purposes.
     */
    static class Node<E> {

        /**
         * The element stored at this node.
         */
        final E element;

        /**
         * Reference to the next node in the list.
         */
        Node<E> next;

        /**
         * Reference to the previous node in the list.
         */
        Node<E> prev;

        /**
         * Constructs a node with the specified element, next node, and previous
         * node.
         *
         * @param element the element to store at this node
         * @param next the next node in the list
         * @param prev the previous node in the list
         */
        public Node(E element, Node<E> next, Node<E> prev) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }
    }

    /**
     * Reference to the head node of this linked list. It has package visibility
     * for testing purposes.
     */
    Node<E> head = null;

    /**
     * Reference to the tail node of this linked list. It has package visibility
     * for testing purposes.
     */
    Node<E> tail = null;

    /**
     * The number of elements in this linked list. It has package visibility for
     * testing purposes.
     */
    int size = 0;

    /**
     * Constructs an empty doubly linked list.
     */
    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * Checks whether the specified index is valid.
     *
     * @param index the index to check
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    private void checkIndex(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    Node<E> getNode(int index) {
        checkIndex(index);
        Node<E> node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E first() {
        return head != null ? head.element : null;
    }

    @Override
    public E last() {
        return tail != null ? tail.element : null;
    }

    @Override
    public E get(int index) {
        return getNode(index).element;
    }

    @Override
    public void addFirst(E element) {
        head = new Node<E>(element, head, null);
        if (tail == null) {
            tail = head;
        }
        this.size++;
    }

    @Override
    public void addLast(E element) {
        if (tail == null) {
            addFirst(element);
        } else {
            tail.next = new Node<E>(element, null, tail);
            tail = tail.next;
            this.size++;
        }
    }

    @Override
    public void add(int index, E element) {
        if (index != size) {
            checkIndex(index);
        }
        if (index == 0) {
            addFirst(element);
        } else if (index == size) {
            addLast(element);
        } else {
            Node<E> node = getNode(index - 1);
            Node<E> newNode = new Node<E>(element, node.next, node);
            node.next = newNode;
            newNode.next.prev = newNode;
            this.size++;
        }
    }

    @Override
    public E removeFirst() {
        if (head == null) {
            return null;
        }
        E element = head.element;
        head = head.next;
        if (head == null) {
            tail = null;
        } else {
            head.prev = null;
        }
        this.size--;
        return element;
    }

    @Override
    public E removeLast() {
        if (tail == null) {
            return null;
        }
        E element = tail.element;
        tail = tail.prev;
        if (tail == null) {
            head = null;
        } else {
            tail.next = null;
        }
        this.size--;
        return element;
    }

    @Override
    public E remove(int index) {
        checkIndex(index);
        if (index == 0) {
            return removeFirst();
        } else if (index == size - 1) {
            return removeLast();
        } else {
            Node<E> node = getNode(index - 1);
            E element = node.next.element;
            node.next = node.next.next;
            node.next.prev = node;
            this.size--;
            return element;
        }
    }

    @Override
    public void swapNodes(int index1, int index2) {
        checkIndex(index1);
        checkIndex(index2);
        if (index1 == index2) {
            return;
        }
        final int indexLower = Math.min(index1, index2);
        final int indexUpper = Math.max(index1, index2);
        final DoublyLinkedList.Node<E> nodeLower = getNode(indexLower);
        final DoublyLinkedList.Node<E> nodeUpper = getNode(indexUpper);
        DoublyLinkedList.Node<E> temp = nodeLower.next;
        nodeLower.next = nodeUpper.next;
        nodeUpper.next = temp;
        temp = nodeLower.prev;
        nodeLower.prev = nodeUpper.prev;
        nodeUpper.prev = temp;
        nodeUpper.next.prev = nodeUpper;
        nodeLower.prev.next = nodeLower;
        if (nodeLower.next != null) {
            nodeLower.next.prev = nodeLower;
        }
        if (nodeUpper.prev != null) {
            nodeUpper.prev.next = nodeUpper;
        }
        if (indexLower == 0) {
            head = nodeUpper;
        }
        if (indexUpper == size - 1) {
            tail = nodeLower;
        }
    }

    @Override
    public DoublyLinkedList<E> clone() {
        DoublyLinkedList<E> clone = new DoublyLinkedList<>();
        Node<E> node = head;
        while (node != null) {
            clone.addLast(node.element);
            node = node.next;
        }
        return clone;
    }

    @Override
    public String toString() {
        return LinkedList.toString(this);
    }

}
