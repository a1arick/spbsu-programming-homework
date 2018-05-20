package com.spbsu.a1arick.homework3.task1.hash.impl;

import com.spbsu.a1arick.homework3.task1.hash.HashFunction;

public class LengthHash implements HashFunction<String> {
    @Override
    public int hash(String text) {
        return text.length();
    }
}
