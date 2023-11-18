package edu.hw7.Task4;

import lombok.Getter;

@Getter
public class Coordinate {
    double x;
    double y;

    public Coordinate(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double calculateDistance(Coordinate anotherCoordinate) {
        return StrictMath.sqrt(StrictMath.pow((anotherCoordinate.getX() - this.getX()), 2)
            + StrictMath.pow((anotherCoordinate.getY() - this.getY()), 2));
    }
}
