package com.spbsu.a1arick.homework4.task2;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class AVLTreeTest {
    @Test
    public void name() {
        AVLTree<Integer> tree = new AVLTree<>();
        int n = 1000;
        for (int i = n; i >= 0; i--) {
            tree.add(i);
        }
        System.out.println(tree);

        for (int i = n; i >= 0; i--) {
            tree.remove(i);
            System.out.println(tree);
        }

    }
}