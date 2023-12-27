package edu.hw10.task1;

import edu.hw10.task1.annotations.Max;
import edu.hw10.task1.annotations.Min;
import edu.hw10.task1.annotations.NotNull;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class RandomObjectGenerator {
    private static final int PROBABILITY_OF_NULL_FOR_STRING = 2;

    @SuppressWarnings("unchecked cast")
    public <T> T nextObject(Class<T> clazz) {
        var constructors = clazz.getDeclaredConstructors();

        if (constructors.length == 0) {
            throw new IllegalArgumentException("No accessible constructors found for class: " + clazz.getName());
        }

        try {
            Constructor<?> constructor = constructors[0];
            return (T) constructor.newInstance(args(constructor.getParameterTypes(), clazz.getDeclaredFields()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked cast")
    public <T> T nextObject(Class<T> clazz, String factoryMethodName) {
        Method[] methods = clazz.getMethods();

        Method factoryMethod =
            (Method) Arrays.stream(methods).filter(method -> method.getName().equals(factoryMethodName)).toArray()[0];

        try {
            return (T) factoryMethod.invoke(null, args(factoryMethod.getParameterTypes(), clazz.getDeclaredFields()));
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private Object[] args(Class<?>[] parameterTypes, Field[] fields) {
        return IntStream.range(0, parameterTypes.length)
            .mapToObj(index -> generateRandomValue(parameterTypes[index], fields[index]))
            .toArray();
    }

    private Object generateRandomValue(Class<?> type, Field field) {
        if (type.equals(int.class) || type.equals(Integer.class)) {
            return generateRandomIntValue(field);
        }

        if (type.equals(double.class) || type.equals(Double.class)) {
            return generateRandomDoubleValue(field);
        }

        if (type.equals(String.class)) {
            return generateRandomStringValue(field);
        }

        throw new RuntimeException("Illegal type in constructor or factory method");
    }

    private Object generateRandomIntValue(Field field) {
        Annotation[] annotations = field.getAnnotations();
        int max = Integer.MAX_VALUE;
        int min = Integer.MIN_VALUE;
        for (Annotation annotation : annotations) {
            if (annotation.annotationType() == Min.class) {
                min = ((Min) annotation).value();
            } else if (annotation.annotationType() == Max.class) {
                max = ((Max) annotation).value();
            }
        }
        return ThreadLocalRandom.current().nextInt(min, max);
    }

    private Object generateRandomDoubleValue(Field field) {
        Annotation[] annotations = field.getAnnotations();
        double max = Double.MAX_VALUE;
        double min = Double.MIN_VALUE;
        for (Annotation annotation : annotations) {
            if (annotation.annotationType() == Min.class) {
                min = ((Min) annotation).value();
            } else if (annotation.annotationType() == Max.class) {
                max = ((Max) annotation).value();
            }
        }
        return ThreadLocalRandom.current().nextDouble(min, max);
    }

    private Object generateRandomStringValue(Field field) {
        Annotation[] annotations = field.getAnnotations();
        boolean notNull = false;
        for (Annotation annotation : annotations) {
            if (annotation.annotationType() == NotNull.class) {
                notNull = true;
            }
        }

        if (!notNull && ThreadLocalRandom.current().nextInt() % PROBABILITY_OF_NULL_FOR_STRING == 0) {
            return null;
        }
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}

