package com.spbsu.a1arick.homework7.task1;

import java.io.IOException;

public class Main {
    public static class Power implements Appendable{

        private int m;

        public Power(int m){
            this.m = m;
        }

        @Override
        public Appendable append(CharSequence csq) throws IOException {
            return null;
        }

        @Override
        public Appendable append(CharSequence csq, int start, int end) throws IOException {
            return null;
        }

        @Override
        public Appendable append(char c) throws IOException {
            return null;
        }
    }

    public static void main(String[] args) {
        Power power = new Power(4);
        ClassPrinter classPrinter = new ClassPrinter(power.getClass());
        classPrinter.getName();
        classPrinter.getModifiers();
        classPrinter.getSuperclass();
        classPrinter.getInterfaces();
        classPrinter.getFields();
        classPrinter.getConstructors();
        classPrinter.getMethods();
    }
}
