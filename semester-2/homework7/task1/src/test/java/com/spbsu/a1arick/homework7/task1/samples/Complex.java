package com.spbsu.a1arick.homework7.task1.samples;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class Complex {

    public int a;

    @SampleAnnotation
    private String name;

    void f() {
    }

    enum SomeEnum implements Comparable<SomeEnum> {
        A, B, C;

        @Retention(RetentionPolicy.RUNTIME)
        @Target({ElementType.TYPE, ElementType.FIELD, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.METHOD})
        public @interface SampleAnnotation1 {
            String value() default "aaa";

            interface F {
                void f();
            }
        }
    }


}
