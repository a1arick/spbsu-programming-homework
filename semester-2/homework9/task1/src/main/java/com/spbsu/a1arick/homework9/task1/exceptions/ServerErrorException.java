package com.spbsu.a1arick.homework9.task1.exceptions;

/**
 * Server side exception
 */
public class ServerErrorException extends RuntimeException {
    public ServerErrorException(String message, Throwable cause) {
        super(message, cause);
    }
}
