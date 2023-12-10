package edu.project4.BaseObjects;

public record Point(double x, double y) {

    public Point rotate(double angle) {
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);
        double newX = x * cos - y * sin;
        double newY = x * sin + y * cos;
        return new Point(newX, newY);
    }
}
