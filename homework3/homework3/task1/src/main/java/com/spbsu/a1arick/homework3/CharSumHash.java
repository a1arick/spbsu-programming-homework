package com.spbsu.a1arick.homework3;

public class CharSumHash implements HashFunction {
    @Override
    public int hash(String element, int value) {
        int result = 0;
        for (int i = 0; i < element.length(); i++)
            result = result + element.charAt(i);
        return result;
    }
}
