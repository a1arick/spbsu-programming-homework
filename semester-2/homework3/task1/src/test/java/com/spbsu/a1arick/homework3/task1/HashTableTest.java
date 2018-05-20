package com.spbsu.a1arick.homework3.task1;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class HashTableTest {
    @Test
    public void addTest() {
        HashTable hashTable = new HashTable(37, new LengthHash());
        hashTable.add("A");
        hashTable.add("B");
        hashTable.add("C");
        assertEquals(3, hashTable.getElementNumber());
    }

    @Test
    public void deleteTest() throws TextNotFoundException {
        HashTable hashTable = new HashTable(37, new LengthHash());
        hashTable.add("A");
        hashTable.add("B");
        hashTable.add("C");
        hashTable.delete("B");
        assertFalse(hashTable.contains("B"));
        assertEquals(2, hashTable.getElementNumber());
    }

    @Test(expected = TextNotFoundException.class)
    public void deleteExceptionTest() throws TextNotFoundException {
        HashTable hashTable = new HashTable(37, new LengthHash());
        hashTable.add("A");
        hashTable.add("B");
        hashTable.add("C");
        hashTable.delete("E");
    }

    @Test
    public void containsTest() throws TextNotFoundException {
        HashTable hashTable = new HashTable(37, new LengthHash());
        hashTable.add("A");
        hashTable.add("B");
        hashTable.add("C");
        assertTrue(hashTable.contains("A"));
        assertTrue(hashTable.contains("B"));
        hashTable.delete("B");
        assertTrue(hashTable.contains("A"));
        assertTrue(hashTable.contains("C"));
        assertFalse(hashTable.contains("B"));
        hashTable.delete("A");
        assertFalse(hashTable.contains("A"));
    }

    @Test
    public void getElementNumberTest() throws TextNotFoundException {
        HashTable hashTable = new HashTable(37, new LengthHash());
        hashTable.add("A");
        hashTable.add("B");
        hashTable.add("C");
        assertEquals(3, hashTable.getElementNumber());
        hashTable.delete("A");
        assertEquals(2, hashTable.getElementNumber());
        hashTable.delete("C");
        assertEquals(1, hashTable.getElementNumber());
    }

    @Test
    public void getConflictNumberTest() throws TextNotFoundException {
        HashTable hashTable = new HashTable(37, new LengthHash());
        hashTable.add("AB");
        hashTable.add("BA");
        hashTable.add("AC");
        assertEquals(1, hashTable.getConflictNumber());
    }

    @Test
    public void getMaxListSizeTest() throws TextNotFoundException {
        HashTable hashTable = new HashTable(37, new LengthHash());
        hashTable.add("AB");
        hashTable.add("BA");
        hashTable.add("C");
        assertEquals(2, hashTable.getMaxListSize());
        hashTable.delete("AB");
        assertEquals(1, hashTable.getMaxListSize());
        hashTable.add("D");
        hashTable.add("D");
        assertEquals(3, hashTable.getMaxListSize());
    }
}
