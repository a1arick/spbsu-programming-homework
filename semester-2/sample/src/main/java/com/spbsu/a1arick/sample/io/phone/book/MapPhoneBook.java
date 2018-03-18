package com.spbsu.a1arick.sample.io.phone.book;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public abstract class MapPhoneBook implements PhoneBook {

    private final Map<String, String> book = new HashMap<>();

    @Override
    public String getNumber(String name) {
        return book.get(name);
    }

    @Override
    public Map<String, String> getAll() {
        return Collections.unmodifiableMap(book);
    }

    @Override
    public void add(String name, String number) {
        book.put(name, number);
    }

    @Override
    public void addAll(Map<String, String> phoneBook) {
        book.putAll(phoneBook);
    }
}
