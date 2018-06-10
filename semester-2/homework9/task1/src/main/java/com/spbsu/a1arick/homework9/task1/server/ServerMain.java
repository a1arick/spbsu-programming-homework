package com.spbsu.a1arick.homework9.task1.server;

public class ServerMain {
    public static void main(String[] args) {
        try {
            new ServerRunnable(1111).run();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
