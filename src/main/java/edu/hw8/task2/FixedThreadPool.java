package edu.hw8.task2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public final class FixedThreadPool implements TreadPool {
    private final Thread[] threads;
    private final int threadCount;
    private final BlockingQueue<Runnable> taskQueue;
    private final AtomicInteger numberOfTasks = new AtomicInteger(0);
    private final AtomicInteger numberOfCompletedTasks = new AtomicInteger(0);
    private final CountDownLatch latch = new CountDownLatch(1);

    private FixedThreadPool(int threadCount) {
        this.threadCount = threadCount;
        threads = new Thread[threadCount];
        taskQueue = new LinkedBlockingQueue<>();
    }

    public static FixedThreadPool create(int threadCount) {
        return new FixedThreadPool(threadCount);
    }

    @Override
    public void start() {
        for (int i = 0; i < threadCount; i++) {
            threads[i] = new Thread(() -> {
                try {
                    while (true) {
                        Runnable task = taskQueue.take();
                        Runnable combinedTask = () -> {
                            task.run();
                            numberOfCompletedTasks.incrementAndGet();
                            if (numberOfCompletedTasks.get() == numberOfTasks.get()) {
                                latch.countDown();
                            }
                        };
                        combinedTask.run();
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
            threads[i].start();
        }
    }

    @Override
    public void execute(Runnable runnable) {
        taskQueue.add(runnable);
        numberOfTasks.incrementAndGet();
    }

    @Override
    public void close() throws InterruptedException {
        latch.await();
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }
}

