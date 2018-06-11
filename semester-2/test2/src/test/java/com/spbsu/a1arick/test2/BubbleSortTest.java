package com.spbsu.a1arick.test2;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class BubbleSortTest {

    private final SortingAlgorithm bubbleSort = new BubbleSort();

    @Test
    public void testNull() {
        assertNull(bubbleSort.sort(null, Integer::compareTo));
    }

    @Test
    public void testEmpty() {
        assertTrue(bubbleSort.sort(new ArrayList<>(), Integer::compareTo).isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullComparator() {
        bubbleSort.sort(new ArrayList<>(), null);
    }

    @Test
    public void testSortIntegers() {
        List<Integer> integers = Arrays.asList(3, 5, 2, 4, -1, 8, 4, -9, 5, 0, -2, 2, 5, 7);
        List<Integer> sorted = bubbleSort.sort(integers, Integer::compareTo);
        Collections.sort(integers);
        assertEquals(integers, sorted);
    }

    @Test
    public void testSortStrings() {
        List<String> strings = Arrays.asList("b", "a", "ab", "c", "d", "a", "sdgsdgfsdgf", "");
        List<String> sorted = bubbleSort.sort(strings, String::compareTo);
        Collections.sort(strings);
        assertEquals(strings, sorted);
    }

    @Test
    public void testSortDoubles() {
        List<Double> doubles = Arrays.asList(2.4, 1.2, 6.234, 7.5645, 0.232, -1.343, -1243124.4);
        List<Double> sorted = bubbleSort.sort(doubles, Double::compareTo);
        Collections.sort(doubles);
        assertEquals(doubles, sorted);
    }
}