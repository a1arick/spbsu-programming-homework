package com.spbsu.a1arick.homework8.task1;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class Benchmark {

    private static final int N = 100;
    private static final int[] array = new int[1000000];
    private static final long[] measurements = new long[N];
    private static final int filterPercent = 5;

    public static void main(String[] args) {
        long sequential = mean(new SequentialQuickSort());
        System.out.println("SequentialQuickSort(ms): " + sequential);
        long parallel = mean(new ParallelQuickSort());
        System.out.println("ParallelQuickSort(ms): " + parallel);
        System.out.println("diff(ms): " + (sequential - parallel));
        System.out.println("%: " + (parallel * 100.0 / sequential));
    }

    private static long mean(QuickSort quickSort) {

        for (int i = 0; i < N; i++) {
            fillRandom(array);
            long time = System.currentTimeMillis();
            quickSort.sort(array);
            measurements[i] = System.currentTimeMillis() - time;
        }
        Arrays.sort(measurements);
        long mean = 0;
        int count = 0;
        for (int i = N * filterPercent / 100; i < N * (100 - filterPercent) / 100; i++) {
            mean += measurements[i];
            count++;
        }
        return mean / count;
    }

    private static void fillRandom(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = ThreadLocalRandom.current().nextInt();
        }
    }
}
