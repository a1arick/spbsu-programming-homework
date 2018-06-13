package com.spbsu.a1arick.homework9.task1.server;

/**
 * Server main
 */
public class ServerMain {
    public static void main(String[] args) {
        int port = 1111;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }
        try {
            new ServerRunnable(port).run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
