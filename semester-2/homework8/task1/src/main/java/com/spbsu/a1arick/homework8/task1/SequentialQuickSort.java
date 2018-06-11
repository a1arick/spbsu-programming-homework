package com.spbsu.a1arick.homework8.task1;

/**
 * Sequential quick sort algorithm
 */
public class SequentialQuickSort extends QuickSort {

    @Override
    protected void sort(int[] a, int left, int right) {
        int index = partition(a, left, right);
        if (left < index - 1) {
            sort(a, left, index - 1);
        }
        if (index < right) {
            sort(a, index, right);
        }
    }
}
