package com.spbsu.a1arick.homework2.task3;

public class Node {
    private char operation = ' ';
    private int number;

    public Node (int number) {
        this.number = number;
    }

    public Node (char operation){
        this.operation = operation;
    }

    public char getOperation() {
        return operation;
    }

    public int getNumber() {
        return number;
    }

    public boolean isOperation(){
        return isOperation(operation);
    }

    public static boolean isOperation(char c){
        return c == '+' || c == '-' || c == '*' || c =='/';
    }
}
