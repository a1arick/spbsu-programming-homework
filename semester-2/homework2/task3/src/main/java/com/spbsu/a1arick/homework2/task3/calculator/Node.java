package com.spbsu.a1arick.homework2.task3.calculator;

/**
 * Calculation node class
 */
class Node {
    private char operation = ' ';
    private int number;

    Node(int number) {
        this.number = number;
    }

    Node(char operation) {
        this.operation = operation;
    }

    static boolean isOperation(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    char getOperation() {
        return operation;
    }

    int getNumber() {
        return number;
    }

    boolean isOperation() {
        return isOperation(operation);
    }
}
