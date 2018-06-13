package com.spbsu.a1arick.homework9.task1.server;

import com.spbsu.a1arick.homework9.task1.Command;
import com.spbsu.a1arick.homework9.task1.exceptions.ServerErrorException;
import javafx.util.Pair;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Runnable for server that matches games by ids
 */
public class ServerRunnable implements Runnable {

    private final int port;
    private final Map<String, ClientSocketWrapper> clientMap = new HashMap<>();

    public ServerRunnable(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started on port: " + port);
            while (true) {
                try {
                    Socket socket = serverSocket.accept();
                    ClientSocketWrapper clientSocketWrapper = new ClientSocketWrapper(socket);
                    System.out.println("Accepted socket connection: " + socket);
                    Pair<Command, List<String>> pair = clientSocketWrapper.applyCommand(Command.GAME_ID);
                    Command.GAME_ID.checkPair(pair, 1);
                    String gameId = pair.getValue().get(0);
                    ClientSocketWrapper firstWrapper = clientMap.remove(gameId);
                    if (firstWrapper != null) {
                        firstWrapper.init(true);
                        clientSocketWrapper.init(false);
                        createGame(executorService, firstWrapper, clientSocketWrapper);
                    } else {
                        clientMap.put(gameId, clientSocketWrapper);
                    }
                } catch (Exception e) {
                    System.out.println("Exception: " + e);
                }
            }
        } catch (IOException e) {
            throw new ServerErrorException("Server socket error", e);
        } finally {
            executorService.shutdown();
        }
    }

    protected void createGame(ExecutorService executorService, ClientSocketWrapper crossWrapper, ClientSocketWrapper zeroWrapper) {
        executorService.execute(new GameRunnable(crossWrapper, zeroWrapper, 3));
    }
}
