package com.spbsu.a1arick.homework7.task1.samples;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.CONSTRUCTOR})
@interface SampleAnnotation {
    String value() default "aaa";
    String v1() default "aaa";
}
