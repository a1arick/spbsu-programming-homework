package com.spbsu.a1arick.homework2.task3;

public interface Stack {
    void push(int x);

    int pop() throws DataSructureIsEmptyExeption;

    boolean isEmpty();
}
