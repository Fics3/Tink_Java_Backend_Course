package edu.project4.BaseObjects;

public record FractalImage(Pixel[] data, int width, int height) {
    public static FractalImage create(int width, int height) {
        Pixel[] pixels = new Pixel[width * height];
        return new FractalImage(pixels, width, height);
    }

    public boolean contains(int x, int y) {
        return x >= 0 && x < width() && y >= 0 && y < height();
    }

    public Pixel pixel(int x, int y) {
        return data[x + y * width()];
    }

    public void updatePixel(int x, int y, Pixel pixel) {
        data[x + y * width()] = pixel;
    }
}
