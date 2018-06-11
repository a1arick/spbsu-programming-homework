package com.spbsu.a1arick.homework3.task2;

import com.spbsu.a1arick.homework3.task2.exceptions.WrongInputException;
import com.spbsu.a1arick.homework3.task2.tree.ExpressionTree;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class ExpressionTreeTest {

    @Test
    public void calculateTest() throws WrongInputException {
        ExpressionTree tree = new ExpressionTree(new Scanner("- 3 (/ (* 5 6) 2"));
        assertEquals(-12, tree.resolve());
    }

    @Test(expected = WrongInputException.class)
    public void noOperandExceptionTest() throws WrongInputException {
        ExpressionTree noOperand = new ExpressionTree(new Scanner("- - 2 )"));
        noOperand.resolve();
    }

    @Test(expected = WrongInputException.class)
    public void wrongBracketsExceptionTest() throws WrongInputException {
        ExpressionTree wrongBrackets = new ExpressionTree(new Scanner("(+ (-2 (- -4 6))"));
        wrongBrackets.resolve();
    }

    @Test
    public void printTest() throws WrongInputException {
        ExpressionTree tree = new ExpressionTree(new Scanner("(- (+ 2 4) (* 1 15))"));
        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        tree.print(new PrintStream(arrayOutputStream));
        String EXPECTED = "(- (+ 2 4) (* 1 15))";
        assertEquals(EXPECTED, arrayOutputStream.toString());
    }
}