package com.spbsu.a1arick.exception;

public class ElementNotFoundException extends Exception {
    public ElementNotFoundException(Object element) {
        super(String.format("Element %s not found", element));
    }
}
