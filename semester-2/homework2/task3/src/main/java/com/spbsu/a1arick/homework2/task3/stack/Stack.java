package com.spbsu.a1arick.homework2.task3.stack;

import com.spbsu.a1arick.homework2.task3.exception.DataStructureIsEmptyException;

/**
 * Stack interface
 */
public interface Stack {
    /**
     * Push element to stack
     *
     * @param x element to add
     */
    void push(int x);

    /**
     * Pop element from top of the stack
     *
     * @return top element
     * @throws DataStructureIsEmptyException if the stack is empty
     */
    int pop() throws DataStructureIsEmptyException;

    /**
     * Checks if stack is empty
     *
     * @return {@code true} if stack is empty else {@code false}
     */
    boolean isEmpty();
}
