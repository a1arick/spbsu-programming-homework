package com.spbsu.a1arick.homework8.task1;

/**
 * Abstract class for quick sort algorithm
 */
public abstract class QuickSort {

    static int partition(int[] a, int left, int right) {
        int i = left, j = right;
        int tmp;
        int pivot = a[(left + right) / 2];
        while (i <= j) {
            while (a[i] < pivot) {
                i++;
            }
            while (a[j] > pivot) {
                j--;
            }
            if (i <= j) {
                tmp = a[i];
                a[i] = a[j];
                a[j] = tmp;
                i++;
                j--;
            }
        }
        return i;
    }

    protected abstract void sort(int[] a, int left, int right);

    /**
     * Sorts array using quick sort algorithm
     * @param a array to sort
     */
    public void sort(int[] a) {
        if (a == null || a.length == 0) return;
        sort(a, 0, a.length - 1);
    }
}
