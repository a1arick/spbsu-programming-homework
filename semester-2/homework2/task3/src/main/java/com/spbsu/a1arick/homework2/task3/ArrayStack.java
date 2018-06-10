package com.spbsu.a1arick.homework2.task3;

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
        if (head > -1) {
            int temp = array[head];
            head--;
            return temp;
        } else {
            throw new DataStructureIsEmptyException("Array stack is empty");
        }
    }

    @Override
    public boolean isEmpty() {
        return head == -1;
    }

}
