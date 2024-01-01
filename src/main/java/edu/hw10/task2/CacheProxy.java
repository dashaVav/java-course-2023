package edu.hw10.task2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.nio.file.Path;

public final class CacheProxy {
    private CacheProxy() {
    }

    @SuppressWarnings("unchecked cast")
    public static <T> T create(T target, Class<T> interfaceClass, Path path) {
        InvocationHandler handler = new CacheHandler(target, path);
        return (T) Proxy.newProxyInstance(
            interfaceClass.getClassLoader(),
            new Class<?>[] {interfaceClass},
            handler
        );
    }
}

