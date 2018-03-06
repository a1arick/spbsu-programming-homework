package com.spbsu.a1arick.homework2.task2;

import org.junit.Test;

import static org.junit.Assert.*;

public class AbstractSpiralAlgTest {
    @Test
    public void printToString() {
        int[][] arr = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        SpiralAlgString spiralAlgString = new SpiralAlgString();
        spiralAlgString.вывести(arr);
        assertEquals("5 2 3 6 9 8 7 4 1 ", spiralAlgString.getOutput());
    }
}