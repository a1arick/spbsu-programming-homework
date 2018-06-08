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
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ClientSocketWrapper implements AutoCloseable {

    private final Socket socket;
    private final boolean isCross;
    private final String name;
    private final String clientName;
    private final BufferedReader reader;
    private final PrintWriter writer;

    public ClientSocketWrapper(Socket socket, boolean isCross) throws IOException {
        this.socket = socket;
        this.isCross = isCross;
        this.name = isCross ? "cross" : "zero";
        this.clientName = name + "[" + socket + "]";
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new PrintWriter(socket.getOutputStream());
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
        writer.println(command.name() + ':' + Arrays.stream(args).map(Object::toString).collect(Collectors.joining(":")));
        writer.flush();
        String line = reader.readLine();
        String[] receivedArgs = line.split(":");
        if (args.length == 0) {
            throw new WrongCommandFormatException(clientName);
        }
        System.out.printf("Received new command: %s from socket: %s\n", line, socket);
        Command receivedCommand = Command.valueOf(receivedArgs[0]);
        List<String> argList = Arrays.stream(receivedArgs).skip(1).collect(Collectors.toList());
        if (receivedCommand == Command.ERROR) {
            throw new ClientErrorException(clientName, argList.get(0));
        }
        return new Pair<>(receivedCommand, argList);
    }

    @Override
    public void close() throws Exception {
        sendCommand(Command.CLOSE);
    }
}
