package com.spbsu.a1arick.homework9.task1;

import javafx.util.Pair;

import java.util.Random;

public class Bots {
    private int n;

    public Bots(int n) {
        this.n = n;
    }

    public Pair<Integer,Integer> makeTurn(){
        Random randomI = new Random();
        Random randomJ = new Random();
        int numI = randomI.nextInt(n);
        int numJ = randomI.nextInt(n);
        return new Pair<>(numI, numJ);
    }
}
