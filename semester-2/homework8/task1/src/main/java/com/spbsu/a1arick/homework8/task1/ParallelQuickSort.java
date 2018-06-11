package com.spbsu.a1arick.homework8.task1;

import java.util.concurrent.ForkJoinPool;

/**
 * Parallel quick sort algorithm
 */
public class ParallelQuickSort extends QuickSort {
    @Override
    protected void sort(int[] a, int left, int right) {
        ForkJoinPool.commonPool().invoke(new QuickSortTask(a, left, right));
    }


}
