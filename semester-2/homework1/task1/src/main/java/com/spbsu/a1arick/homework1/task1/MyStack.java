package com.spbsu.a1arick.homework1.task1;

public class MyStack {

    private class Node{
        private int value;
        private Node next;

        private Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    private Node head = null;
    private int size = 0;

    public void push(int x){
        head = new Node(x,head);
        size++;
    }

    public int pop(){
        int res = head.value;
        head = head.next;
        size--;
        return res;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return head == null;
    }
}