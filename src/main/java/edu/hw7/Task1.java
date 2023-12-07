package edu.hw7;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.Getter;

@Getter
public class Task1 {
    private final AtomicInteger counter = new AtomicInteger(0);

    private static final int NUMBER_OF_THREADS = 3;

    public void increasingCounterByThreads(int n) {
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_THREADS; i++) {
            threads.add(new Thread(() -> {
                for (int j = 0; j < n; j++) {
                    counter.incrementAndGet();
                }
            }));
        }

        for (Thread thread : threads) {
            thread.start();
        }

        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
