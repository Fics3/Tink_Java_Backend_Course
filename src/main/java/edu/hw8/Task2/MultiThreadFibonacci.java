package edu.hw8.Task2;

import java.util.HashMap;
import java.util.Map;

final class MultiThreadFibonacci {

    private static final Map<Integer, Integer> FIBONACCI_VALUES = new HashMap<>();

    private MultiThreadFibonacci() {

    }

    public static Map<Integer, Integer> countFibonacciParallel(int lastFibonacciValue, int threadsCount) {
        try (FixedThreadPool fixedThreadPool = new FixedThreadPool(threadsCount)) {
            for (int i = 0; i < lastFibonacciValue; i++) {
                final int n = i;
                fixedThreadPool.execute(() -> {
                    int result = countFibonacci(n);
                    FIBONACCI_VALUES.put(n, result);
                });
            }
            fixedThreadPool.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return FIBONACCI_VALUES;
    }

    private static int countFibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        return countFibonacci(n - 1) + countFibonacci(n - 2);
    }

}
