package com.spbsu.a1arick.homework3.task1;

public class PolynomialHash implements HashFunction {
    @Override
    public int hash(String text, int mod) {
        int hashMultiplier = 101 % mod;
        int result = 0;
        for (char current: text.toCharArray()) {
            result = (result + hashMultiplier * current) % mod;
        }
        return result;
    }
}
