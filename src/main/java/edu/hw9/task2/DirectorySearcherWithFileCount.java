package edu.hw9.task2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class DirectorySearcherWithFileCount extends RecursiveTask<List<File>> {
    private final File directory;
    private final int fileCount;
    private int attachedFilesCount;

    public DirectorySearcherWithFileCount(File directory, int fileCount) {
        this.directory = directory;
        this.fileCount = fileCount;
        attachedFilesCount = 0;
    }

    @Override
    protected List<File> compute() {
        List<File> result = new ArrayList<>();

        File[] files = directory.listFiles();
        if (files != null) {
            List<DirectorySearcherWithFileCount> subTasks = new ArrayList<>();

            for (File file : files) {
                if (file.isDirectory()) {
                    DirectorySearcherWithFileCount subTask = new DirectorySearcherWithFileCount(file, fileCount);
                    subTasks.add(subTask);
                    subTask.fork();
                } else {
                    attachedFilesCount++;
                }
            }

            for (DirectorySearcherWithFileCount subTask : subTasks) {
                List<File> file = subTask.join();
                attachedFilesCount += subTask.attachedFilesCount;
                result.addAll(file);
            }
        }
        if (attachedFilesCount > fileCount) {
            result.add(directory);
        }
        return result;
    }
}

