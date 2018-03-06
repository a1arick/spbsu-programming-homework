package com.spbsu.a1arick.homework.task2;

public class MyList {
    private class Node {
        private int value;
        Node next;

        Node (int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    private int size = 0;
    private Node head = null;

    public void add(int x){
        Node node = new Node (x, null);
        if (head == null) {
            head = node;
        } else {
            Node cur = head;

            while (cur.next != null) {
                cur = cur.next;
            }
            cur.next = node;
        }
        size++;
    }

    public int remove (int i) {
        if (i < 0 || i >= size) {
            return Integer.MIN_VALUE;
        }

        Node cur = head;
        Node prev = head;

        if (i == 0) {
            int n = head.value;
            head = head.next;
            return n;
        }

        while (i > 0) {
            prev = cur;
            cur = cur.next;
            i--;
        }
        int n = cur.value;
        prev.next = cur.next;
        size--;
        return n;
    }

    public int get (int i) {
        if (i < 0 || i >= size) {
            return Integer.MIN_VALUE;
        }

        Node x = head;

        while (i > 0) {
            x = x.next;
            i--;
        }

        return x.value;
    }

    public int size(){
        return size;
    }
}
