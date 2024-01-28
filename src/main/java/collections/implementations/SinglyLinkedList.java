package collections.implementations;

import collections.CloneableLinkedList;
import collections.ConcatenatableLinkedList;
import collections.LinkedList;
import collections.SwappableLinkedList;

/**
 * A doubly linked list implementation of {@link LinkedList}.
 *
 * @param <E> the type of elements in this linked list
 */
public class SinglyLinkedList<E> implements LinkedList<E>, ConcatenatableLinkedList<E>, SwappableLinkedList<E>, CloneableLinkedList<E> {

    /**
     * A node in a singly linked list. It has package visibility for testing
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
         * Constructs a node with the specified element and next node.
         *
         * @param element the element to store at this node
         * @param next the next node in the list
         */
        public Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
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
     * Constructs an empty singly linked list.
     */
    public SinglyLinkedList() {
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

    /**
     * Adds the specified element after the specified node.
     * @param node the node after which to add the element
     * @param element the element to add
     */
    private void addAfter(Node<E> node, E element) {
        node.next = new Node<E>(element, node.next);
        if (node == tail) {
            tail = node.next;
        }
        this.size++;
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
        head = new Node<E>(element, head);
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
            tail.next = new Node<E>(element, null);
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
        } else {
            addAfter(getNode(index - 1), element);
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
    public E removeFirst() {
        if (head != null) {
            E element = head.element;
            head = head.next;
            if (head == null) {
                tail = null;
            }
            this.size--;
            return element;
        } else {
            return null;
        }
    }

    @Override
    public E removeLast() {
        if (tail != null) {
            if (head == tail) {
                return removeFirst();
            } else {
                Node<E> node = head;
                while (node.next != tail) {
                    node = node.next;
                }
                E element = tail.element;
                tail = node;
                tail.next = null;
                this.size--;
                return element;
            }
        } else {
            return null;
        }
    }

    @Override
    public E remove(int index) {
        checkIndex(index);
        if (index == 0) {
            return removeFirst();
        } else {
            Node<E> node = getNode(index - 1);
            E element = node.next.element;
            node.next = node.next.next;
            if (node.next == null) {
                tail = node;
            }
            this.size--;
            return element;
        }
    }

    @Override
    public SinglyLinkedList<E> concatenate(LinkedList<E> list) {
        if (list == null) {
            throw new NullPointerException();
        }
        if (!(list instanceof SinglyLinkedList)) {
            throw new IllegalArgumentException();
        }
        SinglyLinkedList<E> other = (SinglyLinkedList<E>) list;
        if (!other.isEmpty()) {
            if (this.isEmpty()) {
                head = other.head;
            } else {
                tail.next = other.head;
            }
            tail = other.tail;
            size += other.size;
            other.head = null;
            other.tail = null;
            other.size = 0;
        }
        return this;
    }

    /**
     * This is solution to Exercise 2
     */
    @Override
    public void swapNodes(int index1, int index2) {
        checkIndex(index1);
        checkIndex(index2);
        if (index1 == index2) {
            return;
        }
        int indexMin = Math.min(index1, index2);
        int indexMax = Math.max(index1, index2);
        Node<E> nodeMin = getNode(indexMin);
        Node<E> nodeMax = getNode(indexMax);
        Node<E> temp = nodeMin.next;
        nodeMin.next = nodeMax.next;
        nodeMax.next = temp;
        if (indexMin == 0) {
            head = nodeMax;
        } else {
            getNode(indexMin - 1).next = nodeMax;
        }
        getNode(indexMax - 1).next = nodeMin;
        if (indexMax == size - 1) {
            tail = nodeMin;
        }
    }

    @Override
    public SinglyLinkedList<E> clone() {
        SinglyLinkedList<E> clone = new SinglyLinkedList<>();
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

