package com.spbsu.a1arick.homework3.task1.hash.impl;

import com.spbsu.a1arick.homework3.task1.hash.HashFunction;

/**
 * {@link String} polynomial hash function
 */
public class PolynomialHash implements HashFunction<String> {
    @Override
    public int hash(String text) {
        int hashMultiplier = 239;
        int result = 0;
        for (char current : text.toCharArray()) {
            result = (result + hashMultiplier * current);
        }
        return result;
    }
}
