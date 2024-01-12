package edu.project4.BaseObjects;

public record Rectangle(double x, double y, double width, double height) {
    public boolean contains(Point point) {
        double xMin = getXMin();
        double xMax = getXMax();
        double yMin = getYMin();
        double yMax = getYMax();

        return xMin <= point.x() && xMax >= point.x()
            && yMax >= point.y() && yMin <= point.y();
    }

    public double getXMax() {
        return x + width / 2;
    }

    public double getXMin() {
        return x - width / 2;
    }

    public double getYMax() {
        return y + height / 2;
    }

    public double getYMin() {
        return y - height / 2;
    }

}
