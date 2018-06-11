package com.spbsu.a1arick.homework2.task2;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int arr[][] = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = in.nextInt();
            }
        }
        in.nextLine();
        String line = in.nextLine();
        Printer printer;
        if (line.equals("console")) {
            printer = new SpiralAlgorithmConsole();
        } else {
            printer = new SpiralAlgorithmFile("out.txt");
        }
        printer.print(arr);
    }
}
