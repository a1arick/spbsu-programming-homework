package com.spbsu.a1arick.sample.io.phone.book;

import com.sun.xml.internal.ws.api.ha.StickyFeature;

import java.util.Map;

public interface PhoneBook {
    String getNumber(String name);

    Map<String, String> getAll();

    void add(String name, String number);

    void addAll(Map<String, String> phoneBook);
}
