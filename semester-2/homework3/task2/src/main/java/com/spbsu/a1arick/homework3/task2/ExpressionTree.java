package com.spbsu.a1arick.homework3.task2;

import java.io.PrintStream;
import java.util.Scanner;

public class ExpressionTree {
    private ExpressionTreeNode head;

    ExpressionTree(Scanner scanner) throws WrongInputException {
        if (scanner.hasNextInt()) {
            head = new OperandNode(scanner);
        } else {
            head = new OperatorNode(scanner);
        }
    }

    public void print(PrintStream stream) {
        head.print(stream);
    }

    public int resolve() {
        return head.resolve();
    }
}

