package edu.hw9.task2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class FileSearcherBySizeOrExtension extends RecursiveTask<List<File>> {
    private final File directory;
    private String extension = null;
    private long size = -1;

    public FileSearcherBySizeOrExtension(File directory, long size) {
        this.directory = directory;
        this.size = size;
    }

    public FileSearcherBySizeOrExtension(File directory, String extension) {
        this.directory = directory;
        this.extension = extension;
    }

    @Override
    protected List<File> compute() {
        List<File> result = new ArrayList<>();

        File[] files = directory.listFiles();
        if (files != null) {
            List<FileSearcherBySizeOrExtension> subTasks = new ArrayList<>();
            for (File file : files) {
                if (file.isDirectory()) {
                    FileSearcherBySizeOrExtension subTask;
                    if (extension == null) {
                        subTask = new FileSearcherBySizeOrExtension(file, size);
                    } else {
                        subTask = new FileSearcherBySizeOrExtension(file, extension);
                    }
                    subTasks.add(subTask);
                    subTask.fork();
                } else {
                    if (size != -1 && file.length() > size) {
                        result.add(file);
                    } else if (extension != null && file.getName().endsWith(extension)) {
                        result.add(file);
                    }
                }
            }
            for (FileSearcherBySizeOrExtension subTask : subTasks) {
                result.addAll(subTask.join());
            }
        }
        return result;
    }
}

