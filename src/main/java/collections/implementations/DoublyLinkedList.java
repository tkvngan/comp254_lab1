package collections.implementations;

import collections.CloneableLinkedList;
import collections.ConcatenatableLinkedList;
import collections.LinkedList;
import collections.SwappableLinkedList;

/**
 * A doubly linked list implementation of the {@link LinkedList} interface.
 *
 * @param <E> the type of elements in the linked list
 */
public class DoublyLinkedList<E> implements LinkedList<E>, ConcatenatableLinkedList<E>, SwappableLinkedList<E>, CloneableLinkedList<E> {

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

    /**
     * This is solution to Exercise 1
     */
    @Override
    public DoublyLinkedList<E> concatenate(LinkedList<E> list) {
        if (list == null) {
            throw new NullPointerException();
        }
        if (!(list instanceof DoublyLinkedList)) {
            throw new IllegalArgumentException();
        }
        DoublyLinkedList<E> other = (DoublyLinkedList<E>) list;
        if (other.isEmpty()) {
            return this;
        }
        if (this.head == null) {
            this.head = other.head;
        } else {
            this.tail.next = other.head;
            other.head.prev = this.tail;
        }
        this.tail = other.tail;
        this.size += other.size;
        other.head = null;
        other.tail = null;
        other.size = 0;
        return this;
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
