package edu.hw6.task2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public final class Task2 {
    private Task2() {
    }

    public static void cloneFile(Path path) throws IOException {
        if (!path.toFile().exists()) {
            throw new IOException("File does not exist");
        }

        int version = 1;
        String pathAsString = path.toString();
        Path newPath;
        String separator = File.separator;
        do {
            newPath =
                Path.of(pathAsString.substring(pathAsString.lastIndexOf(separator) + 1, pathAsString.lastIndexOf("."))
                    + String.format(" (%d)", version) + pathAsString.substring(pathAsString.lastIndexOf(".")));
            version++;
        } while (newPath.toFile().exists());

        Files.copy(path, newPath);
    }
}
