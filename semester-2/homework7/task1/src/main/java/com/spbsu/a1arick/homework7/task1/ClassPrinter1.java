package com.spbsu.a1arick.homework7.task1;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.LinkedList;

public class ClassPrinter1 {
    private Class clazz;

    public ClassPrinter1(Class clazz) {
        this.clazz = clazz;
    }

    public void getName() {
        System.out.println(clazz.getSimpleName());
    }

    public void getModifiers() {
        int mods = clazz.getModifiers();
        if (Modifier.isPublic(mods)) {
            System.out.println("public");
        }
        if (Modifier.isAbstract(mods)) {
            System.out.println("abstract");
        }
        if (Modifier.isFinal(mods)) {
            System.out.println("final");
        }
    }

    public void getSuperclass() {
        clazz.getSuperclass();
    }

    public void getInterfaces() {
        Class c = clazz;
        c = LinkedList.class;
        Class[] interfaces = c.getInterfaces();
        System.out.println("Constructors");
        System.out.println("--------------------------------(");
        for (Class cInterface : interfaces) {
            System.out.println(cInterface.getName());
        }
        System.out.println("--------------------------------)");
    }

    public void getFields() {
        Field[] publicFields = clazz.getFields();
        System.out.println("Fields");
        System.out.println("--------------------------------(");
        for (Field field : publicFields) {
            Class fieldType = field.getType();
            System.out.println("Имя: " + field.getName());
            System.out.println("Тип: " + fieldType.getName());
        }
        System.out.println("--------------------------------)");
    }

    public void getConstructors() {
        Constructor[] constructors = clazz.getConstructors();
        System.out.println("Constructors");
        System.out.println("--------------------------------(");
        for (Constructor constructor : constructors) {
            Class[] paramTypes = constructor.getParameterTypes();
            for (Class paramType : paramTypes) {
                System.out.print(paramType.getName() + " ");
            }
            System.out.println();
        }
        System.out.println("--------------------------------)");
    }

    public void getMethods() {
        Method[] methods = clazz.getMethods();
        System.out.println("Methods");
        System.out.println("--------------------------------(");
        for (Method method : methods) {
            System.out.println("Имя: " + method.getName());
            System.out.println("Возвращаемый тип: " + method.getReturnType().getName());

            Class[] paramTypes = method.getParameterTypes();
            System.out.print("Типы параметров: ");
            for (Class paramType : paramTypes) {
                System.out.print(" " + paramType.getName());
            }
            System.out.println();
        }
        System.out.println("--------------------------------)");
    }


}