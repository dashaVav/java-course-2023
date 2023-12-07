package edu.hw7.task4;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLong;

public final class Task4MultiThread {
    private Task4MultiThread() {
    }

    private static final int WIDTH = 1000;
    private static final double PROBABILITY_OF_POINT_HITTING_CIRCLE = 4.0;

    public static double calculationOfPiUsingMonteCarloMethod(int numberOfThreads, int simulations) {
        AtomicLong totalCount = new AtomicLong();
        AtomicLong circleCount = new AtomicLong();

        Thread[] threads = new Thread[numberOfThreads];

        int simulationsPerThread = simulations / numberOfThreads;

        for (int i = 0; i < numberOfThreads; i++) {
            threads[i] = new Thread(() -> {
                long localCircleCount = 0;

                for (int j = 0; j < simulationsPerThread; j++) {
                    double x = ThreadLocalRandom.current().nextDouble(-WIDTH / 2.0, WIDTH / 2.0);
                    double y = ThreadLocalRandom.current().nextDouble(-WIDTH / 2.0, WIDTH / 2.0);

                    if (x * x + y * y <= (WIDTH / 2.0) * (WIDTH / 2.0)) {
                        localCircleCount++;
                    }
                }
                circleCount.addAndGet(localCircleCount);
                totalCount.addAndGet(simulationsPerThread);
            });
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

        return PROBABILITY_OF_POINT_HITTING_CIRCLE * circleCount.get() / totalCount.get();
    }
}
