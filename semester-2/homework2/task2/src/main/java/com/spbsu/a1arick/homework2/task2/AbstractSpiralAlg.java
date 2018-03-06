package com.spbsu.a1arick.homework2.task2;

import java.io.PrintStream;

abstract class AbstractSpiralAlg implements Выводилка {

    public void вывести(int[][] a, PrintStream printStream) {
        int startIndex = a[0].length / 2;
        printStream.print(a[startIndex][startIndex]);
        printStream.print(" ");

        int leftBorder = startIndex;
        int upBorder = startIndex - 1;
        int rightBorder = startIndex;
        int downBorder = startIndex;

        int i = leftBorder;
        int j = upBorder;

        while (j >= 0) {
            while (i <= rightBorder) {
                printStream.print(a[j][i++]);
                printStream.print(" ");
            }
            ++rightBorder;

            while (j <= downBorder) {
                printStream.print(a[j++][i]);
                printStream.print(" ");
            }
            ++downBorder;

            while (i >= leftBorder) {
                printStream.print(a[j][i--]);
                printStream.print(" ");
            }
            --leftBorder;

            while (j >= upBorder) {
                printStream.print(a[j--][i]);
                printStream.print(" ");
            }
            --upBorder;
        }
        printStream.flush();
    }
}
