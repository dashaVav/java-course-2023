package edu.hw6.task4;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OutputStreamCompositionTest {
    private static final Path TEST_DIRECTORY = Path.of("src/main/resources/hw6/task4");

    @BeforeEach
    void setUp() throws IOException {
        Files.createDirectories(TEST_DIRECTORY);
    }

    @AfterEach
    void tearDown() throws IOException {
        for (File file : Objects.requireNonNull(TEST_DIRECTORY.toFile().listFiles())) {
            if (!file.isDirectory()) {
                file.delete();
            }
        }

        Files.deleteIfExists(TEST_DIRECTORY);
    }

    @Test
    void testPrintWriterCustom() {
        String text = "hello";
        Path pathFile = TEST_DIRECTORY.resolve("test.txt");

        OutputStreamComposition.printWriterCustom(pathFile, text);

        assertEquals(text, readFromFile(pathFile));
    }

    private String readFromFile(Path filePath) {
        try {
            return Files.readString(filePath);
        } catch (IOException e) {
            return null;
        }
    }
}
