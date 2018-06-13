package com.spbsu.a1arick.homework3.task1.hash;

/**
 * Interface for hash function
 *
 * @param <T> object type for hash function calculation
 */
public interface HashFunction<T> {
    /**
     * Calculates hash function value
     *
     * @param value for hash function calculation
     * @return hash function value
     */
    int hash(T value);
}
