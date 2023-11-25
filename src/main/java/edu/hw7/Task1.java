package edu.hw7;

import java.util.concurrent.atomic.AtomicInteger;
import lombok.Getter;

@Getter
public class Task1 {
    private final AtomicInteger counter = new AtomicInteger(0);

    public void increasingCounterByThreads(int n) {
        var incrementor1 = new Thread(() -> {
            for (int i = 0; i < n; i++) {
                counter.incrementAndGet();
            }
        });

        var incrementor2 = new Thread(() -> {
            for (int i = 0; i < n; i++) {
                counter.incrementAndGet();
            }
        });

        var incrementor3 = new Thread(() -> {
            for (int i = 0; i < n; i++) {
                counter.incrementAndGet();
            }
        });

        incrementor1.start();
        incrementor2.start();
        incrementor3.start();

        try {
            incrementor1.join();
            incrementor2.join();
            incrementor3.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
