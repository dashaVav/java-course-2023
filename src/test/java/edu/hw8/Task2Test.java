package edu.hw8;

import edu.hw8.task2.FixedThreadPool;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task2Test {

    @Test
    void calculateFibonacci() {
        int countOfNumbers = 10;
        long[] fibonacci = {0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55};

        try (FixedThreadPool threadPool = FixedThreadPool.create(4)) {
            threadPool.start();

            for (int i = 0; i <= countOfNumbers; i++) {
                int finalI = i;
                threadPool.execute(() -> {
                    long result = calculateFibonacci(finalI);
                    System.out.println(result);
                    assertEquals(result, fibonacci[finalI]);
                });
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private int calculateFibonacci(int n) {
        if (n <= 1) {
            return n;
        } else {
            return calculateFibonacci(n - 1) + calculateFibonacci(n - 2);
        }
    }
}
