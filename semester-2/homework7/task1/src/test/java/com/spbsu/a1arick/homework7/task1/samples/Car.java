package com.spbsu.a1arick.homework7.task1.samples;

import javafx.util.Pair;

import javax.annotation.Resource;
import javax.management.MXBean;
import javax.xml.bind.annotation.XmlAnyElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

@MXBean
@Resource
@SampleAnnotation(v1 = "aab")
public abstract class Car<T, P, R, S, F>
        extends ArrayList<Pair<? extends Integer, Map<? super String, Set<Map<Long, Car<T, P, R, S, F>>>>>>
        implements Serializable, Comparable<String> {

    private final static int a = 1;

    private String name;

    public Car(int initialCapacity, String name) {
        super(initialCapacity);
        this.name = name;
    }

    private Car(String name) {
        this.name = name;
    }

    @SampleAnnotation
    @XmlAnyElement
    private final Map<String, Comparable<Number>> map = Collections.emptyMap();

    Supplier<Comparable<Number>>[] v;
    char[] c;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
