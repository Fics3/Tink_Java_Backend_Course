package edu.project4.Transformations;

import edu.project4.BaseObjects.Point;

public class PolarTransformation implements Transformation {

    @Override
    public Point apply(Point point) {
        double newX = (Math.atan(point.y() / point.x())) / Math.PI;
        double newY = Math.hypot(point.x(), point.y()) - 1;
        return new Point(newX, newY);
    }
}
