package com.spbsu.a1arick.homework9.task1.exceptions;

import com.spbsu.a1arick.homework9.task1.Command;

public class WrongCommandFormatException extends Exception {
    public WrongCommandFormatException() {
        super("Wrong command format");
    }

    public WrongCommandFormatException(String clientName) {
        super("Wrong command format from client: " + clientName);
    }

    public WrongCommandFormatException(Command command) {
        super("Wrong command: " + command + " format");
    }
}
