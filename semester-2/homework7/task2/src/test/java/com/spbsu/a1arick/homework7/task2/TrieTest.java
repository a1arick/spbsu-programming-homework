package com.spbsu.a1arick.homework7.task2;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static org.junit.Assert.*;

public class TrieTest {

    private Trie root;

    @Before
    public void setUp() {
        root = new Trie();
    }

    @Test
    public void add() {
        assertTrue(root.add("abc"));
        assertFalse(root.add("abc"));
        assertTrue(root.add("ab"));
        assertTrue(root.add("abd"));
        assertTrue(root.add("a"));
        assertFalse(root.add("a"));
        assertTrue(root.add("b"));
        assertFalse(root.add("a"));
        assertTrue(root.add(""));
        assertFalse(root.add(""));
    }

    @Test
    public void contains() {
        root.add("a");
        root.add("abc");
        assertTrue(root.contains("abc"));
        assertFalse(root.contains("abd"));
        assertTrue(root.contains("a"));
        root.add("abd");
        assertTrue(root.contains("abd"));
        assertFalse(root.contains(""));
    }

    @Test
    public void size() {
        addAllToRoot("abc", "a");
        assertEquals(2, root.size());
        root.add("abc");
        assertEquals(2, root.size());
        root.add("");
        assertEquals(3, root.size());
        root.add("");
        assertEquals(3, root.size());
        root.remove("");
        assertEquals(2, root.size());
        root.remove("");
        assertEquals(2, root.size());
        root.remove("a");
        root.remove("abc");
        assertEquals(0, root.size());
    }

    @Test
    public void remove() {
        assertFalse(root.remove(""));
        addAllToRoot("abc", "abe", "ab", "", "acd", "bf", "ba", "ba");
        assertTrue(root.remove(""));
        assertFalse(root.remove("a"));
        assertTrue(root.contains("abc"));
        assertTrue(root.remove("abc"));
        assertFalse(root.remove("abc"));
        assertFalse(root.remove("retgdfg"));
    }

    @Test
    public void howManyStartWithPrefix() {
        assertEquals(0, root.howManyStartWithPrefix(""));
        assertEquals(0, root.howManyStartWithPrefix("abc"));
        addAllToRoot("abc", "abe", "ab", "", "acd", "bf", "ba", "ba");
        assertEquals(7, root.howManyStartWithPrefix(""));
        assertEquals(4, root.howManyStartWithPrefix("a"));
        assertEquals(3, root.howManyStartWithPrefix("ab"));
        assertEquals(1, root.howManyStartWithPrefix("abc"));
        assertEquals(2, root.howManyStartWithPrefix("b"));
        assertEquals(1, root.howManyStartWithPrefix("ba"));
        assertEquals(1, root.howManyStartWithPrefix("abe"));
        assertEquals(0, root.howManyStartWithPrefix("abed"));
        assertEquals(0, root.howManyStartWithPrefix("fdsfdsfsdd"));
    }

    @Test
    public void serialization() throws IOException {
        addAllToRoot("abc", "abe", "ab", "", "acd", "bf", "ba", "ba");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        root.serialize(bos);
        byte[] serializedTrie = bos.toByteArray();
        ByteArrayInputStream bis = new ByteArrayInputStream(serializedTrie);
        Trie deserializeTrie = new Trie();
        deserializeTrie.deserialize(bis);
        assertEquals(root, deserializeTrie);
    }

    private void addAllToRoot(String... strings){
        for (String string : strings) {
            root.add(string);
        }
    }
}