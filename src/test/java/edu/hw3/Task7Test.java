package edu.hw3;

import java.util.TreeMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task7Test {
    TreeMap<String, String> treeMap;

    @BeforeEach
    void setUp() {
        treeMap = new TreeMap<>(new Task7<>());
    }

    @Test
    public void testContainsNullKey() {
        treeMap.put(null, "test");

        assertTrue(treeMap.containsKey(null));
    }

    @Test
    public void testContainsSort() {
        treeMap.put(null, "test");
        treeMap.put("a", "b");
        treeMap.put("c", "d");

        String[] actualOrder = treeMap.keySet().toArray(new String[0]);

        String[] expectedOrder = {null, "a", "c"};

        assertArrayEquals(expectedOrder, actualOrder);
    }

}
