package edu.hw6.task1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DiskMapTest {
    private DiskMap diskMap;
    private Path directory;

    @BeforeEach
    void setUp() {
        directory = Path.of("src/main/resources/hw6/task1");
        diskMap = new DiskMap();
        diskMap.put("key1", "value1");
        diskMap.put("key2", "value2");
    }

    @AfterEach
    void tearDown() {
        diskMap.clear();
    }

    @Test
    void testSize() {
        File[] files = directory.toFile().listFiles();

        assert files != null;
        assertEquals(diskMap.size(), files.length, 2);
    }

    @Test
    void testIsEmptyTrue() {
        diskMap.clear();

        File[] files = directory.toFile().listFiles();

        assertTrue(diskMap.isEmpty());
        assert files != null;
        assertEquals(files.length, 0);
    }

    @Test
    void testIsEmptyFalse() {
        File[] files = directory.toFile().listFiles();

        assertFalse(diskMap.isEmpty());
        assert files != null;
        assertEquals(files.length, 2);
    }

    @Test
    void testContainsKey() {
        String key = "key3";
        String value = "value3";
        diskMap.put(key, value);

        boolean result = diskMap.containsKey(key);

        assertTrue(directory.resolve(key).toFile().exists());
        assertTrue(result);
    }

    @Test
    void testNotContainsKey() {
        String key = "key4";

        boolean result = diskMap.containsKey(key);

        assertFalse(directory.resolve(key).toFile().exists());
        assertFalse(result);
    }

    @Test
    void testContainsValue() {
        String key = "key3";
        String value = "value3";
        diskMap.put(key, value);

        boolean result = diskMap.containsValue(value);

        assertTrue(result);
        assertEquals(value, readFromFile(directory.resolve(key)));
    }

    @Test
    void testGetAndPut() {
        String key = "key3";
        String value = "value3";
        diskMap.put(key, value);

        String result = diskMap.get(key);

        assertEquals(result, value);
        assertEquals(value, readFromFile(directory.resolve(key)));
    }

    @Test
    void testPutAll() {
        Map<String, String> data = new HashMap<>();
        data.put("key3", "value3");
        data.put("key4", "value4");

        diskMap.putAll(data);

        assertEquals("value3", readFromFile(directory.resolve("key3")));
        assertEquals("value4", readFromFile(directory.resolve("key4")));
    }

    @Test
    void testRemove() {
        String key = "key2";
        String value = "value2";

        diskMap.remove(key, value);

        assertFalse(directory.resolve(key).toFile().exists());
    }

    @Test
    void loadDataFromFileOnRecreation() {
        DiskMap recreatedDiskMap = new DiskMap();

        assertEquals("value1", recreatedDiskMap.get("key1"));
        assertEquals("value2", recreatedDiskMap.get("key2"));
    }

    private String readFromFile(Path filePath) {
        try {
            return Files.readString(filePath);
        } catch (IOException e) {
            return null;
        }
    }
}
