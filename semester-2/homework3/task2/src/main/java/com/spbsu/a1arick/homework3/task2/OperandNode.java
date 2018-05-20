package com.spbsu.a1arick.homework3.task2;

import java.io.PrintStream;
import java.util.Scanner;

public class OperandNode implements ExpressionTreeNode {
    private int value;

    OperandNode(Scanner scanner) throws WrongInputException {
        try {
            String input = scanner.next();
            if (input.charAt(input.length() - 1) == ')') {
                value = Integer.parseInt(input.substring(0, input.indexOf(')')));
            } else {
                value = Integer.parseInt(input);
            }

        } catch (NumberFormatException exception) {
            throw new WrongInputException("Number was expected");
        }
    }

    @Override
    public int resolve() {
        return value;
    }

    @Override
    public void print(PrintStream stream) {
        stream.print(value);
    }
}
