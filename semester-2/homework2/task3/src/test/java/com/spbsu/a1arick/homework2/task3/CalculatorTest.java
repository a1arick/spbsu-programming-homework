package com.spbsu.a1arick.homework2.task3;

import org.junit.Test;

import static org.junit.Assert.*;

public class CalculatorTest {

    @Test
    public void arrayStackCalculator() throws DataStructureIsEmptyException {
        Calculator calculator = new Calculator();
        Stack stack = new ArrayStack();
        assertEquals(0, calculator.solve("2 2 -", stack));
        assertEquals(1, calculator.solve("2 2 /", stack));
    }

    @Test
    public void linkedStackCalculator() throws DataStructureIsEmptyException {
        Calculator calculator = new Calculator();
        Stack stack = new LinkedStack();
        assertEquals(0, calculator.solve("2 2 -", stack));
        assertEquals(1, calculator.solve("2 2 /", stack));
    }

}