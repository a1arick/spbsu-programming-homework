package com.spbsu.a1arick.homework9.task1.client;

import javafx.util.Pair;

import java.util.function.BiPredicate;

/**
 * Game client interface
 */
public interface GameClient {
    /**
     * Shows message on game client
     *
     * @param message to show
     */
    void showMessage(String message);

    /**
     * Sets label to {@code i, j} on client
     *
     * @param i       row
     * @param j       column
     * @param isCross cross or zero
     */
    void set(int i, int j, boolean isCross);

    /**
     * Clears state of game on client side
     */
    void clear();

    /**
     * @return game id to play with other player
     */
    String gameId();

    /**
     * Get next turn from client
     *
     * @param canMakeTurn predicate to use to check turn
     * @return next turn
     */
    Pair<Integer, Integer> nextTurn(BiPredicate<Integer, Integer> canMakeTurn);

    /**
     * Checks if client is closed
     *
     * @return {@code true} if is closed else {@code false}
     */
    boolean isClosed();
}
