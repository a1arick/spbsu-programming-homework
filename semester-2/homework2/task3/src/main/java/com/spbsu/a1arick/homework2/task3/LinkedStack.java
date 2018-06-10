package com.spbsu.a1arick.homework2.task3;

public class LinkedStack implements Stack {
    private int size = 0;

    private Node head = null;


    @Override
    public void push(int x) {
        head = new Node(x, head);
        size++;
    }

    @Override
    public int pop() throws DataStructureIsEmptyException {
        if (isEmpty())
            throw new DataStructureIsEmptyException("Queue is empty");
        else {
            int x = head.value;
            head = head.next;
            size--;
            return x;
        }
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private class Node {
        private int value;
        private Node next;

        private Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }

    }
}
