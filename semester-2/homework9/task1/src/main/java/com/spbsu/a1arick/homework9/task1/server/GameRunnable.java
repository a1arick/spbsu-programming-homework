package com.spbsu.a1arick.homework9.task1.server;

import com.spbsu.a1arick.homework9.task1.Command;
import com.spbsu.a1arick.homework9.task1.exceptions.ClientErrorException;
import com.spbsu.a1arick.homework9.task1.exceptions.UnknownCommandException;
import com.spbsu.a1arick.homework9.task1.exceptions.WrongCommandFormatException;
import javafx.util.Pair;

import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

public class GameRunnable implements Runnable {

    private final Socket crossSocket;
    private final Socket zeroSocket;
    private final TicTacToeController controller;

    public GameRunnable(Socket crossSocket, Socket zeroSocket, int n) {
        this.crossSocket = crossSocket;
        this.zeroSocket = zeroSocket;
        this.controller = new TicTacToeController(n);
    }

    @Override
    public void run() {
        try (ClientSocketWrapper crossWrapper = new ClientSocketWrapper(crossSocket, true);
             ClientSocketWrapper zeroWrapper = new ClientSocketWrapper(zeroSocket, false)) {

            List<ClientSocketWrapper> wrappers = Arrays.asList(crossWrapper, zeroWrapper);

            while(true) {
                ClientSocketWrapper currentWrapper = crossWrapper;
                for (ClientSocketWrapper wrapper : wrappers) {
                    wrapper.sendCommand(Command.CLEAR);
                    wrapper.sendCommand(Command.TEXT, "GLHF! New game started");
                    wrapper.sendCommand(Command.TEXT, "Current turn is: " + currentWrapper.getName());
                }

                while (true) {
                    boolean makeTurn = makeTurn(currentWrapper, wrappers);
                    if(makeTurn || controller.isFinished()) {
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

    private boolean makeTurn(ClientSocketWrapper currentWrapper, List<ClientSocketWrapper> wrappers)
            throws WrongCommandFormatException, IOException, ClientErrorException, UnknownCommandException {
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
                    if(makeTurn) {
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