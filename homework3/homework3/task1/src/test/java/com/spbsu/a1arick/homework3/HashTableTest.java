package com.spbsu.a1arick.homework3;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class HashTableTest {
    @Test
    public void addTest() {
        HashTable hashTable = new HashTable(322, new LengthHash());
        hashTable.add("A");
        hashTable.add("B");
        hashTable.add("C");
        hashTable.add("A");
        assertEquals(4, hashTable.getElementNumber());
    }

    @Test
    public void removeTest() throws NoSuchElementException {
        HashTable hashTable = new HashTable(322, new LengthHash());
        hashTable.add("A");
        hashTable.add("B");
        hashTable.add("C");
        hashTable.remove("A");
        assertFalse(hashTable.contains("A"));
        assertEquals(2, hashTable.getElementNumber());
    }

    @Test
    public void containsTest() throws NoSuchElementException {
        HashTable hashTable = new HashTable(322, new LengthHash());
        hashTable.add("A");
        hashTable.add("B");
        hashTable.add("C");
        assertTrue(hashTable.contains("A"));
        assertFalse(hashTable.contains("D"));
        hashTable.remove("A");
        assertFalse(hashTable.contains("A"));
    }

    @Test
    public void getConflictNumberTest() throws NoSuchElementException {
        HashTable hashTable = new HashTable(322, new LengthHash());
        hashTable.add("A");
        hashTable.add("B");
        assertEquals(1, hashTable.getConflictNumber());
        hashTable.remove("A");
        assertEquals(0, hashTable.getConflictNumber());
        hashTable.add("D");
        assertEquals(1, hashTable.getConflictNumber());
    }

    @Test
    public void getElementNumberTest() throws NoSuchElementException {
        HashTable hashTable = new HashTable(322, new LengthHash());
        hashTable.add("A");
        hashTable.add("B");
        hashTable.add("C");
        hashTable.add("D");
        assertEquals(4, hashTable.getElementNumber());
        hashTable.remove("A");
        assertEquals(3, hashTable.getElementNumber());
        hashTable.add("D");
        assertEquals(4, hashTable.getElementNumber());
    }

    @Test
    public void getMaxListSizeTest() throws NoSuchElementException {
        HashTable hashTable = new HashTable(322, new LengthHash());
        hashTable.add("A");
        hashTable.add("B");
        hashTable.add("C");
        assertEquals(3, hashTable.getMaxListSize());
        hashTable.remove("C");
        assertEquals(2, hashTable.getMaxListSize());
        hashTable.add("D");
        assertEquals(3, hashTable.getMaxListSize());
    }
}