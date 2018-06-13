package com.spbsu.a1arick.homework9.task1.exceptions;

/**
 * Client side exception
 */
public class ClientErrorException extends Exception {
    /**
     * Constructs new exception with client name and message
     *
     * @param clientName client name
     * @param message    message
     */
    public ClientErrorException(String clientName, String message) {
        super(clientName + " error message: " + message);
    }
}
