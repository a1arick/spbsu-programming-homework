package com.spbsu.a1arick.homework9.task1.exceptions;

public class ClientErrorException extends Exception {
    public ClientErrorException(String clientName, String message) {
        super(clientName + " error message: " + message);
    }
}
