package com.spbsu.a1arick.homework7.task1.samples;

import com.sun.xml.internal.ws.api.FeatureConstructor;
import javafx.util.Pair;

import javax.annotation.Resource;
import javax.management.MXBean;
import javax.xml.bind.annotation.XmlAnyElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@MXBean
@Resource
@SampleAnnotation(v1 = "aab")
public abstract class Car<T, P, R, S, F>
        extends ArrayList<Pair<? extends Integer, Map<? super String, Set<Map<Long, Car<T, P, R, S, F>>>>>>
        implements Serializable, Comparable<String> {

    private final static int a = 1;
    private T temp;

    private String name;

    public Car(@SampleAnnotation int initialCapacity, Consumer<Map<String, P>> consumer, Function<R, S>[] functions) throws IllegalArgumentException, IllegalStateException {
        super(initialCapacity);
        this.name = "";
        class A {
            private int a;
        }
    }

    @FeatureConstructor
    @SampleAnnotation
    private Car(String name) {
        this.name = name;
    }

    protected Car(int initialCapacity, T temp) {
        super(initialCapacity);
        this.temp = temp;
    }

    Car() {
        super(0);
        this.temp = null;
    }

    @SampleAnnotation
    @XmlAnyElement
    private final Map<String, Comparable<Number>> map = Collections.emptyMap();

    Supplier<Comparable<Number>>[] v;
    transient volatile char[] c;

    public Map<F, P> getName() throws IllegalArgumentException, IllegalStateException  {
        return null;
    }

    @SafeVarargs
    public final synchronized  <Y> void setName(@SampleAnnotation String name, Consumer<Map<String, P>> consumer,  Integer... args) {
        this.name = name;
    }

    protected abstract <F, P> Long getName1();

    private static class Road{
        @SampleAnnotation private String name;
        void f(){}

        public class Road1{
            @SampleAnnotation private String name;
            void f(){}
        }
    }

    public class Road2{
        @SampleAnnotation private String name;
        void f(){}
    }
}
