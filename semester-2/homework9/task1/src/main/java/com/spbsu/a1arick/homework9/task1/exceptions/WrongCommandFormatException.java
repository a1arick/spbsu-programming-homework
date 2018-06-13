package com.spbsu.a1arick.homework9.task1.exceptions;

import com.spbsu.a1arick.homework9.task1.Command;

/**
 * Wrong command exception
 */
public class WrongCommandFormatException extends Exception {
    /**
     * Constructs new exception  with general message
     */
    public WrongCommandFormatException() {
        super("Wrong command format");
    }

    /**
     * Constructs new exception with wrong command
     *
     * @param command wrong command
     */
    public WrongCommandFormatException(Command command) {
        super("Wrong command: " + command + " format");
    }
}
