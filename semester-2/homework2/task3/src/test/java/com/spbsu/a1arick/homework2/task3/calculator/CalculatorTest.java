package com.spbsu.a1arick.homework2.task3.calculator;

import com.spbsu.a1arick.homework2.task3.stack.Stack;
import com.spbsu.a1arick.homework2.task3.stack.impl.ArrayStack;
import com.spbsu.a1arick.homework2.task3.stack.impl.LinkedStack;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalculatorTest {

    @Test
    public void arrayStackCalculator() {
        Stack stack = new ArrayStack();
        assertEquals(0, Calculator.solve("2 2 -", stack));
        assertEquals(1, Calculator.solve("2 2 /", stack));
    }

    @Test
    public void linkedStackCalculator() {
        Stack stack = new LinkedStack();
        assertEquals(0, Calculator.solve("2 2 -", stack));
        assertEquals(1, Calculator.solve("2 2 /", stack));
    }

}