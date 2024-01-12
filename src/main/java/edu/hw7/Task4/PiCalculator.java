package edu.hw7.Task4;

import java.util.Random;

public class PiCalculator {

    private static final Coordinate CIRCLE_CENTER = new Coordinate(0.5, 0.5);
    private static final int FORMULA_FOUR = 4;
    private final Random random = new Random();
    private double totalCount = 0;
    private double circleCount = 0;

    public double calculatePi(long n) {
        for (int i = 0; i < n; i++) {
            Coordinate point = generatePointInSquare();

            if (isInCircle(point)) {
                circleCount++;
            }
            totalCount++;
        }
        return FORMULA_FOUR * (circleCount / totalCount);
    }

    private boolean isInCircle(Coordinate coordinate) {
        return coordinate.calculateDistance(CIRCLE_CENTER) <= CIRCLE_CENTER.x;
    }

    private Coordinate generatePointInSquare() {
        return new Coordinate(random.nextDouble(), random.nextDouble());
    }

}
