package com.spbsu.a1arick.homework8.task1;

import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.*;

public class QuickSortTest {

    @Test
    public void sequential() {
        testSort(new SequentialQuickSort(), 100, 10000);
    }

    @Test
    public void parallel() {
        testSort(new ParallelQuickSort(), 100, 10000);
    }

    private void testSort (QuickSort quickSort, int cycleCount, int arraySize){
        for(int i = 0; i < cycleCount; i++) {
            int[] array = new int[arraySize];
            for (int j = 0; j < arraySize; j++) {
                array[j] = ThreadLocalRandom.current().nextInt();
            }
            int[] copyArray = Arrays.copyOf(array, arraySize);
            Arrays.sort(copyArray);
            quickSort.sort(array);
            assertArrayEquals(copyArray, array);
        }
    }

}