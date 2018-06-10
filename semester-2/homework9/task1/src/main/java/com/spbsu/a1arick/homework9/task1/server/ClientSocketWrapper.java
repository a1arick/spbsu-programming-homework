package com.spbsu.a1arick.homework9.task1.server;

import com.spbsu.a1arick.homework9.task1.Command;
import com.spbsu.a1arick.homework9.task1.exceptions.ClientErrorException;
import com.spbsu.a1arick.homework9.task1.exceptions.UnknownCommandException;
import com.spbsu.a1arick.homework9.task1.exceptions.WrongCommandFormatException;
import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class ClientSocketWrapper implements AutoCloseable {

    private Socket socket;
    private boolean isCross;
    private String name;
    private String clientName;
    private BufferedReader reader;
    private PrintWriter writer;

    public ClientSocketWrapper(Socket socket) throws IOException {
        this.socket = socket;
        this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.writer = new PrintWriter(socket.getOutputStream());
    }

    public void init(boolean isCross) {
        this.isCross = isCross;
        this.name = isCross ? "cross" : "zero";
        this.clientName = name + "[" + socket + "]";
    }

    public boolean isCross() {
        return isCross;
    }

    public String getName() {
        return name;
    }

    public void sendCommand(Command command, Object... args) throws IOException, ClientErrorException, UnknownCommandException, WrongCommandFormatException {
        Pair<Command, List<String>> pair = applyCommand(command, args);
        Command receivedCommand = pair.getKey();
        if (receivedCommand != Command.OK) {
            throw new UnknownCommandException(clientName, receivedCommand);
        }
    }

    public Pair<Command, List<String>> applyCommand(Command command, Object... args) throws IOException, ClientErrorException, WrongCommandFormatException {
        writer.println(command.makeCommand(args));
        writer.flush();
        Pair<Command, List<String>> pair = Command.parse(reader.readLine());
        System.out.printf("Received new command: %s from socket: %s\n", pair, socket);
        if (pair.getKey() == Command.ERROR) {
            throw new ClientErrorException(clientName, pair.getValue().get(0));
        }
        return pair;
    }

    @Override
    public void close() throws Exception {
        try (BufferedReader reader = this.reader;
             PrintWriter writer = this.writer;
             Socket socket = this.socket) {
            sendCommand(Command.CLOSE);
        } finally {
            reader = null;
            writer = null;
            socket = null;
        }
    }

    @Override
    public String toString() {
        return clientName;
    }
}
