package com.spbsu.a1arick.homework7.task1;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class ClassPrinter {


    public static String print(Class clazz) {
        StringBuilder builder = new StringBuilder();
        addPackage(builder, clazz);
        addClass("", builder, clazz);
        return builder.toString();
    }

    private static void addPackage(StringBuilder builder, Class clazz) {
        builder.append(clazz.getPackage())
                .append(';')
                .append('\n')
                .append('\n');
    }

    private static void addClass(String tabs, StringBuilder builder, Class clazz) {
        addAnnotations(tabs, builder, clazz.getAnnotations());
        addClassName(tabs, builder, clazz);
        addFields(tabs + "\t", builder, clazz);
        addConstructor(tabs , builder, clazz);
        builder.append('\n').append(tabs).append("}\n\n");
    }

    private static void addAnnotations(String tabs, StringBuilder builder, Annotation[] annotations){
        Arrays.stream(annotations).forEach(annotation ->  builder.append(tabs).append(annotation).append('\n'));
    }

    private static void addClassName(String tabs, StringBuilder builder, Class clazz){
        String typeParameters = Arrays.stream(clazz.getTypeParameters())
                .map(Object::toString)
                .collect(Collectors.joining(", ", "<", ">"));
        builder.append(tabs)
                .append(Modifier.toString(clazz.getModifiers()))
                .append(" class ")
                .append(clazz.getSimpleName())
                .append(typeParameters)
                .append(' ');
        if(!clazz.getSuperclass().equals(Object.class)){
            builder.append("extends ")
                    .append(clazz.getGenericSuperclass())
                    .append(' ');
        }
        builder.append(Arrays.stream(clazz.getGenericInterfaces())
                .map(Object::toString)
                .collect(Collectors.joining(", ", "implements ", "")));
        builder.append(" {\n\n");
    }

    private static void addFields(String tabs, StringBuilder builder, Class clazz){
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            addAnnotations(tabs, builder, field.getAnnotations());
            builder.append(tabs)
                    .append(Modifier.toString(field.getModifiers()))
                    .append(field.getGenericType().getTypeName())
                    .append(' ')
                    .append(field.getName())
                    .append(";\n\n");
        }
    }

    private static void addConstructor(String tabs, StringBuilder builder, Class clazz) {
        if (clazz.getDeclaredConstructors().length == 0) {
            return ;
        }
        //addAnnotations(tabs, builder, clazz.getAnnotations());
        builder.append(Arrays.stream(clazz.getDeclaredConstructors())
                .map(constructor -> constructorToString(tabs, clazz, constructor))
                .collect(Collectors.joining( ";\n", "\n", ";\n")));
    }

    private static String constructorToString(String tabs, Class clazz, Constructor constructor) {

        return Modifier.toString(constructor.getModifiers()) + " "
                + clazz.getSimpleName()
                + Arrays.stream(constructor.getParameterTypes())
                .map(Type::getTypeName)
                .collect(Collectors.joining(", ", "(", ")"));
    }


    private Class clazz;
    public ClassPrinter(Class clazz) {
        this.clazz = clazz;
    }

    public void getName() {
        System.out.println(clazz.getSimpleName());
    }

    public void getModifiers() {
        int mods = clazz.getModifiers();
        Modifier.toString(mods);
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