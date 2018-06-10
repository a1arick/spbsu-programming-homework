package com.spbsu.a1arick.homework9.task1.client;

import javafx.util.Pair;

import java.util.function.BiPredicate;

public interface GameClient {
    void showMessage(String message);

    boolean isClosed();

    void set(int i, int j, boolean isCross);

    void clear();

    String gameId();

    Pair<Integer, Integer> nextTurn(BiPredicate<Integer, Integer> canMakeTurn);
}
