package com.spbsu.a1arick.homework2.task3.stack.impl;

import com.spbsu.a1arick.homework2.task3.exception.DataStructureIsEmptyException;
import com.spbsu.a1arick.homework2.task3.stack.Stack;

/**
 * Array based stack
 */
public class ArrayStack implements Stack {

    private int length = 10;
    private int head = -1;
    private int[] array = new int[length];

    @Override
    public void push(int value) {
        if (head == length - 1) {
            int[] newArray = new int[2 * length];
            System.arraycopy(array, 0, newArray, 0, newArray.length);
            array = newArray;
            length = length * 2;
        }
        head++;
        array[head] = value;
    }

    @Override
    public int pop() throws DataStructureIsEmptyException {
        if (isEmpty()) {
            throw new DataStructureIsEmptyException("Array stack is empty");
        } else {
            int temp = array[head];
            head--;
            return temp;
        }
    }

    @Override
    public boolean isEmpty() {
        return head == -1;
    }

}
