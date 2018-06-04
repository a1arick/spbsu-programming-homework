package com.spbsu.a1arick.homework8.task1;

import java.util.concurrent.RecursiveTask;

class QuickSortTask extends RecursiveTask<Void> {

    private final int[] array;
    private final int left;
    private final int right;

    public QuickSortTask(int[] array, int left, int right) {
        this.array = array;
        this.left = left;
        this.right = right;
    }

    @Override
    protected Void compute() {
        int index = QuickSort.partition(array, left, right);
        QuickSortTask leftTask = null;
        if (left < index - 1) {
            leftTask = new QuickSortTask(array, left, index - 1);
            leftTask.fork();
        }
        QuickSortTask rightTask = null;
        if (index < right) {
            rightTask = new QuickSortTask(array, index, right);
            rightTask.fork();
        }
        if(leftTask != null){
            leftTask.join();
        }
        if(rightTask != null){
            rightTask.join();
        }
        return null;
    }
}
