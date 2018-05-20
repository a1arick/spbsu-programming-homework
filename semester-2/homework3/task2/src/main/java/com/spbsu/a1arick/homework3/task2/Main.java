package com.spbsu.a1arick.homework3.task2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in;
        try {
            in = new Scanner(new File("input.txt"));
            ExpressionTree tree = new ExpressionTree(in);
            System.out.println("Expression tree:");
            tree.print(System.out);
            System.out.println("");
            System.out.print("Result: ");
            System.out.println(tree.resolve());
        } catch (FileNotFoundException fileException) {
            System.out.println("File is not found");
        } catch (WrongInputException wrongTreeException) {
            System.out.println(wrongTreeException.getMessage());
        }
    }
}
