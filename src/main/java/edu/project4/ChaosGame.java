package edu.project4;

import edu.project4.BaseObjects.FractalImage;
import edu.project4.BaseObjects.Pixel;
import edu.project4.BaseObjects.Point;
import edu.project4.BaseObjects.Rectangle;
import edu.project4.Transformations.AffineTransformation;
import edu.project4.Transformations.Transformation;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import org.jetbrains.annotations.NotNull;
import static edu.project4.GeneratorUtils.gammaCorrection;
import static edu.project4.GeneratorUtils.generateAffines;

public class ChaosGame implements FractalGenerator {
    private final static int ITER_START = -20;
    private final FractalImage fractalImage;
    private final List<Transformation> transformations;
    private final Random random;

    public ChaosGame(FractalImage fractalImage, List<Transformation> transformations) {
        this.fractalImage = fractalImage;
        this.transformations = transformations;
        random = ThreadLocalRandom.current();
    }

    @Override
    public FractalImage render(int sample, Rectangle field, int iterPerSample, int eq, int symmerty) {
        List<AffineTransformation> affineTransformations = generateAffines(eq, random);
        for (int i = 0; i < sample; i++) {

            Point point = new Point(random.nextDouble(field.getXMin(), field.getXMax()), random.nextDouble(
                field.getYMin(),
                field.getYMax()
            ));

            for (int j = ITER_START; j < iterPerSample; j++) {
                AffineTransformation currentAffine = affineTransformations.get(random.nextInt(0, eq));
                Transformation transformation = transformations.get(random.nextInt(0, transformations.size()));

                point = currentAffine.getTransformation(point);
                point = transformation.apply(point);

                double theta = 0.0;
                Point rotated;
                for (int k = 0; k < symmerty; theta += Math.PI * 2 / symmerty, k++) {
                    rotated = point.rotate(theta);
                    if (field.contains(rotated) && j >= 0) {
                        setPixelOnImage(rotated, field, currentAffine);
                    }
                }
            }
        }
        gammaCorrection(fractalImage);
        return fractalImage;
    }

    private void setPixelOnImage(Point rotated, Rectangle field, AffineTransformation currentAffine) {
        double relativeX = (field.getXMax() - rotated.x()) / (field.getXMax() - field.getXMin());
        double relativeY = (field.getYMax() - rotated.y()) / (field.getYMax() - field.getYMin());

        int x = (int) (fractalImage.width() - Math.floor(relativeX * fractalImage.width()));
        int y = (int) (fractalImage.height() - Math.floor(relativeY * fractalImage.height()));

        if (fractalImage.contains(x, y)) {
            Pixel newPixel = getPixel(x, y, currentAffine);
            fractalImage.updatePixel(x, y, newPixel);
        }
    }

    @NotNull
    private Pixel getPixel(int x, int y, AffineTransformation currentAffine) {
        Pixel newPixel;
        if (fractalImage.pixel(x, y) == null) {
            newPixel =
                new Pixel(currentAffine.getRed(), currentAffine.getGreen(), currentAffine.getBlue(), 1);
        } else {
            Pixel existingPixel = fractalImage.pixel(x, y);
            int newRed = (existingPixel.r() + currentAffine.getRed()) / 2;
            int newGreen = (existingPixel.g() + currentAffine.getGreen()) / 2;
            int newBlue = (existingPixel.b() + currentAffine.getBlue()) / 2;
            int count = existingPixel.hitCount();
            count++;

            newPixel = new Pixel(newRed, newGreen, newBlue, count);
        }
        return newPixel;
    }

}
