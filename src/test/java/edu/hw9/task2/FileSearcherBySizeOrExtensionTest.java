package edu.hw9.task2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileSearcherBySizeOrExtensionTest {
    private Path tempDirectory;

    @BeforeEach
    public void setUp() throws IOException {
        tempDirectory = Files.createTempDirectory("test");
        createFiles(tempDirectory, 3);
    }

    @AfterEach
    public void tearDown() throws IOException {
        Files.walk(tempDirectory)
            .sorted((a, b) -> -a.compareTo(b))
            .forEach(path -> {
                try {
                    Files.deleteIfExists(path);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    @Test
    public void testFileSearcherBySizeWithResult() {
        try (ForkJoinPool forkJoinPool = new ForkJoinPool()) {
            FileSearcherBySizeOrExtension findFilesBySizeTask =
                new FileSearcherBySizeOrExtension(tempDirectory.toFile(), 50);
            List<File> filesWithSizeGreaterThan50Bytes = forkJoinPool.invoke(findFilesBySizeTask);

            assertEquals(filesWithSizeGreaterThan50Bytes.size(), 3);

            for (File file : filesWithSizeGreaterThan50Bytes) {
                assertTrue(file.length() > 50);
            }
        }
    }

    @Test
    public void testFileSearcherByExtension() throws IOException {
        try (ForkJoinPool forkJoinPool = new ForkJoinPool()) {
            Files.createFile(tempDirectory.resolve("testfile.docx"));
            FileSearcherBySizeOrExtension findFilesByExtensionTask =
                new FileSearcherBySizeOrExtension(tempDirectory.toFile(), "docx");
            List<File> docxFile = forkJoinPool.invoke(findFilesByExtensionTask);
            assertEquals(1, docxFile.size());
            assertTrue(docxFile.get(0).getName().endsWith("docx"));
        }
    }

    @Test
    public void testFileSearcherBySizeAndExtension() throws IOException {
        try (ForkJoinPool forkJoinPool = new ForkJoinPool()) {

            Path largeTxtFile = Files.createFile(tempDirectory.resolve("largetestfile.txt"));
            Files.write(
                largeTxtFile,
                "This is a test file with more than 100 bytes.This is a test file with more than 100 bytes.This is a test file with more than 100 bytes.".getBytes(),
                StandardOpenOption.APPEND
            );

            FileSearcherBySizeOrExtension findFilesBySizeAndExtensionTask =
                new FileSearcherBySizeOrExtension(tempDirectory.toFile(), 100);
            List<File> largeTxtFiles = forkJoinPool.invoke(findFilesBySizeAndExtensionTask);
            assertEquals(1, largeTxtFiles.size());
            assertTrue(largeTxtFiles.get(0).getName().endsWith("txt"));
            assertTrue(largeTxtFiles.get(0).length() > 100);
        }
    }

    private void createFiles(Path directory, int count) throws IOException {
        for (int i = 0; i < count; i++) {
            Path path = directory.resolve("file" + i + ".txt");
            Files.createFile(path);
            Files.write(path, ("Test" + path).getBytes(), StandardOpenOption.APPEND);
        }
    }
}
