package edu.hw6.task2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task2Test {
    Path path;

    @BeforeEach
    void setUp() throws IOException {
        path = Path.of("src/main/resources/hw6/task2/file.txt");
        Files.createDirectories(path.getParent());
        Files.createFile(path);

        Files.writeString(path, "hello");
    }

    @AfterEach
    void tearDown() throws IOException {
        if (path.getParent().toFile().listFiles() == null) {
            return;
        }
        for (File file : Objects.requireNonNull(path.getParent().toFile().listFiles())) {
            if (!file.isDirectory()) {
                file.delete();
            }
        }
        Files.deleteIfExists(path.getParent());
    }

    @Test
    void testCloneFile() throws IOException {
        Path newPath = Path.of("src/main/resources/hw6/task2/file (1).txt");

        Task2.cloneFile(path);

        assertTrue(newPath.toFile().exists());
        assertEquals(readFromFile(path), readFromFile(newPath));
    }

    @Test
    void testExceptionFileDoesNotExist() {
        assertThrows(IOException.class, () -> Task2.cloneFile(Path.of("src/main/resources/hw6/task2/1.txt")));
    }

    private String readFromFile(Path filePath) {
        try {
            return Files.readString(filePath);
        } catch (IOException e) {
            return null;
        }
    }
}
