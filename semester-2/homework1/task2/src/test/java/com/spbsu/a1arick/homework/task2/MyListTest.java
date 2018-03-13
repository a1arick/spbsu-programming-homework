package com.spbsu.a1arick.homework.task2;

import org.junit.Test;

import static org.junit.Assert.*;

public class MyListTest {
    @Test
    public void get() {
        MyList list = new MyList();
        int n = 10;
        for (int i = 0; i < n; i++) {
            list.add(i);
        }

        for (int i = 0; i < n; i++) {
            assertEquals(i, list.get(i));
        }
    }

    @Test
    public void remove() {
        int n = 10;
        for (int i = 0; i < n; i++) {
            MyList list = new MyList();
            for (int j = 0; j < n; j++) {
                list.add(j);
            }
            assertEquals(i,list.remove(i));
        }

    }
}