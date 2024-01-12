package edu.project4.Transformations;

import edu.project4.BaseObjects.Point;

public class SphericTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double sSumYPow = (Math.pow(point.x(), 2) + Math.pow(point.y(), 2));
        double newX = point.x() / sSumYPow;
        double newY = point.y() / sSumYPow;
        return new Point(newX, newY);

    }
}
