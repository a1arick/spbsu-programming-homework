package com.spbsu.a1arick.homework5.task3;

import com.spbsu.a1arick.homework5.task3.exception.UnknownOperationException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ControllerTest {

    private Controller controller;

    @Before
    public void setUp() {
        controller = new Controller();
    }

    @Test
    public void sum() throws UnknownOperationException {
        assertEquals(3L, controller.calculate(3L, "+"));
        assertEquals(10L, controller.calculate(7L, "+"));
    }

    @Test
    public void subtract() throws UnknownOperationException {
        assertEquals(-3L, controller.calculate(3L, "-"));
        assertEquals(-10L, controller.calculate(7L, "-"));
    }

    @Test
    public void multiply() throws UnknownOperationException {
        assertEquals(3L, controller.calculate(3L, "+"));
        assertEquals(21L, controller.calculate(7L, "*"));
    }

    @Test
    public void divide() throws UnknownOperationException {
        assertEquals(4L, controller.calculate(4L, "+"));
        assertEquals(2L, controller.calculate(2L, "/"));
    }
}