package com.spbsu.a1arick.homework7.task1;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.stream.Collectors;


/**
 * Class for printing class structure
 */
public class ClassPrinter {

    /**
     * Prints class structure
     *
     * @param clazz to print
     * @return class structure
     */
    public static String print(Class clazz) {
        StringBuilder builder = new StringBuilder();
        addPackage(builder, clazz);
        addClass("", builder, clazz);
        return builder.toString();
    }

    private static void addPackage(StringBuilder builder, Class clazz) {
        builder.append(clazz.getPackage()).append(";\n\n");
    }

    private static void addClass(String tabs, StringBuilder builder, Class clazz) {
        addAnnotations(tabs, builder, clazz.getAnnotations());
        addClassName(tabs, builder, clazz);
        builder.append(" {\n\n");
        addFields(tabs + "\t", builder, clazz);
        addConstructors(tabs + "\t", builder, clazz);
        addMethods(tabs + "\t", builder, clazz);
        Arrays.stream(clazz.getDeclaredClasses()).forEach(nestedClass -> addClass(tabs + "\t", builder, nestedClass));
        builder.append(tabs).append("}\n\n");
    }

    private static void addAnnotations(String tabs, StringBuilder builder, Annotation[] annotations) {
        Arrays.stream(annotations).forEach(annotation -> builder.append(tabs).append(annotation).append('\n'));
    }

    private static String getAnnotations(Annotation[] annotations) {
        return Arrays.stream(annotations).map(Object::toString).collect(Collectors.joining(" "));
    }

    private static void addClassName(String tabs, StringBuilder builder, Class clazz) {
        String typeParameters = getTypeParameters(clazz.getTypeParameters());
        builder.append(tabs)
                .append(getModifiers(clazz))
                .append(clazz.getSimpleName())
                .append(typeParameters);
        if (clazz.getSuperclass() != null && !clazz.getSuperclass().equals(Object.class) && !clazz.getSuperclass().equals(Enum.class)) {
            builder.append("extends ")
                    .append(clazz.getGenericSuperclass())
                    .append(' ');
        }
        if (!clazz.isAnnotation()) {
            String interfaces = Arrays.stream(clazz.getGenericInterfaces())
                    .map(Object::toString)
                    .collect(Collectors.joining(", "));

            String action = clazz.isInterface() ? " extends " : " implements ";

            builder.append(interfaces.length() > 0 ? action + interfaces : "");
        }
    }

    private static void addFields(String tabs, StringBuilder builder, Class clazz) {
        if (clazz.isEnum()) {
            //noinspection unchecked
            builder.append(tabs)
                    .append(EnumSet.allOf(clazz).stream()
                            .map(Object::toString)
                            .collect(Collectors.joining(", ", "", ";\n\n")));
        }
        Arrays.stream(clazz.getDeclaredFields())
                .filter(field -> !field.isSynthetic() && !field.isEnumConstant())
                .forEach(field -> addField(tabs, builder, clazz, field));
    }

    private static void addField(String tabs, StringBuilder builder, Class clazz, Field field) {
        field.setAccessible(true);
        addAnnotations(tabs, builder, field.getAnnotations());
        builder.append(tabs)
                .append(getModifiers(field.getModifiers()))
                .append(field.getGenericType().getTypeName())
                .append(' ')
                .append(field.getName())
                .append(";\n\n");
    }

    private static void addConstructors(String tabs, StringBuilder builder, Class clazz) {
        Arrays.stream(clazz.getDeclaredConstructors())
                .forEach(constructor -> addConstructor(tabs, builder, clazz, constructor));
    }

    private static void addConstructor(String tabs, StringBuilder builder, Class clazz, Constructor constructor) {
        constructor.setAccessible(true);
        addAnnotations(tabs, builder, constructor.getAnnotations());
        int[] counter = {0};
        Annotation[][] parameterAnnotations = constructor.getParameterAnnotations();
        String args = Arrays.stream(constructor.getGenericParameterTypes())
                .map(type -> getAnnotations(parameterAnnotations[counter[0]]) + " " + type.getTypeName() + " arg" + (counter[0]++))
                .collect(Collectors.joining(", ", "(", ")"));
        String exceptions = Arrays.stream(constructor.getGenericExceptionTypes())
                .map(Type::getTypeName)
                .collect(Collectors.joining(", "));
        builder.append(tabs)
                .append(getModifiers(constructor.getModifiers()))
                .append(clazz.getSimpleName())
                .append(args)
                .append(exceptions.length() > 0 ? " throws " + exceptions : "")
                .append(";\n\n");
    }

    private static void addMethods(String tabs, StringBuilder builder, Class clazz) {
        Arrays.stream(clazz.getDeclaredMethods())
                .forEach(method -> addMethod(tabs, builder, clazz, method));
    }

    private static void addMethod(String tabs, StringBuilder builder, Class clazz, Method method) {
        method.setAccessible(true);
        addAnnotations(tabs, builder, method.getAnnotations());
        int[] counter = {0};
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        String args = Arrays.stream(method.getGenericParameterTypes())
                .map(type -> getAnnotations(parameterAnnotations[counter[0]]) + " " + type.getTypeName() + " arg" + (counter[0]++))
                .collect(Collectors.joining(", ", "(", ")"));
        String exceptions = Arrays.stream(method.getGenericExceptionTypes())
                .map(Type::getTypeName)
                .collect(Collectors.joining(", "));
        builder.append(tabs)
                .append(getModifiers(method.getModifiers()))
                .append(getTypeParameters(method.getTypeParameters()))
                .append(method.getGenericReturnType().getTypeName())
                .append(' ')
                .append(method.getName())
                .append(args)
                .append(exceptions.length() > 0 ? " throws " + exceptions : "")
                .append(clazz.isAnnotation() && method.getDefaultValue() != null ? " default " + method.getDefaultValue() : "")
                .append(";\n\n");
    }

    private static String getModifiers(Class clazz) {
        if (clazz.isAnnotation()) {
            return getModifiers(clazz.getModifiers() & (~Modifier.INTERFACE) & (~Modifier.ABSTRACT)) + "@interface ";
        } else if (clazz.isEnum()) {
            return getModifiers(clazz.getModifiers()) + "enum ";
        } else if (clazz.isInterface()) {
            return getModifiers(clazz.getModifiers());
        } else {
            return getModifiers(clazz.getModifiers()) + "class ";
        }
    }

    private static String getModifiers(int modifiers) {
        return Modifier.toString(modifiers) + (modifiers != 0 ? " " : "");
    }

    private static String getTypeParameters(TypeVariable[] typeParameters) {
        if (typeParameters.length == 0) {
            return "";
        }
        return Arrays.stream(typeParameters).map(Object::toString).collect(Collectors.joining(", ", "<", "> "));
    }
}