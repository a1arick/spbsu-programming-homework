package com.spbsu.a1arick.homework7.task1;

import com.spbsu.a1arick.homework7.task1.samples.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ClassPrinterTest {

    @Test
    public void testCarClass() {
        String expected = "package com.spbsu.a1arick.homework7.task1.samples;\n" +
                "\n" +
                "@javax.management.MXBean(value=true)\n" +
                "@javax.annotation.Resource(shareable=true, lookup=, name=, description=, authenticationType=CONTAINER, type=class java.lang.Object, mappedName=)\n" +
                "@com.spbsu.a1arick.homework7.task1.samples.SampleAnnotation(value=aaa, v1=aab)\n" +
                "public abstract class Car<T, P, R, S, F> extends java.util.ArrayList<javafx.util.Pair<? extends java.lang.Integer, java.util.Map<? super java.lang.String, java.util.Set<java.util.Map<java.lang.Long, com.spbsu.a1arick.homework7.task1.samples.Car<T, P, R, S, F>>>>>>  implements interface java.io.Serializable, java.lang.Comparable<java.lang.String> {\n" +
                "\n" +
                "\tprivate static final int a;\n" +
                "\n" +
                "\tprivate T temp;\n" +
                "\n" +
                "\tprivate java.lang.String name;\n" +
                "\n" +
                "\t@com.spbsu.a1arick.homework7.task1.samples.SampleAnnotation(value=aaa, v1=aaa)\n" +
                "\t@javax.xml.bind.annotation.XmlAnyElement(value=class javax.xml.bind.annotation.W3CDomHandler, lax=false)\n" +
                "\tprivate final java.util.Map<java.lang.String, java.lang.Comparable<java.lang.Number>> map;\n" +
                "\n" +
                "\tjava.util.function.Supplier<java.lang.Comparable<java.lang.Number>>[] v;\n" +
                "\n" +
                "\ttransient volatile char[] c;\n" +
                "\n" +
                "\tCar();\n" +
                "\n" +
                "\tprotected Car( int arg0,  T arg1);\n" +
                "\n" +
                "\t@com.sun.xml.internal.ws.api.FeatureConstructor(value=[])\n" +
                "\t@com.spbsu.a1arick.homework7.task1.samples.SampleAnnotation(value=aaa, v1=aaa)\n" +
                "\tprivate Car( java.lang.String arg0);\n" +
                "\n" +
                "\tpublic Car(@com.spbsu.a1arick.homework7.task1.samples.SampleAnnotation(value=aaa, v1=aaa) int arg0,  java.util.function.Consumer<java.util.Map<java.lang.String, P>> arg1,  java.util.function.Function<R, S>[] arg2) throws java.lang.IllegalArgumentException, java.lang.IllegalStateException;\n" +
                "\n" +
                "\tpublic java.util.Map<F, P> getName() throws java.lang.IllegalArgumentException, java.lang.IllegalStateException;\n" +
                "\n" +
                "\t@java.lang.SafeVarargs()\n" +
                "\tpublic final transient synchronized <Y> void setName(@com.spbsu.a1arick.homework7.task1.samples.SampleAnnotation(value=aaa, v1=aaa) java.lang.String arg0,  java.util.function.Consumer<java.util.Map<java.lang.String, P>> arg1,  java.lang.Integer[] arg2);\n" +
                "\n" +
                "\tprotected abstract <F, P> java.lang.Long getName1();\n" +
                "\n" +
                "\tpublic class Road2 {\n" +
                "\n" +
                "\t\t@com.spbsu.a1arick.homework7.task1.samples.SampleAnnotation(value=aaa, v1=aaa)\n" +
                "\t\tprivate java.lang.String name;\n" +
                "\n" +
                "\t\tpublic Road2( com.spbsu.a1arick.homework7.task1.samples.Car arg0);\n" +
                "\n" +
                "\t\tvoid f();\n" +
                "\n" +
                "\t}\n" +
                "\n" +
                "\tprivate static class Road {\n" +
                "\n" +
                "\t\t@com.spbsu.a1arick.homework7.task1.samples.SampleAnnotation(value=aaa, v1=aaa)\n" +
                "\t\tprivate java.lang.String name;\n" +
                "\n" +
                "\t\tprivate Road();\n" +
                "\n" +
                "\t\tvoid f();\n" +
                "\n" +
                "\t\tpublic class Road1 {\n" +
                "\n" +
                "\t\t\t@com.spbsu.a1arick.homework7.task1.samples.SampleAnnotation(value=aaa, v1=aaa)\n" +
                "\t\t\tprivate java.lang.String name;\n" +
                "\n" +
                "\t\t\tpublic Road1( com.spbsu.a1arick.homework7.task1.samples.Car$Road arg0);\n" +
                "\n" +
                "\t\t\tvoid f();\n" +
                "\n" +
                "\t\t}\n" +
                "\n" +
                "\t}\n" +
                "\n" +
                "}\n" +
                "\n";
        assertEquals(expected, ClassPrinter.print(Car.class));
    }

    @Test
    public void testAnnotation() {
        String expected = "package com.spbsu.a1arick.homework7.task1.samples;\n" +
                "\n" +
                "@java.lang.annotation.Retention(value=RUNTIME)\n" +
                "@java.lang.annotation.Target(value=[TYPE, FIELD, CONSTRUCTOR, PARAMETER, METHOD])\n" +
                "public @interface SampleAnnotation {\n" +
                "\n" +
                "\tpublic abstract java.lang.String value() default aaa;\n" +
                "\n" +
                "\tpublic abstract java.lang.String v1() default aaa;\n" +
                "\n" +
                "}\n" +
                "\n";
        assertEquals(expected, ClassPrinter.print(SampleAnnotation.class));
    }

    @Test
    public void testInterface(){
        String expected = "package com.spbsu.a1arick.homework7.task1.samples;\n" +
                "\n" +
                "public abstract interface MyInterface extends java.util.function.Predicate<java.lang.Boolean>, java.lang.Comparable<java.lang.Number> {\n" +
                "\n" +
                "\tpublic static final int a;\n" +
                "\n" +
                "\t@com.spbsu.a1arick.homework7.task1.samples.SampleAnnotation(value=aaa, v1=aaa)\n" +
                "\tpublic abstract java.lang.String getName();\n" +
                "\n" +
                "\tpublic abstract <T> void f( int arg0, @com.spbsu.a1arick.homework7.task1.samples.SampleAnnotation(value=aaa, v1=aaa) java.lang.String arg1,  java.util.function.Supplier<T> arg2);\n" +
                "\n" +
                "}\n" +
                "\n";
        assertEquals(expected, ClassPrinter.print(MyInterface.class));
    }

    @Test
    public void testEnum() {
        String expected = "package com.spbsu.a1arick.homework7.task1.samples;\n" +
                "\n" +
                "public final enum Shop {\n" +
                "\n" +
                "\tPRODUCTS, DISKS, CARS, ELECTRONICKS, CLOTHES;\n" +
                "\n" +
                "\tprivate final int field;\n" +
                "\n" +
                "\tprivate Shop();\n" +
                "\n" +
                "\tpublic static com.spbsu.a1arick.homework7.task1.samples.Shop[] values();\n" +
                "\n" +
                "\tpublic static com.spbsu.a1arick.homework7.task1.samples.Shop valueOf( java.lang.String arg0);\n" +
                "\n" +
                "\tpublic static java.lang.String r();\n" +
                "\n" +
                "\tprivate void f();\n" +
                "\n" +
                "\tpublic class Road3 {\n" +
                "\n" +
                "\t\t@com.spbsu.a1arick.homework7.task1.samples.SampleAnnotation(value=aaa, v1=aaa)\n" +
                "\t\tprivate java.lang.String name;\n" +
                "\n" +
                "\t\tpublic Road3( com.spbsu.a1arick.homework7.task1.samples.Shop arg0);\n" +
                "\n" +
                "\t\tvoid f();\n" +
                "\n" +
                "\t}\n" +
                "\n" +
                "}\n" +
                "\n";
        assertEquals(expected, ClassPrinter.print(Shop.class));
    }

    @Test
    public void testComplex() {
        String expected = "package com.spbsu.a1arick.homework7.task1.samples;\n" +
                "\n" +
                "public class Complex {\n" +
                "\n" +
                "\tpublic int a;\n" +
                "\n" +
                "\t@com.spbsu.a1arick.homework7.task1.samples.SampleAnnotation(value=aaa, v1=aaa)\n" +
                "\tprivate java.lang.String name;\n" +
                "\n" +
                "\tpublic Complex();\n" +
                "\n" +
                "\tvoid f();\n" +
                "\n" +
                "\tstatic final enum SomeEnum implements java.lang.Comparable<com.spbsu.a1arick.homework7.task1.samples.Complex$SomeEnum> {\n" +
                "\n" +
                "\t\tA, B, C;\n" +
                "\n" +
                "\t\tprivate SomeEnum();\n" +
                "\n" +
                "\t\tpublic static com.spbsu.a1arick.homework7.task1.samples.Complex$SomeEnum[] values();\n" +
                "\n" +
                "\t\tpublic static com.spbsu.a1arick.homework7.task1.samples.Complex$SomeEnum valueOf( java.lang.String arg0);\n" +
                "\n" +
                "\t\t@java.lang.annotation.Retention(value=RUNTIME)\n" +
                "\t\t@java.lang.annotation.Target(value=[TYPE, FIELD, CONSTRUCTOR, PARAMETER, METHOD])\n" +
                "\t\tpublic static @interface SampleAnnotation1 {\n" +
                "\n" +
                "\t\t\tpublic abstract java.lang.String value() default aaa;\n" +
                "\n" +
                "\t\t\tpublic abstract static interface F {\n" +
                "\n" +
                "\t\t\t\tpublic abstract void f();\n" +
                "\n" +
                "\t\t\t}\n" +
                "\n" +
                "\t\t}\n" +
                "\n" +
                "\t}\n" +
                "\n" +
                "}\n" +
                "\n";
        assertEquals(expected, ClassPrinter.print(Complex.class));
    }
}