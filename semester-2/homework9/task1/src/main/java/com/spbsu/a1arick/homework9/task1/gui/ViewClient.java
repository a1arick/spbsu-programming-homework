package com.spbsu.a1arick.homework9.task1.gui;

import com.spbsu.a1arick.homework9.task1.client.GameClient;
import javafx.application.Platform;
import javafx.util.Pair;

import java.util.concurrent.BlockingQueue;
import java.util.function.BiPredicate;

public class ViewClient implements GameClient{
    private final View view;

    public ViewClient(View view) {
        this.view = view;
    }

    @Override
    public void showMessage(String message) {
        Platform.runLater(() -> view.showMessage(message));
    }

    @Override
    public boolean isClosed() {
        return false;
    }

    @Override
    public void set(int i, int j, boolean isCross) {
        Platform.runLater(() -> view.set(i, j, isCross));
    }

    @Override
    public void clear() {
        Platform.runLater(view::clear);

    }

    @Override
    public String gameId() {
        return "game";
    }

    @Override
    public Pair<Integer, Integer> nextTurn(BiPredicate<Integer, Integer> canMakeTurn) {
        BlockingQueue<Pair<Integer, Integer>> turn = view.nextTurn(canMakeTurn);
        return turn.poll();
    }
}
