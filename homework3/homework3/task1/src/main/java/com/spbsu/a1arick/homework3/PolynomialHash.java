package com.spbsu.a1arick.homework3;

public class PolynomialHash implements HashFunction{
    @Override
    public int hash(String element, int value) {
        int multiplier = 239 % value;
        int result = 0;
        for (int i = 0; i < element.length(); i++)
            result = (result + multiplier * element.charAt(i)) % value;
        return result;
    }
}
