package com.spbsu.a1arick.homework9.task1;

import com.spbsu.a1arick.homework9.task1.exceptions.WrongCommandFormatException;
import javafx.util.Pair;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Enum for commands that is used for sever-client communication
 */
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
    GAME_ID;

    /**
     * Checks number of arguments
     * @param args arguments
     * @param length expected number of arguments
     * @throws WrongCommandFormatException if number of arguments is wrong
     */
    public void checkLength(List<?> args, int length) throws WrongCommandFormatException {
        if (args.size() < length) {
            throw new WrongCommandFormatException(this);
        }
    }

    /**
     * Checks command and argument length
     * @param pair command and arguments
     * @param length expected number of arguments
     * @throws WrongCommandFormatException if command is wrong or number of arguments is wrong
     */
    public void checkPair(Pair<Command, List<String>> pair, int length) throws WrongCommandFormatException {
        if (pair.getKey() != this || pair.getValue().size() < length) {
            throw new WrongCommandFormatException(this);
        }
    }

    /**
     * Serializes command and arguments
     * @param args arguments
     * @return serialized command and its arguments
     */
    public String makeCommand(Object... args) {
        String arguments = args != null ? Arrays.stream(args).map(Object::toString).collect(Collectors.joining(":")) : null;
        return this.name() + ':' + arguments;
    }

    /**
     * Parses command and arguments
     * @param s string to parse
     * @return command and arguments
     * @throws WrongCommandFormatException if format is wrong
     */
    public static Pair<Command, List<String>> parse(String s) throws WrongCommandFormatException {
        String[] args = s.split(":");
        if (args.length == 0) {
            throw new WrongCommandFormatException();
        }
        Command command = Command.valueOf(args[0]);
        List<String> argList = Arrays.stream(args).skip(1).collect(Collectors.toList());
        return new Pair<>(command, argList);
    }
}
