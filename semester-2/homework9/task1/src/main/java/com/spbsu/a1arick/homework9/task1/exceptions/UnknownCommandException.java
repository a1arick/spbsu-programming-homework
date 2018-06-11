package com.spbsu.a1arick.homework9.task1.exceptions;

import com.spbsu.a1arick.homework9.task1.Command;

/**
 * Unknown command exception
 */
public class UnknownCommandException extends Exception {

    /**
     * Constructs new exception with client name and unknown command
     *
     * @param clientName client name
     * @param command    unknown command
     */
    public UnknownCommandException(String clientName, Command command) {
        super(clientName + " : Unknown command:" + command);
    }

    /**
     * Constructs new exception with unknown command
     *
     * @param command unknown command
     */
    public UnknownCommandException(Command command) {
        super("Unknown command:" + command);
    }
}
