package com.spbsu.a1arick.homework6.task1;

import com.google.common.collect.ImmutableMap;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import static com.spbsu.a1arick.homework6.task1.SecondPartTasks.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class SecondPartTasksTest {

    @Test
    public void testFindQuotes() throws IOException {
        List<File> files = Arrays.asList(new File("f1"), new File("f2"), new File("f3"));
        List<String> paths = files.stream().map(File::getPath).collect(Collectors.toList());
        for (File file : files) file.createNewFile();

        Files.write(Paths.get(files.get(0).getPath()), Arrays.asList("abc", "def", "ghi"));
        Files.write(Paths.get(files.get(1).getPath()), Arrays.asList("123", "426", "789"));
        Files.write(Paths.get(files.get(2).getPath()), Arrays.asList("abc", "322", "adg"));

        assertEquals(Arrays.asList("123", "426", "322"), findQuotes(paths, "2"));
        assertEquals(Arrays.asList("abc", "abc", "adg"), findQuotes(paths, "a"));
        assertEquals(Collections.emptyList(), findQuotes(paths, "sdfsdfsdfse"));

        for (File file : files) file.delete();
    }

    @Test
    public void testPiDividedBy4() {
        assertEquals(3.1415 / 4, piDividedBy4(), 0.001);
    }

    @Test
    public void testFindPrinter() {
        assertEquals("a2", findPrinter(ImmutableMap.of(
                "a1", Arrays.asList("aaaa", "bbbb"),
                "a2", Arrays.asList("a", "bbbbbbbbb"),
                "a3", Arrays.asList("dfsdfsd", ""))));

        assertEquals("a1", findPrinter(ImmutableMap.of(
                "a1", Arrays.asList("affefefs", "b"),
                "a2", Arrays.asList("a", "b1"),
                "a3", Arrays.asList("a", "a"))));

        try{
            findPrinter(Collections.emptyMap());
            fail();
        } catch (IllegalArgumentException e) {
            // ok
        }
    }

    @Test
    public void testCalculateGlobalOrder() {
        assertEquals(Collections.emptyMap(), calculateGlobalOrder(Collections.emptyList()));

        List<Map<String, Integer>> orders = Arrays.asList(
                ImmutableMap.of("A", 2, "B", 3, "C", 5),
                ImmutableMap.of("A", 3, "C", 5, "D", 100),
                ImmutableMap.of("B", 1, "C", 5, "D", 1),
                ImmutableMap.of("A", 2, "B", 3, "C", 5));

        assertEquals(ImmutableMap.of("A", 7, "B", 7, "C", 20, "D", 101),
                calculateGlobalOrder(orders));
    }
}
