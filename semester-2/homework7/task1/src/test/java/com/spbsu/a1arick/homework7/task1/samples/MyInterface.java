package com.spbsu.a1arick.homework7.task1.samples;

import java.util.function.Predicate;
import java.util.function.Supplier;

public interface MyInterface extends Predicate<Boolean>, Comparable<Number>{

    int a = 3;

    @SampleAnnotation
    String getName();

    <T> void f(int a, @SampleAnnotation String b, Supplier<T> supplier);
}
