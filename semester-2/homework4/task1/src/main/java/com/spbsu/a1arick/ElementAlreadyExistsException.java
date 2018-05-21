package com.spbsu.a1arick;

public class ElementAlreadyExistsException extends Exception {
    public ElementAlreadyExistsException(Object element) {
        super(String.format("Element %s already exists", element));
    }
}
