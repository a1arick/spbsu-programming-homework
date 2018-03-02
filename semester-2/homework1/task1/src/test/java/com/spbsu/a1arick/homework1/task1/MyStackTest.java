package com.spbsu.a1arick.homework1.task1;

import org.junit.Test;

import static org.junit.Assert.*;

public class MyStackTest {
    @Test
    public void stack() {
        MyStack stack = new MyStack();
        int n = 10;
        for (int i = 0; i < n; i++)
            stack.push(i);

        assertFalse(stack.isEmpty());
        assertEquals(n,stack.size());

        for (int i = 0; i < n; i++)
            assertEquals(n - i - 1, stack.pop());

        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
    }
}