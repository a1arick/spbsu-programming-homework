package com.spbsu.a1arick.homework9.task1.client;

import com.spbsu.a1arick.homework9.task1.Command;
import com.spbsu.a1arick.homework9.task1.exceptions.UnknownCommandException;
import com.spbsu.a1arick.homework9.task1.exceptions.WrongCommandFormatException;
import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;

/**
 * Runnable for handling commands for client
 */
public class ClientRunnable implements Runnable {

    private final GameClient client;
    private final Socket socket;
    private final BufferedReader is;
    private final PrintWriter os;

    public ClientRunnable(InetAddress address, int port, GameClient client) throws IOException {
        this.client = client;
        this.socket = new Socket(address, port);
        this.is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.os = new PrintWriter(socket.getOutputStream());
    }

    @Override
    public void run() {
        try (Socket socket = this.socket;
             BufferedReader is = this.is;
             PrintWriter os = this.os) {
            try {
                while (handleNextCommand(is, os)) ;
            } catch (Exception e) {
                send(os, Command.ERROR, e.getMessage() != null ? e.getMessage() : e.getClass());
                throw e;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private boolean handleNextCommand(BufferedReader is, PrintWriter os) throws IOException, WrongCommandFormatException, UnknownCommandException {
        if (client.isClosed()) {
            send(os, Command.ERROR, "closed by user");
        }
        Pair<Command, List<String>> pair = Command.parse(is.readLine());
        System.out.println("Received new command: " + pair);
        Command command = pair.getKey();
        List<String> argList = pair.getValue();
        switch (command) {
            case CLOSE:
                send(os, Command.OK);
                return false;
            case TEXT:
                command.checkLength(argList, 1);
                client.showMessage(argList.get(0));
                send(os, Command.OK);
                break;
            case CLEAR:
                client.clear();
                send(os, Command.OK);
                break;
            case GAME_ID:
                send(os, Command.GAME_ID, client.gameId());
                break;
            case SET:
                command.checkLength(argList, 3);
                client.set(Integer.parseInt(argList.get(0)), Integer.parseInt(argList.get(1)), Boolean.parseBoolean(argList.get(2)));
                send(os, Command.OK);
                break;
            case CLIENT_NEXT_TURN:
                Pair<Integer, Integer> nextTurn = client.nextTurn((i, j) -> {
                    send(os, Command.SERVER_NEXT_TURN_TRY, i, j);
                    try {
                        Pair<Command, List<String>> result = Command.parse(is.readLine());
                        return Command.RESULT_TRUE == result.getKey();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
                send(os, Command.SERVER_NEXT_TURN, nextTurn.getKey(), nextTurn.getValue());
                break;
            default:
                throw new UnknownCommandException(command);
        }
        return true;
    }

    private static void send(PrintWriter os, Command command, Object... args) {
        os.println(command.makeCommand(args));
        os.flush();
    }
}
