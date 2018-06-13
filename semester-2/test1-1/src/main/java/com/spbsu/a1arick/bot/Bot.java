package com.spbsu.a1arick.bot;

import javafx.util.Pair;

import java.util.function.BiPredicate;

public interface Bot {
    void clearState();

    void set(int i, int j);

    Pair<Integer, Integer> nextTurn(BiPredicate<Integer, Integer> canMakeTurn);
}
