package com.spbsu.a1arick.homework2.task3;

public class LinkedStack implements Stack {

    private class Node{
        private int value;
        private Node next;

        private Node(int value, Node next){
            this.value = value;
            this.next = next;
        }
    }

    private Node head = null;
    private int size = 0;

    @Override
    public void push(int x) {
        head = new Node(x,head);
        size++;
    }

    @Override
    public int pop() {
        if(isEmpty())
            throw new IllegalStateException("Queue is empty");
        else{
            int x = head.value;
            head = head.next;
            return x;
        }
    }
    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
