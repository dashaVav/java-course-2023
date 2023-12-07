package edu.hw7;

import java.util.stream.LongStream;

public final class Task2 {
    private Task2() {
    }

    public static long factorialOfNumber(long n) {
        return LongStream.rangeClosed(1, n)
            .parallel().reduce(1, (a, b) -> a * b);

    }
}
