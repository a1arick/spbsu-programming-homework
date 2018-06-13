package com.spbsu.a1arick.homework2;

/**
 * Quick sort algorithm
 */
public class QuickSort implements SortAlgorithm {
    @Override
    public void sort(int[] a) {
        sort(a, 0, a.length - 1);
    }

    private void sort(int[] a, int b, int e) {
        int l = b;
        int r = e;
        int piv = a[(l + r) / 2];
        while (l <= r) {
            while (a[l] < piv)
                l++;
            while (a[r] > piv)
                r--;
            if (l <= r) {
                int temp;
                int y1 = l++;
                int y2 = r--;
                temp = a[y1];
                a[y1] = a[y2];
                a[y2] = temp;
            }
        }
        if (b < r) {
            sort(a, b, r);
        }
        if (e > l) {
            sort(a, l, e);
        }
    }
}
