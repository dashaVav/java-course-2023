package edu.hw6.task3;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AbstractFilterTest {
    private static final Path TEST_DIRECTORY = Path.of("src/main/resources/hw6/task3");
    private static final Path TEST_FILE_PATH = TEST_DIRECTORY.resolve("test_file.txt");

    @BeforeEach
    void setUp() throws IOException {
        Files.createDirectories(TEST_FILE_PATH.getParent());
        Files.createFile(TEST_FILE_PATH);

        Files.write(TEST_FILE_PATH, "Test data".getBytes(), StandardOpenOption.WRITE);
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
    void testRegularFile() {
        AbstractFilter filter = AbstractFilter.regularFile;

        assertTrue(filter.accept(TEST_FILE_PATH));

        assertFalse(filter.accept(TEST_DIRECTORY));
    }

    @Test
    void testReadable() {
        AbstractFilter filter = AbstractFilter.readable;
        assertTrue(filter.accept(TEST_FILE_PATH));
    }

    @Test
    void testAnd() throws IOException {
        DirectoryStream.Filter<Path> filter = AbstractFilter.regularFile
            .and(AbstractFilter.readable)
            .and(AbstractFilter.smallerThan(100_000))
            .and(AbstractFilter.globMatches("*.txt"))
            .and(AbstractFilter.regexContains("[.]"));

        List<Path> paths = new ArrayList<>();
        try (DirectoryStream<Path> entries = Files.newDirectoryStream(TEST_DIRECTORY, filter)) {
            entries.forEach(paths::add);
        }

        assertEquals(paths.size(), 1);
        assertEquals(paths.get(0), TEST_FILE_PATH);
    }

    @Test
    void testSizeEqualsTo() throws IOException {
        AbstractFilter filter = AbstractFilter.sizeEqualsTo(9);

        assertTrue(filter.accept(TEST_FILE_PATH));

        Path filePathWithSize10 = TEST_DIRECTORY.resolve("file_size_10.txt");
        Files.createFile(filePathWithSize10);
        Files.write(filePathWithSize10, "Test data!".getBytes(), StandardOpenOption.WRITE);
        assertFalse(filter.accept(filePathWithSize10));
    }

    @Test
    void testGlobMatches() {
        AbstractFilter filter = AbstractFilter.globMatches("*.txt");

        assertTrue(filter.accept(TEST_FILE_PATH));

        Path nonMatchingFilePath = TEST_DIRECTORY.resolve("example.png");
        assertFalse(filter.accept(nonMatchingFilePath));
    }

    @Test
    void testRegexContains() {
        AbstractFilter filter = AbstractFilter.regexContains(".*test.*");

        assertTrue(filter.accept(TEST_FILE_PATH));

        Path nonMatchingContentFilePath = TEST_DIRECTORY.resolve("no_data.txt");
        assertFalse(filter.accept(nonMatchingContentFilePath));
    }

    @Test
    void testMagicNumber() throws IOException {
        AbstractFilter filter = AbstractFilter.magicNumber((byte) 'T', (byte) 'e', (byte) 's', (byte) 't');

        assertTrue(filter.accept(TEST_FILE_PATH));

        Path nonMatchingMagicNumberFilePath = TEST_DIRECTORY.resolve("different_magic.txt");
        Files.createFile(nonMatchingMagicNumberFilePath);
        Files.write(nonMatchingMagicNumberFilePath, "!Test data!".getBytes(), StandardOpenOption.WRITE);
        assertFalse(filter.accept(nonMatchingMagicNumberFilePath));
    }
}
