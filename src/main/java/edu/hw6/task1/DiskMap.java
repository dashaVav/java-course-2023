package edu.hw6.task1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("ResultOfMethodCallIgnored")
public class DiskMap implements Map<String, String> {
    private final Map<String, String> memoryMap;
    private File directory;

    public DiskMap() {
        memoryMap = new HashMap<>();
        downloadKeyValuesFromFile();
    }

    private void createDirIfDirDoesNotExist() {
        directory = new File("src/main/resources/hw6/task1");

        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    private void downloadKeyValuesFromFile() {
        createDirIfDirDoesNotExist();
        File[] files = directory.listFiles();
        if (files == null) {
            return;
        }

        for (File file : files) {
            if (file.isFile()) {
                memoryMap.put(file.getName(), readFromFile(file));
            }
        }
    }

    private String readFromFile(File file) {
        String value;
        try (var reader = new BufferedReader(new FileReader(file))) {
            value = reader.lines().collect(Collectors.joining(""));
            return value;
        } catch (IOException e) {
            return "";
        }
    }

    @Override
    public int size() {
        return memoryMap.size();
    }

    @Override
    public boolean isEmpty() {
        return memoryMap.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return memoryMap.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return memoryMap.containsValue(value);
    }

    @Override
    public String get(Object key) {
        return memoryMap.get(key);
    }

    @Nullable
    @Override
    public String put(String key, String value) {
        putInFile(key, value);
        return memoryMap.put(key, value);
    }

    private void putInFile(String key, String value) {
        try {
            File file = new File(directory, key);
            Files.writeString(file.toPath(), value);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String remove(Object key) {
        removeFile(key);
        return memoryMap.remove(key);
    }

    private void removeFile(Object key) {
        File file = new File(directory, key.toString());
        if (!file.delete()) {
            throw new RuntimeException("There is no such key");
        }
    }

    @Override
    public void putAll(@NotNull Map<? extends String, ? extends String> m) {
        putAllInFile(m);
        memoryMap.putAll(m);
    }

    private void putAllInFile(@NotNull Map<? extends String, ? extends String> m) {
        for (Map.Entry<? extends String, ? extends String> entry : m.entrySet()) {
            putInFile(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void clear() {
        deleteAllFiles();
        memoryMap.clear();
    }

    private void deleteAllFiles() {
        if (directory.listFiles() == null) {
            return;
        }
        for (File file : Objects.requireNonNull(directory.listFiles())) {
            if (!file.isDirectory()) {
                file.delete();
            }
        }
    }

    @NotNull
    @Override
    public Set<String> keySet() {
        return memoryMap.keySet();
    }

    @NotNull
    @Override
    public Collection<String> values() {
        return memoryMap.values();
    }

    @NotNull
    @Override
    public Set<Entry<String, String>> entrySet() {
        return memoryMap.entrySet();
    }
}
