package edu.hw2;

final class Task2 {
    private Task2() {

    }

    public static class Rectangle {
        private int width;
        private int height;

        Rectangle() {

        }

        Rectangle(int height, int width) {
            this.height = height;
            this.width = width;
        }

        Rectangle setWidth(int width) {
            this.width = width;
            return this;
        }

        Rectangle setHeight(int height) {
            this.height = height;
            return this;
        }

        double area() {
            return width * height;
        }

    }

    public static class Square extends Rectangle {

        Square(int side) {
            super(side, side);
        }

        Square() {

        }

        @Override final Rectangle setWidth(int width) {
            return new Rectangle(width, width);
        }

        @Override final Rectangle setHeight(int height) {
            return new Rectangle(height, height);
        }

        @Override
        double area() {
            return super.area();
        }
    }

}
