package com.spbsu.a1arick.test2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BubbleSort implements SortingAlgorithm {
    public <T> List<T> sort(List<T> list, Comparator<T> comparator) {
        if (comparator == null) {
            throw new IllegalArgumentException("Null comparator");
        }
        if (list == null || list.isEmpty()) return list;
        ArrayList<T> result = new ArrayList<>(list);

        int n = result.size();
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                if (comparator.compare(result.get(j - 1), result.get(j)) > 0) {
                    Collections.swap(result, j - 1, j);
                }
            }
        }
        return result;
    }
}
