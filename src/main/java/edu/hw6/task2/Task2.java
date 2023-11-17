package edu.hw6.task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

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
        do {
            newPath = Path.of(pathAsString.substring(pathAsString.lastIndexOf("/") + 1, pathAsString.lastIndexOf("."))
                + String.format(" (%d)", version) + pathAsString.substring(pathAsString.lastIndexOf(".")));
            version++;
        } while (newPath.toFile().exists());

        try {
            Files.copy(path, newPath);
        } catch (Exception e) {
            System.out.println("File not created");
        }

    }
}
