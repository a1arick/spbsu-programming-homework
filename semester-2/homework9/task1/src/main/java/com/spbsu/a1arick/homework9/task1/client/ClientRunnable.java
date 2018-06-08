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
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ClientRunnable implements Runnable {

    private final InetAddress address;
    private final int port;
    private final Client client;

    public ClientRunnable(InetAddress address, int port, Client client) {
        this.address = address;
        this.port = port;
        this.client = client;
    }

    @Override
    public void run() {
        try (Socket s1 = new Socket(address, port);
             BufferedReader is = new BufferedReader(new InputStreamReader(s1.getInputStream()));
             PrintWriter os = new PrintWriter(s1.getOutputStream())) {
            try {
                while (handleNextCommand(is, os));
                System.out.println("Socket closed successfully");
            } catch (Exception e) {
                send(os, Command.ERROR, e.getMessage());
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
        String line = is.readLine();
        String[] args = line.split(":");
        if (args.length == 0) {
            throw new WrongCommandFormatException();
        }
        System.out.println("Received new command: " + line);
        Command command = Command.valueOf(args[0]);
        List<String> argList = Arrays.stream(args).skip(1).collect(Collectors.toList());
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
            case SET:
                command.checkLength(argList, 3);
                client.set(Integer.parseInt(argList.get(0)), Integer.parseInt(argList.get(1)), Boolean.parseBoolean(argList.get(2)));
                send(os, Command.OK);
                break;
            case CLIENT_NEXT_TURN:
                Pair<Integer, Integer> nextTurn = client.nextTurn((i, j) -> {
                    send(os, Command.SERVER_NEXT_TURN_TRY, i, j);
                    try {
                        return Command.RESULT_TRUE == Command.valueOf(is.readLine());
                    } catch (IOException e) {
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
        os.println(command.name() + ':' + Arrays.stream(args).map(Object::toString).collect(Collectors.joining(":")));
        os.flush();
    }
}
