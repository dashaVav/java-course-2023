package edu.hw7;

import java.util.concurrent.CountDownLatch;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task1Test {
    @Test
    void testIncreasingCounterByThreads() throws InterruptedException {
        int n = 10;

        for (int i = 0; i < 10; i++) {
            Task1 task = new Task1();
            CountDownLatch latch = new CountDownLatch(1);
            new Thread(() -> {
                task.increasingCounterByThreads(n);
                latch.countDown();
            }).start();
            latch.await();
            assertEquals(task.getCounter().get(), 30);
        }
    }
}
