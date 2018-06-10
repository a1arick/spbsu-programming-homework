package com.spbsu.a1arick.homework9.task1.server;

import java.util.function.Function;

/**
 * Class for controlling Tic-tac-toe game
 */
public class GameController {
    private final int n;
    private int[][] turns;
    private boolean[][] used;
    private boolean turn = true;
    private int size = 0;


    public GameController(int n) {
        this.n = n;
        this.turns = new int[n][n];
        this.used = new boolean[n][n];
    }

    public boolean makeTurn(int i, int j) {
        if (!canMakeTurn(i, j)) return false;
        turns[i][j] = turn ? 1 : 2;
        turn = !turn;
        size++;
        used[i][j] = true;
        return true;
    }

    public boolean check(boolean isCross) {
        return check(isCross ? 2 : 1);
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
        if (isFinished()) return false;
        boolean outOfBorders = i < 0 || j < 0 || i >= n || j >= n;
        return !outOfBorders && !used[i][j];
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