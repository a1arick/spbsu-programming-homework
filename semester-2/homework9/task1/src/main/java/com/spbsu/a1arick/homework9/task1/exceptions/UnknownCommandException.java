package com.spbsu.a1arick.homework9.task1.exceptions;

import com.spbsu.a1arick.homework9.task1.Command;

public class UnknownCommandException extends Exception {
    public UnknownCommandException(String clientName, Command command) {
        super(clientName + " : Unknown command:" + command);
    }

    public UnknownCommandException(Command command) {
        super("Unknown command:" + command);
    }
}
