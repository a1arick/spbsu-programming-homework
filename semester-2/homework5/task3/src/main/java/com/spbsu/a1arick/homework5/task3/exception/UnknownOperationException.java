package com.spbsu.a1arick.homework5.task3.exception;

/**
 * Unknown operation exception
 */
public class UnknownOperationException extends Exception {

    public UnknownOperationException(String operation) {
        super("Unknown operation: " + operation);
    }
}
