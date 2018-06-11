package com.spbsu.a1arick.homework9.task1.server;

import com.spbsu.a1arick.homework9.task1.Command;
import javafx.util.Pair;

import java.util.Arrays;
import java.util.List;

/**
 * Runnable for handling game process
 */
public class GameRunnable implements Runnable {

    private final ClientSocketWrapper crossWrapper;
    private final ClientSocketWrapper zeroWrapper;
    private final GameController controller;

    /**
     * Constructs new game for two players
     * @param crossWrapper "cross" client
     * @param zeroWrapper "zero" client
     * @param n dimension for game controller
     */
    public GameRunnable(ClientSocketWrapper crossWrapper, ClientSocketWrapper zeroWrapper, int n) {
        this.crossWrapper = crossWrapper;
        this.zeroWrapper = zeroWrapper;
        this.controller = new GameController(n);
    }

    @Override
    public void run() {
        try (ClientSocketWrapper crossWrapper = this.crossWrapper;
             ClientSocketWrapper zeroWrapper = this.zeroWrapper) {
            List<ClientSocketWrapper> wrappers = Arrays.asList(crossWrapper, zeroWrapper);

            while (true) {
                System.out.println("Started new game for clients: " + wrappers);
                ClientSocketWrapper currentWrapper = crossWrapper;
                controller.clear();
                for (ClientSocketWrapper wrapper : wrappers) {
                    wrapper.sendCommand(Command.CLEAR);
                    wrapper.sendCommand(Command.TEXT, "GLHF! New game started. Current turn is: " + wrapper.getName());
                }

                while (true) {
                    boolean makeTurn = makeTurn(currentWrapper, wrappers);
                    if (makeTurn || controller.isFinished()) {
                        String message = makeTurn ? "Win " + currentWrapper.getName() : "Draw";
                        for (ClientSocketWrapper wrapper : wrappers) {
                            wrapper.sendCommand(Command.TEXT, message);
                        }
                        break;
                    } else {
                        currentWrapper = currentWrapper.isCross() ? zeroWrapper : crossWrapper;
                    }
                }

            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected boolean makeTurn(ClientSocketWrapper currentWrapper, List<ClientSocketWrapper> wrappers)
            throws Exception {
        Pair<Command, List<String>> pair = currentWrapper.applyCommand(Command.CLIENT_NEXT_TURN);
        while (true) {
            Command command = pair.getKey();
            List<String> argList = pair.getValue();
            command.checkLength(argList, 2);
            int i = Integer.parseInt(argList.get(0));
            int j = Integer.parseInt(argList.get(1));
            switch (command) {
                case SERVER_NEXT_TURN_TRY:
                    boolean canMakeTurn = controller.canMakeTurn(i, j);
                    pair = currentWrapper.applyCommand(canMakeTurn ? Command.RESULT_TRUE : Command.RESULT_FALSE);
                    break;
                case SERVER_NEXT_TURN:
                    boolean makeTurn = controller.makeTurn(i, j);
                    if (makeTurn) {
                        for (ClientSocketWrapper wrapper : wrappers) {
                            wrapper.sendCommand(Command.SET, i, j, currentWrapper.isCross());
                        }
                        return controller.check(currentWrapper.isCross());
                    } else {
                        currentWrapper.sendCommand(Command.TEXT, "Wrong turn!");
                        pair = currentWrapper.applyCommand(Command.CLIENT_NEXT_TURN);
                    }
                    break;
            }
        }
    }
}
