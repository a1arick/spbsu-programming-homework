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
        ClassPrinter1 classPrinter1 = new ClassPrinter1(power.getClass());
        classPrinter1.getName();
        classPrinter1.getModifiers();
        classPrinter1.getSuperclass();
        classPrinter1.getInterfaces();
        classPrinter1.getFields();
        classPrinter1.getConstructors();
        classPrinter1.getMethods();
    }
}
