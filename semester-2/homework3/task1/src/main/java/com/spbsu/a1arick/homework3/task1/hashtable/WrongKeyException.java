package com.spbsu.a1arick.homework3.task1.hashtable;

/**
 * Exception for wrong key in hash table
 */
public class WrongKeyException extends RuntimeException {
    public WrongKeyException(String message) {
        super(message);
    }
}
