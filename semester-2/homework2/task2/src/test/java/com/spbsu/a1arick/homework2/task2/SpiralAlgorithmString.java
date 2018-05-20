package com.spbsu.a1arick.homework2.task2;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class SpiralAlgorithmString extends AbstractSpiralAlgorithm {

    private ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

    public String getOutput() {
        return byteArrayOutputStream.toString();
    }

    @Override
    public void print(int[][] a) {
        printToStream(a, new PrintStream(byteArrayOutputStream));
    }
}
