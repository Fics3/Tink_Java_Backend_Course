package edu.project4;

import edu.project4.BaseObjects.FractalImage;
import edu.project4.BaseObjects.Pixel;
import edu.project4.Transformations.AffineTransformation;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

final class GeneratorUtils {

    private static final double GAMMA = 1.2;

    private GeneratorUtils() {

    }

    public static List<AffineTransformation> generateAffines(int countAff, Random random) {
        List<AffineTransformation> affineTransformations = new ArrayList<>();
        for (int i = 0; i < countAff; i++) {
            affineTransformations.add(new AffineTransformation(random));
        }
        return affineTransformations;
    }

    public static void gammaCorrection(FractalImage fractalImage) {
        double max = -1;

        for (int i = 0; i < fractalImage.width(); i++) {
            for (int j = 0; j < fractalImage.height(); j++) {
                if (fractalImage.pixel(i, j) != null) {
                    if (fractalImage.pixel(i, j).normal() > max) {
                        max = fractalImage.pixel(i, j).normal();
                    }
                }
            }
        }
        for (int i = 0; i < fractalImage.width(); i++) {
            for (int j = 0; j < fractalImage.height(); j++) {
                Pixel pixel = fractalImage.pixel(i, j);
                if (pixel != null) {
                    double normalDivMax = pixel.normal() / max;
                    int red = (int) (pixel.r() * Math.pow(normalDivMax, (1.0 / GAMMA)));
                    int green = (int) (pixel.g() * Math.pow(normalDivMax, (1.0 / GAMMA)));
                    int blue = (int) (pixel.b() * Math.pow(normalDivMax, (1.0 / GAMMA)));

                    fractalImage.updatePixel(i, j, new Pixel(red, green, blue, 1));
                }
            }

        }

    }
}
