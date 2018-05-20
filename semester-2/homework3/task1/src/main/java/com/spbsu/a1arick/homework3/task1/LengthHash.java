package com.spbsu.a1arick.homework3.task1;

public class LengthHash implements HashFunction {
    @Override
    public int hash(String text, int mod) {
        return text.length() % mod;
    }
}
