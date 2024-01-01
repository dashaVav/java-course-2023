package edu.hw9.task2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DirectorySearcherWithFileCountTest {
    private Path tempDirectory;

    @BeforeEach
    public void setUp() throws IOException {
        tempDirectory = Files.createTempDirectory("test");
        createFiles(tempDirectory, 5);
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
    public void testDirectorySearcherWithFileCount() {
        try (ForkJoinPool forkJoinPool = new ForkJoinPool()) {
            DirectorySearcherWithFileCount findDirectoriesTask =
                new DirectorySearcherWithFileCount(tempDirectory.toFile(), 2);
            List<File> directoriesWithMoreThan2Files = forkJoinPool.invoke(findDirectoriesTask);

            assertFalse(directoriesWithMoreThan2Files.isEmpty());
            assertEquals(tempDirectory.toFile(), directoriesWithMoreThan2Files.get(0));
        }
    }

    private void createFiles(Path directory, int count) throws IOException {
        for (int i = 0; i < count; i++) {
            Files.createFile(directory.resolve("file" + i + ".txt"));
        }
    }

    @Test
    public void testDirectorySearcherWithNoMatchingDirectories() throws IOException {
        try (ForkJoinPool forkJoinPool = new ForkJoinPool()) {
            Path emptyDirectory = Files.createTempDirectory("emptyDir");
            DirectorySearcherWithFileCount findDirectoriesTask =
                new DirectorySearcherWithFileCount(emptyDirectory.toFile(), 1);
            List<File> directoriesWithMoreThan1File = forkJoinPool.invoke(findDirectoriesTask);
            assertTrue(directoriesWithMoreThan1File.isEmpty());
            Files.deleteIfExists(emptyDirectory);
        }
    }

    @Test
    public void testDirectorySearcherWithMultipleFilesInSubdirectories() throws IOException {
        Path dir1 = Path.of("src/main/resources/dir/dir1");
        Files.createDirectories(dir1);
        Path dir2 = Path.of("src/main/resources/dir/dir2");
        Files.createDirectories(dir2);

        Path path1 = Path.of("src/main/resources/dir/dir1/text.txt");
        Files.createFile(path1);
        Path path2 = Path.of("src/main/resources/dir/dir2/text.txt");
        Files.createFile(path2);

        try (ForkJoinPool forkJoinPool = new ForkJoinPool()) {
            DirectorySearcherWithFileCount findDirectoriesTask =
                new DirectorySearcherWithFileCount(new File("src/main/resources/dir"), 1);
            List<File> directoriesWithMoreThan2Files = forkJoinPool.invoke(findDirectoriesTask);

            assertFalse(directoriesWithMoreThan2Files.isEmpty());

            assertEquals(1, directoriesWithMoreThan2Files.size());
            assertEquals("dir", directoriesWithMoreThan2Files.get(0).getName());
        }
        Files.deleteIfExists(path1);
        Files.deleteIfExists(path2);
        Files.deleteIfExists(dir1);
        Files.deleteIfExists(dir2);
        Files.deleteIfExists(Path.of("src/main/resources/dir"));
    }

}
