package com.spbsu.a1arick.homework9.task1.server;

import com.spbsu.a1arick.homework9.task1.client.ClientRunnable;
import com.spbsu.a1arick.homework9.task1.client.GameClient;
import javafx.util.Pair;
import org.junit.Test;

import java.io.IOException;
import java.net.InetAddress;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BiPredicate;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ServerRunnableTest {

    @Test
    public void matching() throws InterruptedException, IOException {
        int port = 11111;
        TestServerRunnable serverRunnable = new TestServerRunnable(port);
        Thread server = new Thread(serverRunnable);
        server.start();

        new Thread(new ClientRunnable(InetAddress.getLocalHost(), port, new TestGameClient("a"))).start();
        new Thread(new ClientRunnable(InetAddress.getLocalHost(), port, new TestGameClient("b"))).start();
        new Thread(new ClientRunnable(InetAddress.getLocalHost(), port, new TestGameClient("c"))).start();
        new Thread(new ClientRunnable(InetAddress.getLocalHost(), port, new TestGameClient("a"))).start();

        serverRunnable.latch.await();
        assertTrue(serverRunnable.called.get());
    }

    private static class TestServerRunnable extends ServerRunnable {

        private final AtomicBoolean called = new AtomicBoolean();
        private final CountDownLatch latch = new CountDownLatch(1);

        public TestServerRunnable(int port) {
            super(port);
        }

        @Override
        protected void createGame(ExecutorService executorService, ClientSocketWrapper crossWrapper, ClientSocketWrapper zeroWrapper) {
            called.set(true);
            assertTrue(crossWrapper.isCross());
            assertFalse(zeroWrapper.isCross());
            latch.countDown();
        }
    }

    private static class TestGameClient implements GameClient {

        private final String gameId;

        private TestGameClient(String gameId) {
            this.gameId = gameId;
        }

        @Override
        public void showMessage(String message) {

        }

        @Override
        public void set(int i, int j, boolean isCross) {

        }

        @Override
        public void clear() {

        }

        @Override
        public String gameId() {
            return gameId;
        }

        @Override
        public Pair<Integer, Integer> nextTurn(BiPredicate<Integer, Integer> canMakeTurn) {
            return null;
        }

        @Override
        public boolean isClosed() {
            return false;
        }
    }
}