package edu.hw10.task2;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CacheHandler implements InvocationHandler {
    private final Map<String, Object> cache = new HashMap<>();
    private final Object target;
    private final Path path;

    public CacheHandler(Object target, Path path) {
        this.target = target;
        this.path = path;
        retrieveResults();
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Cache cacheAnnotation = method.getAnnotation(Cache.class);
        if (cacheAnnotation != null) {
            String key = method.getName() + Arrays.toString(args);
            if (cache.containsKey(key)) {
                return cache.get(key);
            }
            Object result = method.invoke(target, args);
            cache.put(key, result);

            if (cacheAnnotation.persist()) {
                persistResult(key, result);
            }
            return result;
        } else {
            return method.invoke(target, args);
        }
    }

    private void persistResult(String key, Object result) {
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(path.resolve(key)))) {
            oos.writeObject(result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Object readFromFile(Path file) {
        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(file))) {
            return ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void retrieveResults() {
        try {
            Files.createDirectories(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        File[] files = path.toFile().listFiles();
        if (files == null) {
            return;
        }
        for (File file : files) {
            if (file.isFile()) {
                cache.put(file.getName(), readFromFile(file.toPath()));
            }
        }
    }
}
