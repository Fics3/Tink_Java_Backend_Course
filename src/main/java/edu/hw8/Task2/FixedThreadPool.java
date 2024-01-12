package edu.hw8.Task2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class FixedThreadPool implements ThreadPool {

    private static final int MUL_PUL_SIZE = 4;
    private final Thread[] threads;
    private final BlockingQueue<Runnable> taskQueue;


    FixedThreadPool(int threadsCount) {
        threads = new Thread[threadsCount];
        taskQueue = new ArrayBlockingQueue<>(threadsCount * MUL_PUL_SIZE);
    }

    @Override
    public void start() {
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                while (true) {
                    try {
                        Runnable task = taskQueue.take();
                        task.run();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
            });
            threads[i].start();
        }
    }

    @Override
    public void execute(Runnable runnable) {
        try {
            taskQueue.put(runnable);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void close() throws Exception {
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }
}
