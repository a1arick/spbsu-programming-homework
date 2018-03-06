package com.spbsu.a1arick.homework2.task2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("in.txt"));
        int n = in.nextInt();
        int arr[][] = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = in.nextInt();
            }
        }
        Scanner console = new Scanner(System.in);
        String line = console.nextLine();
        Выводилка выводилка;
        if (line.equals("console")) {
            выводилка = new SpiralAlgConsole();
        } else {
            выводилка = new SpiralAlgFile();
        }
        выводилка.вывести(arr);
    }
}
