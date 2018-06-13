package com.spbsu.a1arick.homework2;

/**
 * Bubble sort algorithm
 */
public class BubbleSort implements SortAlgorithm {

    @Override
    public void sort(int[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < n - i; j++) {
                if (a[j - 1] > a[j]) {
                    int temp = a[j - 1];
                    a[j - 1] = a[j];
                    a[j] = temp;
                }
            }
        }
    }
}
