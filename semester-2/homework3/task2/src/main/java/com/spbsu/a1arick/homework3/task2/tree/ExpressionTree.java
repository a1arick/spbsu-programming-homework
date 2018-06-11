package com.spbsu.a1arick.homework3.task2.tree;

import com.spbsu.a1arick.homework3.task2.exceptions.WrongInputException;
import com.spbsu.a1arick.homework3.task2.node.ExpressionTreeNode;
import com.spbsu.a1arick.homework3.task2.node.OperandNode;
import com.spbsu.a1arick.homework3.task2.node.OperatorNode;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * Expression tree class
 */
public class ExpressionTree {
    private ExpressionTreeNode head;

    /**
     * Constructs tree from {@link Scanner}
     *
     * @param scanner to read from
     * @throws WrongInputException if tree format is wrong
     */
    public ExpressionTree(Scanner scanner) throws WrongInputException {
        if (scanner.hasNextInt()) {
            head = new OperandNode(scanner);
        } else {
            head = new OperatorNode(scanner);
        }
    }

    /**
     * Prints tree to {@link PrintStream}
     *
     * @param stream to rpint in
     */
    public void print(PrintStream stream) {
        head.print(stream);
    }

    /**
     * Computes value in tree
     *
     * @return resolved value
     */
    public int resolve() {
        return head.resolve();
    }
}

