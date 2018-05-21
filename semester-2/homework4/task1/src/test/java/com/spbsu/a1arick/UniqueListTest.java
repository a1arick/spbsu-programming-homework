package com.spbsu.a1arick;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UniqueListTest {

    private UniqueList<String> list;

    @Before
    public void setUp() {
        list = new UniqueList<>();
    }

    @Test
    public void testContains() {
        assertFalse(list.contains("D"));
    }

    @Test
    public void testAdd() throws Exception {
        list.add("A");
        list.add("B");
        list.add("C");
        assertTrue(list.contains("A"));
        assertTrue(list.contains("B"));
        assertTrue(list.contains("C"));
    }

    @Test(expected = ElementAlreadyExistsException.class)
    public void testElementAlreadyExistsException() throws Exception {
        list.add("A");
        list.add("A");
    }

    @Test(expected = ElementNotFoundException.class)
    public void testEmptyListElementNotFoundException() throws Exception {
        list.remove("ololol");
    }

    @Test(expected = ElementNotFoundException.class)
    public void testListElementNotFoundException() throws Exception {
        list.add("A");
        list.add("B");
        list.add("C");
        list.remove("ololol");
    }

    @Test
    public void testRemove() throws Exception {
        list.add("A");
        list.add("B");
        list.add("C");
        list.remove("B");
        assertTrue(list.contains("A"));
        assertFalse(list.contains("B"));
        assertTrue(list.contains("C"));
    }
}