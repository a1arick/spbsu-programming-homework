package com.spbsu.a1arick.homework9.task1.server;

import com.spbsu.a1arick.homework9.task1.Command;
import com.spbsu.a1arick.homework9.task1.exceptions.ClientErrorException;
import com.spbsu.a1arick.homework9.task1.exceptions.UnknownCommandException;
import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

/**
 * Wrapper for client socket
 */
public class ClientSocketWrapper implements AutoCloseable {

    private Socket socket;
    private boolean isCross;
    private String name;
    private String clientName;
    private BufferedReader reader;
    private PrintWriter writer;

    /**
     * Constructs wrapper for client socket
     *
     * @param socket to wrap
     * @throws IOException if can't get socket input/output streams
     */
    public ClientSocketWrapper(Socket socket) throws IOException {
        this.socket = socket;
        this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.writer = new PrintWriter(socket.getOutputStream());
    }

    /**
     * Initializes client with "cross" or "zero" label
     *
     * @param isCross {@code true} if is "cross" else {@code false}
     */
    public void init(boolean isCross) {
        this.isCross = isCross;
        this.name = isCross ? "cross" : "zero";
        this.clientName = name + "[" + socket + "]";
    }

    /**
     * @return {@code true} if is "cross" else {@code false}
     */
    public boolean isCross() {
        return isCross;
    }

    /**
     * @return client name
     */
    public String getName() {
        return name;
    }

    /**
     * Sends command to client and expects "OK"
     *
     * @param command command to send
     * @param args    command arguments
     * @throws Exception in case of command format, client or connection error
     */
    public void sendCommand(Command command, Object... args) throws Exception {
        Pair<Command, List<String>> pair = applyCommand(command, args);
        Command receivedCommand = pair.getKey();
        if (receivedCommand != Command.OK) {
            throw new UnknownCommandException(clientName, receivedCommand);
        }
    }

    /**
     * Sends command to client and returns result command
     *
     * @param command command to send
     * @param args    command arguments
     * @return received command with arguments
     * @throws Exception in case of command format, client or connection error
     */
    public Pair<Command, List<String>> applyCommand(Command command, Object... args) throws Exception {
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
