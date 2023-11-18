package edu.hw7.Task4;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class PiCalculatorMultiThread {

    private static final int FORMULA_FOUR = 4;
    private final AtomicInteger circleCount = new AtomicInteger(0);
    private final AtomicInteger totalCount = new AtomicInteger(0);

    public double calculatePi(long n, int threadCount) {
        long step = n / threadCount;
        ArrayList<PiProcessorThread> threads = new ArrayList<>();
        for (int i = 1; i <= threadCount; i++) {
            long end = step * i;
            long start = end - step;
            threads.add(new PiProcessorThread(start, end, circleCount, totalCount));
            threads.get(i - 1).start();
        }

        for (PiProcessorThread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return FORMULA_FOUR * (circleCount.doubleValue() / totalCount.doubleValue());
    }

}
