package com.spbsu.a1arick;

public class ElementNotFoundException extends RuntimeException {
    public ElementNotFoundException(Object element) {
        super(String.format("Element %s not found", element));
    }
}
