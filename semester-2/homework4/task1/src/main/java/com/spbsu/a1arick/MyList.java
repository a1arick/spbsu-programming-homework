package com.spbsu.a1arick;

public class MyList<T> {
    private Node<T> head = null;

    private int size = 0;

    public void add(T x) {
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

    public void remove(T x) {
        Node<T> cur = head;
        Node<T> prev = head;

        if (x.equals(head.value)) {
            head = head.next;
            return;
        }

        while (!x.equals(cur.value)) {
            prev = cur;
            cur = cur.next;
            if(cur == null)
                return;
        }
        prev.next = cur.next;
        size--;
    }

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

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return head == null;
    }

    private static class Node<K> {
        private K value;
        private Node<K> next = null;

        private Node (K value) {
            this.value = value;
        }
    }
}
