package com.spbsu.a1arick.homework9.task1;

import com.spbsu.a1arick.homework9.task1.exceptions.WrongCommandFormatException;

import java.util.List;

public enum Command {
    OK,
    ERROR,
    CLOSE,
    CLEAR,
    SET,
    CLIENT_NEXT_TURN,
    SERVER_NEXT_TURN,
    SERVER_NEXT_TURN_TRY,
    RESULT_TRUE,
    RESULT_FALSE,
    TEXT,
    ;

    public void checkLength(List<?> args, int length) throws WrongCommandFormatException {
        if (args.size() < length) {
            throw new WrongCommandFormatException(this);
        }
    }
}
