package com.spbsu.a1arick.homework3.task2.node;

import java.io.PrintStream;

/**
 * Expression tree node interface
 */
public interface ExpressionTreeNode {
    /**
     * Computes expression in node
     *
     * @return resolved value
     */
    int resolve();

    /**
     * Prints node to {@link PrintStream}
     *
     * @param stream to print in
     */
    void print(PrintStream stream);
}
