package com.spbsu.a1arick.homework2.task3;

import org.junit.Test;

import java.util.EmptyStackException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ArrayStackTest {

    @Test
    public void pop() throws DataStructureIsEmptyException {
        Stack stack = new ArrayStack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertEquals(3, stack.pop());
        assertEquals(2, stack.pop());
        assertEquals(1, stack.pop());
    }

    @Test(expected = DataStructureIsEmptyException.class)
    public void popDataStructureIsEmptyException() throws DataStructureIsEmptyException {
        Stack stack = new ArrayStack();
        stack.push(1);
        assertEquals(1, stack.pop());
        stack.pop();
    }

    @Test
    public void isEmpty() throws DataStructureIsEmptyException {
        Stack stack = new ArrayStack();
        stack.push(1);
        assertFalse(stack.isEmpty());
        stack.pop();
        assertTrue(stack.isEmpty());
    }

}
