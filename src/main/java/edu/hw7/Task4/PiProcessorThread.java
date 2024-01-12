package edu.hw7.Task4;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class PiProcessorThread extends Thread implements Runnable {
    private static final Coordinate CIRCLE_CENTER = new Coordinate(0.5, 0.5);
    private static final ThreadLocalRandom RANDOM = ThreadLocalRandom.current();
    private final long startThread;
    private final long end;
    private final AtomicInteger circleCount;
    private final AtomicInteger totalCount;

    PiProcessorThread(long start, long end, AtomicInteger circleCount, AtomicInteger totalCount) {
        this.startThread = start;
        this.end = end;
        this.circleCount = circleCount;
        this.totalCount = totalCount;
    }

    @Override
    public void run() {
        int localCircleCount = 0;
        int localTotalCount = 0;
        for (long j = startThread; j < end; j++) {
            Coordinate point = generatePointInSquare();

            if (isInCircle(point)) {
                localCircleCount++;
            }
            localTotalCount++;
        }

        circleCount.addAndGet(localCircleCount);
        totalCount.addAndGet(localTotalCount);
    }

    private boolean isInCircle(Coordinate coordinate) {
        return coordinate.calculateDistance(CIRCLE_CENTER) <= CIRCLE_CENTER.x;
    }

    private Coordinate generatePointInSquare() {
        return new Coordinate(RANDOM.nextDouble(), RANDOM.nextDouble());
    }

}
