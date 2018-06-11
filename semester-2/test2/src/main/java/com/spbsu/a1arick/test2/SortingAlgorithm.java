package com.spbsu.a1arick.test2;

import java.util.Comparator;
import java.util.List;

/**
 * Interface for sorting algorithm
 */
public interface SortingAlgorithm {

    /**
     * Sorts the list
     * @param list list to sort
     * @param comparator comparator to use for sorting
     * @param <T> type of objects to sort
     * @return sorted list
     */
    <T> List<T> sort(List<T> list, Comparator<T> comparator);
}
