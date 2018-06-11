package com.spbsu.a1arick.homework2.task2;

import org.junit.Test;

import static org.junit.Assert.*;

public class AbstractSpiralAlgorithmTest {
    @Test
    public void testSingle() {
        int[][] arr = {{1}};
        testAlgorithm(arr, "1 ");
    }

    @Test
    public void testAlgorithm1() {
        int[][] arr = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        testAlgorithm(arr, "5 2 3 6 9 8 7 4 1 ");
    }

    @Test
    public void testAlgorithm2() {
        int[][] arr = {{1, 2, 3, 11, 8}, {4, 5, 6, 22, 33}, {7, 8, 9, 44, 55}, {7, 8, 9, 66, 77}, {7, 8, 9, 88, 99}};
        testAlgorithm(arr, "9 6 22 44 66 9 8 8 5 2 3 11 8 33 55 77 99 88 9 8 7 7 7 4 1 ");
    }

    private void testAlgorithm(int [][] arr, String expected){
        SpiralAlgorithmString spiralAlgString = new SpiralAlgorithmString();
        spiralAlgString.print(arr);
        assertEquals(expected, spiralAlgString.getOutput());

    }
}