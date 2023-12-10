package edu.project4.BaseObjects;

public record Pixel(int r, int g, int b, int hitCount) {

    public double normal() {
        return Math.log10(hitCount);
    }

}
