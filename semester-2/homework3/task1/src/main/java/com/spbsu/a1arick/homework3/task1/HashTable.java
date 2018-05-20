package com.spbsu.a1arick.homework3.task1;

import java.util.ArrayList;
import java.util.LinkedList;

public class HashTable {
    private ArrayList<LinkedList<String>> table;
    private HashFunction hashFunction;
    private int textNumber = 0;
    private int conflictNumber = 0;

    HashTable(int size, HashFunction hashFunction) {
        table = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            table.add(new LinkedList<>());
        }
        this.hashFunction = hashFunction;
    }

    public void add(String text) {
        int index = hashFunction.hash(text, table.size());
        if (table.get(index).size() == 1) {
            conflictNumber++;
        }
        table.get(hashFunction.hash(text, table.size())).add(text);
        textNumber++;
    }

    public void delete(String text) throws TextNotFoundException {
        int index = hashFunction.hash(text, table.size());
        if (table.get(index).remove(text)){
            textNumber--;
            if (table.get(index).size() == 1) {
                conflictNumber--;
            }
        } else {
            throw new TextNotFoundException();
        }
    }

    public boolean contains(String text) {
        return table.get(hashFunction.hash(text, table.size())).contains(text);
    }

    public int gettextNumber() {
        return textNumber;
    }

    public int getConflictNumber() {
        return conflictNumber;
    }

    public int getMaxListSize() {
        int result = 0;
        for (LinkedList list: table) {
            result = Integer.max(result, list.size());
        }
        return result;
    }

    public double loadFactor() {
        return (double) gettextNumber() / table.size();
    }
}