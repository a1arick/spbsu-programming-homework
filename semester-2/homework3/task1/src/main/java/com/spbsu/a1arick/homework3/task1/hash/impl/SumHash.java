package com.spbsu.a1arick.homework3.task1.hash.impl;

import com.spbsu.a1arick.homework3.task1.hash.HashFunction;

/**
 * {@link String} symbol sum hash function
 */
public class SumHash implements HashFunction<String> {
    @Override
    public int hash(String text) {
        int result = 0;
        for (char current : text.toCharArray()) {
            result += current;
        }
        return result;
    }
}
