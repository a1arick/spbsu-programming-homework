package com.spbsu.a1arick.homework9.task1.gui;

import com.spbsu.a1arick.homework9.task1.client.GameClient;
import javafx.application.Platform;
import javafx.util.Pair;

import java.util.concurrent.SynchronousQueue;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Game client implementation for JavaFX view
 */
public class ViewClient implements GameClient {
    private final View view;
    private volatile boolean closed = false;

    /**
     * Constructs new view client
     * @param view JavaFX view
     */
    public ViewClient(View view) {
        this.view = view;
    }

    @Override
    public void showMessage(String message) {
        waitWithResult(() -> {
            view.showMessage(message);
            return 1;
        });
    }

    @Override
    public void set(int i, int j, boolean isCross) {
        waitWithResult(() -> {
            view.set(i, j, isCross);
            return 1;
        });
    }

    @Override
    public void clear() {
        waitWithResult(() -> {
            view.clear();
            return 1;
        });
    }

    @Override
    public String gameId() {
        return waitWithResult(view::getGameId);
    }

    @Override
    public Pair<Integer, Integer> nextTurn(BiPredicate<Integer, Integer> canMakeTurn) {
        return waitWithResult(consumer -> view.nextTurn(canMakeTurn, consumer));
    }

    public void close() {
        this.closed = true;
    }

    @Override
    public boolean isClosed() {
        return closed;
    }

    private static <T> T waitWithResult(Supplier<T> supplier) {
        return waitWithResult(consumer -> consumer.accept(supplier.get()));
    }

    // wrapper for waiting a result
    private static <T> T waitWithResult(Consumer<Consumer<T>> consumer) {
        SynchronousQueue<T> queue = new SynchronousQueue<>();
        Platform.runLater(() -> consumer.accept(queue::add));
        try {
            return queue.take();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
