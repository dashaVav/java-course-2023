package edu.hw8.task2;

public interface TreadPool extends AutoCloseable {
    void start();

    void execute(Runnable runnable) throws InterruptedException;
}
