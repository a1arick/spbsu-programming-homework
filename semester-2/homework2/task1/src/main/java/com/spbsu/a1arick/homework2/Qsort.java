package com.spbsu.a1arick.homework2;

public class Qsort implements SortAlgorithm {
    @Override
    public void Sort(int[] a) {
        qsort(a,0,a.length - 1);
    }

    private void qsort(int[] a,int b, int e) {
            int l = b;
            int r = e;
            int piv = a[(l + r) / 2];
            while (l <= r)
            {
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
            if (b < r)

                qsort (a, b, r);
            if (e > l)
                qsort (a, l, e);
        }
}
