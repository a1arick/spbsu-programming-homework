package com.spbsu.a1arick.homework1.task1;

import org.junit.Test;

import static org.junit.Assert.*;

public class MyStackTest {
    @Test
    public void testPush() {
        MyStack stack = new MyStack();
        int n = 10;
        for (int i = 0; i < n; i++)
            stack.push(i);

        assertFalse(stack.isEmpty());
        assertEquals(n,stack.size());
    }

    @Test
    public void testIsEmpty() {
        MyStack stack = new MyStack();
        assertTrue(stack.isEmpty());
        stack.push(5);
        assertFalse(stack.isEmpty());
    }

    @Test
    public void testPop() {
        MyStack stack = new MyStack();
        int n = 10;
        for (int i = 0; i < n; i++)
            stack.push(i);
        for (int i = 0; i < n; i++)
            assertEquals(n - i - 1, stack.pop());
    }
}