package com.spbsu.a1arick.bot;

import javafx.util.Pair;

import java.util.function.BiPredicate;

public class StupidBot implements Bot {

    private final int n;

    public StupidBot(int n) {
        this.n = n;
    }

    @Override
    public void clearState() {
    }

    @Override
    public void set(int i, int j) {
    }

    @Override
    public Pair<Integer, Integer> nextTurn(BiPredicate<Integer, Integer> canMakeTurn) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (canMakeTurn.test(i, j)) return new Pair<>(i, j);
            }
        }
        throw new IllegalStateException("Can't make turn");
    }
}
