package com.spbsu.a1arick.homework3;

public class LengthHash implements HashFunction {
    @Override
    public int hash(String element, int value) {
        return element.length() % value;
    }
}
