package edu.project4.Transformations;

import edu.project4.BaseObjects.Point;

public class HeartTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double xSumYSqrt = Math.hypot(point.x(), point.y());
        double newX = xSumYSqrt * Math.sin(xSumYSqrt * Math.atan(point.y()) / point.x());
        double newY = xSumYSqrt * Math.cos(xSumYSqrt * Math.atan(point.y()) / point.x());
        return new Point(newX, -newY);
    }
}
