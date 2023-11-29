package edu.hw6.task2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public final class Task2 {
    private Task2() {
    }

    private final static String DOT_BEFORE_FILE_EXTENSION = ".";

    public static void cloneFile(Path path) throws IOException {
        if (!path.toFile().exists()) {
            throw new IOException("File does not exist");
        }

        int version = 1;
        String pathAsString = path.toString();
        Path newPath;
        do {
            newPath = path.getParent().resolve(
                pathAsString.substring(
                    pathAsString.lastIndexOf(File.separator) + 1,
                    pathAsString.lastIndexOf(DOT_BEFORE_FILE_EXTENSION)
                ) + String.format(" (%d)", version)
                    + pathAsString.substring(pathAsString.lastIndexOf(DOT_BEFORE_FILE_EXTENSION)));
            version++;
        } while (Files.exists(newPath));

        Files.createFile(newPath);
        Files.copy(path, newPath, StandardCopyOption.REPLACE_EXISTING);
    }
}
