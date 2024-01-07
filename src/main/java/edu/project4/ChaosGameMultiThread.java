package edu.project4;

import edu.project4.BaseObjects.FractalImage;
import edu.project4.BaseObjects.Pixel;
import edu.project4.BaseObjects.Point;
import edu.project4.BaseObjects.Rectangle;
import edu.project4.Transformations.AffineTransformation;
import edu.project4.Transformations.Transformation;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import static edu.project4.GeneratorUtils.gammaCorrection;
import static edu.project4.GeneratorUtils.generateAffines;

public class ChaosGameMultiThread implements FractalGenerator {

    private final static int ITER_START = -20;
    private final FractalImage fractalImage;
    private final List<Transformation> transformations;
    private final Random random;
    private final ExecutorService executorService;

    public ChaosGameMultiThread(FractalImage fractalImage, List<Transformation> transformations, int threadsCount) {
        this.fractalImage = fractalImage;
        this.transformations = transformations;
        this.random = ThreadLocalRandom.current();
        this.executorService = Executors.newFixedThreadPool(threadsCount);
    }

    @Override
    public FractalImage render(int sample, Rectangle field, int iterPerSample, int eq, int symmerty) {
        List<AffineTransformation> affineTransformations = generateAffines(eq, random);
        List<Callable<Void>> tasks = new ArrayList<>();
        for (int i = 0; i < sample; i++) {
            tasks.add(() -> {
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
                    for (int k = 0; k < symmerty; theta += Math.PI * 2 / symmerty, k++) {
                        Point rotated = point.rotate(theta);
                        synchronized (rotated) {
                            if (field.contains(rotated) && j >= 0) {
                                setPixelOnImage(rotated, field, currentAffine);
                            }
                        }
                    }
                }
                return null;
            });
        }
        try {
            executorService.invokeAll(tasks);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        executorService.shutdown();
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

    private Pixel getPixel(int x, int y, AffineTransformation currentAffine) {
        Pixel newPixel;
        if (fractalImage.pixel(x, y) == null) {
            newPixel = new Pixel(currentAffine.getRed(), currentAffine.getGreen(), currentAffine.getBlue(), 1);
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
