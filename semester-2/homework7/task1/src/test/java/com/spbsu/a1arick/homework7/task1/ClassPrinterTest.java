package com.spbsu.a1arick.homework7.task1;

import com.spbsu.a1arick.homework7.task1.samples.Car;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ClassPrinterTest {

    @Test
    public void testCarClass() {
        String expected = "package com.spbsu.a1arick.homework7.task1.samples;\n" +
                "\n" +
                "public abstract class Car extends ArrayList<Integer> implements Serializable, Comparable<String> {\n" +
                "    private String name;\n" +
                "\n" +
                "    public String getName() {\n" +
                "        return name;\n" +
                "    }\n" +
                "\n" +
                "    public void setName(String name) {\n" +
                "        this.name = name;\n" +
                "    }\n" +
                "}";
        assertEquals(expected, ClassPrinter.print(Car.class));
    }
}