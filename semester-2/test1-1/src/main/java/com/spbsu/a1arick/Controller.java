package com.spbsu.a1arick;

import java.util.function.Function;

/**
 * Class for controlling Tic-tac-toe game
 */
public class Controller {
    private final int n;
    private int[][] turns;
    private boolean[][] used;
    private boolean turn = true;
    private int size = 0;


    public Controller(int n) {
        this.n = n;
        this.turns = new int[n][n];
        this.used = new boolean[n][n];
    }

    public boolean makeTurn(int i, int j) {
        if (!canMakeTurn(i, j)) {
            throw new IllegalArgumentException("Can't make turn");
        }
        turns[i][j] = turn ? 1 : 2;
        turn = !turn;
        size++;
        used[i][j] = true;
        return turn;
    }

    public boolean check(int label) {
        if (check(i -> i, i -> i, label)) return true;

        if (check(i -> i, i -> n - i - 1, label)) return true;

        for (int k = 0; k < n; k++) {
            int j = k;
            if (check(i -> j, i -> i, label)) return true;
            if (check(i -> i, i -> j, label)) return true;
        }
        return false;
    }

    public boolean canMakeTurn(int i, int j) {
        boolean outOfBorders = i < 0 || j < 0 || i >= n || j >= n;
        boolean alreadyUsed = used[i][j];
        return !(outOfBorders || alreadyUsed || isFinished());
    }

    public boolean isFinished() {
        return size == n * n;
    }

    public void clear() {
        used = new boolean[n][n];
        turns = new int[n][n];
        turn = true;
        size = 0;
    }

    private boolean check(Function<Integer, Integer> getIOperator, Function<Integer, Integer> getJOperator, int label) {
        for (int i = 0; i < n; i++) {
            if (turns[getIOperator.apply(i)][getJOperator.apply(i)] != label) {
                return false;
            }
        }
        return true;
    }
}
