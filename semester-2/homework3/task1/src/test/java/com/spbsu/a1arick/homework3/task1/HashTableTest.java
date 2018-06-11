package com.spbsu.a1arick.homework3.task1;

import com.spbsu.a1arick.homework3.task1.hash.impl.LengthHash;
import com.spbsu.a1arick.homework3.task1.hashtable.HashTable;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.*;
import static org.junit.Assert.assertFalse;

public class HashTableTest {

    private HashTable<String, Integer> hashTable;

    @Before
    public void setUp() {
        hashTable = new HashTable<>(37, new LengthHash());
    }

    @Test
    public void testAdd() {
        hashTable.add("A", 1);
        hashTable.add("B", 2);
        hashTable.add("C", 3);
        assertEquals(3, hashTable.size());
        assertEquals(2, hashTable.getConflictNumber());
    }

    @Test
    public void testDelete() {
        hashTable.add("A", 1);
        hashTable.add("B", 2);
        hashTable.add("C", 3);
        assertTrue(hashTable.delete("B"));
        assertFalse(hashTable.delete("B"));
        assertNull(hashTable.get("B"));
        assertEquals(2, hashTable.size());
    }

    @Test
    public void testGet() {
        hashTable.add("A", 1);
        hashTable.add("B", 2);
        hashTable.add("C", 3);
        assertEquals(Integer.valueOf(1), hashTable.get("A"));
        assertEquals(Integer.valueOf(2), hashTable.get("B"));
        assertNull(hashTable.get("D"));
    }

    @Test
    public void testConflictNumber() {
        hashTable.add("AB", 1);
        hashTable.add("BA", 2);
        hashTable.add("AC", 4);
        assertEquals(2, hashTable.getConflictNumber());
    }

    @Test
    public void testMaxListSize() {
        hashTable.add("AB", 4);
        hashTable.add("BA", 4);
        hashTable.add("C", 4);
        assertEquals(2, hashTable.getMaxListSize());
        hashTable.delete("AB");
        assertEquals(1, hashTable.getMaxListSize());
        hashTable.add("D", 4);
        hashTable.add("D", 5);
        hashTable.add("E", 5);
        assertEquals(3, hashTable.getMaxListSize());
    }
}
