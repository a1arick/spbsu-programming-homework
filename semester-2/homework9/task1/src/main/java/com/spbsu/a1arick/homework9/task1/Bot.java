package com.spbsu.a1arick.homework9.task1;

import javafx.util.Pair;

import java.util.function.BiPredicate;

public interface Bot {
    void clearState();

    void set(int i, int j);

    Pair<Integer, Integer> nextTurn(BiPredicate<Integer, Integer> canMakeTurn);
}
