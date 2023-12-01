package edu.hw8.task2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public final class FixedThreadPool implements TreadPool {
    private final Thread[] threads;
    private final int threadCount;
    private final BlockingQueue<Runnable> taskQueue;

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
                        task.run();
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
    }

    @Override
    public void close() {
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }
}

