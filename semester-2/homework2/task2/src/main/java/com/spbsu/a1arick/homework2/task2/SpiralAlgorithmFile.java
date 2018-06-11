package com.spbsu.a1arick.homework2.task2;

import java.io.IOException;
import java.io.PrintStream;

public class SpiralAlgorithmFile extends AbstractSpiralAlgorithm {

    private final String fileName;

    public SpiralAlgorithmFile(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void print(int[][] a) throws IOException {
        printToStream(a, new PrintStream(fileName));
    }
}
