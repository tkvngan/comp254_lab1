package comp245lab1.ex2;

import comp245lab1.CloneableLinkedList;
import comp245lab1.ConcatenatableLinkedList;
import comp245lab1.LinkedList;
import comp245lab1.SwappableLinkedList;

/**
 * A singly linked list implementation of {@link LinkedList}. This class
 * supports concatenation, swapping, and cloning operations.
 *
 */
public class SinglyLinkedList<E> implements LinkedList<E>,
        ConcatenatableLinkedList<E>,
        SwappableLinkedList<E>,
        CloneableLinkedList<E> {

    /**
     * Exercise 2: Method to swap the elements at the specified positions in the
     * linked list. If the specified index positions are the same, invoking this
     * method leaves the linked list unchanged.
     * @param index1 the index of one element to be swapped
     * @param index2 the index of the other element to be swapped
     * @throws IndexOutOfBoundsException if either index is out of range
     */
    @Override
    public void swapNodes(int index1, int index2) {
        // Ensure that the specified indices are valid
        checkIndex(index1);
        checkIndex(index2);

        // If the specified indices are the same, do nothing and return
        if (index1 == index2) {
            return;
        }

        // Determine the lower and higher indices, and the nodes at those indices
        int indexLower = Math.min(index1, index2);
        int indexHigher = Math.max(index1, index2);
        Node<E> nodeLower = getNode(indexLower);
        Node<E> nodeHigher = getNode(indexHigher);

        // Swap the nodes so that the lower node is now at the higher index
        // and the higher node is now at the lower index
        Node<E> temp = nodeLower.next;
        nodeLower.next = nodeHigher.next;
        nodeHigher.next = temp;


        // If the lower index is 0, it means the previously higher node now
        // become the head of the list
        if (indexLower == 0) {
            head = nodeHigher;
        } else {
            // Otherwise, it becomes the next node of the node at the
            // position right before the lower index
            getNode(indexLower - 1).next = nodeHigher;
        }

        // Update the next node of the node at the position right before the
        // higher index to be the previously lower node
        getNode(indexHigher - 1).next = nodeLower;

        // If the higher index is the last index, it means the previously
        // lower node now become the tail of the list
        if (indexHigher == size - 1) {
            tail = nodeLower;
        }
    }

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
     *
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

