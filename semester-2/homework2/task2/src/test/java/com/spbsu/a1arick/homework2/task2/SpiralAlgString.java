package com.spbsu.a1arick.homework2.task2;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class SpiralAlgString extends AbstractSpiralAlg {

    private ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

    public String getOutput(){
        return byteArrayOutputStream.toString();
    }

    @Override
    public void вывести(int[][] a) {
        вывести(a, new PrintStream(byteArrayOutputStream));
    }
}
