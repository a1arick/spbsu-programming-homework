package com.spbsu.a1arick.homework2.task3.stack.impl;

import com.spbsu.a1arick.homework2.task3.exception.DataStructureIsEmptyException;
import com.spbsu.a1arick.homework2.task3.stack.Stack;
import org.junit.Test;

import static org.junit.Assert.*;

public class LinkedStackTest {
    @Test
    public void pop() throws DataStructureIsEmptyException {
        Stack stack = new LinkedStack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertEquals(3, stack.pop());
        assertEquals(2, stack.pop());
        assertEquals(1, stack.pop());
    }

    @Test(expected = DataStructureIsEmptyException.class)
    public void popDataStructureIsEmptyException() throws DataStructureIsEmptyException {
        Stack stack = new LinkedStack();
        stack.push(1);
        assertEquals(1, stack.pop());
        stack.pop();
    }

    @Test
    public void isEmpty() throws DataStructureIsEmptyException {
        Stack stack = new LinkedStack();
        stack.push(1);
        assertFalse(stack.isEmpty());
        stack.pop();
        assertTrue(stack.isEmpty());
    }
}
