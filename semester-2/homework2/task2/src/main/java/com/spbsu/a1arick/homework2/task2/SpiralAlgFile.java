package com.spbsu.a1arick.homework2.task2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class SpiralAlgFile extends AbstractSpiralAlg {

    @Override
    public void вывести(int[][] a) throws FileNotFoundException {
        вывести(a, new PrintStream("out.txt"));
    }
}
