package com.spbsu.a1arick.homework6.task2;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ControllerTest {

    private Controller controller = new Controller(3);

    @Test
    public void canMakeTurn() {
        assertTrue(controller.canMakeTurn(0, 0));
        assertFalse(controller.canMakeTurn(-1, 0));
        assertFalse(controller.canMakeTurn(5, 5));

        controller.makeTurn(0, 0);
        assertFalse(controller.canMakeTurn(0, 0));

        controller.makeTurn(0, 1);
        controller.makeTurn(0, 2);
        controller.makeTurn(1, 0);
        controller.makeTurn(1, 1);
        controller.makeTurn(1, 2);
        controller.makeTurn(2, 1);
        controller.makeTurn(2, 0);
        controller.makeTurn(2, 2);
        assertFalse(controller.canMakeTurn(0, 0));
    }

    @Test
    public void clear() {
        controller.makeTurn(0, 0);
        controller.makeTurn(0, 1);
        controller.makeTurn(0, 2);
        controller.makeTurn(1, 0);
        controller.makeTurn(1, 1);
        controller.makeTurn(1, 2);
        controller.makeTurn(2, 1);
        controller.makeTurn(2, 0);
        controller.makeTurn(2, 2);
        assertFalse(controller.canMakeTurn(0, 0));
        controller.clear();
        assertTrue(controller.canMakeTurn(0, 0));
        assertTrue(controller.canMakeTurn(0, 1));
        assertTrue(controller.canMakeTurn(1, 2));
        assertTrue(controller.canMakeTurn(1, 0));
        assertTrue(controller.canMakeTurn(1, 0));
        assertTrue(controller.canMakeTurn(2, 1));
        assertTrue(controller.canMakeTurn(2, 0));
        assertTrue(controller.canMakeTurn(2, 2));
    }

    @Test
    public void oWin() {
        controller.makeTurn(0, 0);
        controller.makeTurn(1, 1);
        controller.makeTurn(0, 1);
        controller.makeTurn(1, 2);
        controller.makeTurn(0, 2);
        assertTrue(controller.check(1));
    }

    @Test
    public void xWin() {
        controller.makeTurn(2, 1);
        controller.makeTurn(1, 1);
        controller.makeTurn(1, 2);
        controller.makeTurn(0, 0);
        controller.makeTurn(2, 0);
        controller.makeTurn(2, 2);
        assertTrue(controller.check(2));
    }
}