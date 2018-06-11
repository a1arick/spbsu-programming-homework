package com.spbsu.a1arick.homework9.task1.server;

import com.spbsu.a1arick.homework9.task1.client.ClientRunnable;
import com.spbsu.a1arick.homework9.task1.client.GameClient;
import javafx.util.Pair;
import org.junit.Test;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.function.BiPredicate;

import static org.junit.Assert.assertTrue;

/**
 * Game runnable test
 */
public class GameRunnableTest {

    @Test
    public void game() throws IOException, InterruptedException {

        int port = 11112;
        TestServerRunnable serverRunnable = new TestServerRunnable(port);
        Thread server = new Thread(serverRunnable);
        server.start();

        TestGameClient cross = new TestGameClient(true);
        TestGameClient zero = new TestGameClient(false);
        new Thread(new ClientRunnable(InetAddress.getLocalHost(), port, cross)).start();
        new Thread(new ClientRunnable(InetAddress.getLocalHost(), port, zero)).start();

        serverRunnable.latch.await();
        serverRunnable.gameRunnable.latch.await();

        for (TestGameClient client : Arrays.asList(cross, zero)) {
            assertTrue(client.showMessage);
            assertTrue(client.clear);
            assertTrue(client.setIJ);
            assertTrue(client.nextTurn);
        }
    }

    private static class TestServerRunnable extends ServerRunnable {

        private volatile CountDownLatch latch = new CountDownLatch(1);
        private volatile TestGameRunnable gameRunnable;

        public TestServerRunnable(int port) {
            super(port);
        }

        @Override
        protected void createGame(ExecutorService executorService, ClientSocketWrapper crossWrapper, ClientSocketWrapper zeroWrapper) {
            gameRunnable = new TestGameRunnable(crossWrapper, zeroWrapper, 3);
            executorService.execute(gameRunnable);
            latch.countDown();
        }
    }

    private static class TestGameRunnable extends GameRunnable {

        private final CountDownLatch latch = new CountDownLatch(2);

        public TestGameRunnable(ClientSocketWrapper crossWrapper, ClientSocketWrapper zeroWrapper, int n) {
            super(crossWrapper, zeroWrapper, n);
        }

        @Override
        protected boolean makeTurn(ClientSocketWrapper currentWrapper, List<ClientSocketWrapper> wrappers) throws Exception {
            boolean makeTurn = super.makeTurn(currentWrapper, wrappers);
            latch.countDown();
            return makeTurn;
        }
    }

    private static class TestGameClient implements GameClient {

        private final boolean isCross;
        private volatile boolean showMessage = false;
        private volatile boolean setIJ = false;
        private volatile boolean clear = false;
        private volatile boolean nextTurn = false;

        private TestGameClient(boolean isCross) {
            this.isCross = isCross;
        }

        @Override
        public void showMessage(String message) {
            showMessage = true;
        }

        @Override
        public void set(int i, int j, boolean isCross) {
            setIJ = true;
        }

        @Override
        public void clear() {
            clear = true;
        }

        @Override
        public String gameId() {
            return "game";
        }

        @Override
        public Pair<Integer, Integer> nextTurn(BiPredicate<Integer, Integer> canMakeTurn) {
            nextTurn = true;
            return new Pair<>(isCross ? 0 : 1, 0);
        }

        @Override
        public boolean isClosed() {
            return false;
        }
    }
}