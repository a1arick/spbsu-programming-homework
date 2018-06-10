package com.spbsu.a1arick;

/**
 * List that stores only unique values
 *
 * @param <T> object type to store in this list
 */
public class MyList<T> {
    private Node<T> head = null;

    private int size = 0;

    /**
     * Adds value to the list
     *
     * @param x to add
     * @throws ElementAlreadyExistsException if value already stored in the list
     */
    public void add(T x) throws ElementAlreadyExistsException {
        Node<T> node = new Node<>(x);
        if (head == null) {
            head = node;
        } else {
            Node<T> cur = head;

            while (cur.next != null) {
                cur = cur.next;
            }
            cur.next = node;
        }
        size++;
    }

    /**
     * Removes value to the list
     *
     * @param x to remove
     * @throws ElementNotFoundException if value isn't stored in the list
     */
    public void remove(T x) throws ElementNotFoundException {
        Node<T> cur = head;
        Node<T> prev = head;

        if (x.equals(head.value)) {
            head = head.next;
            return;
        }

        while (!x.equals(cur.value)) {
            prev = cur;
            cur = cur.next;
            if (cur == null)
                return;
        }
        prev.next = cur.next;
        size--;
    }

    /**
     * @param value to contains
     * @return true if list contains value else false
     */
    public boolean contains(T value) {
        if (isEmpty())
            return false;
        Node<T> temp = head;
        while (temp != null) {
            if (value.equals(temp.value))
                return true;
            temp = temp.next;
        }
        return false;
    }

    /**
     * @return List size
     */
    public int size() {
        return size;
    }

    /**
     * @return true if List is empty else false
     */
    public boolean isEmpty() {
        return head == null;
    }

    private static class Node<K> {
        private K value;
        private Node<K> next = null;

        private Node(K value) {
            this.value = value;
        }
    }
}
