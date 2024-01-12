package edu.project4.Transformations;

import edu.project4.BaseObjects.Point;
import java.util.Random;
import lombok.Getter;

@Getter
public class AffineTransformation {

    private static final int RGB_BOUND = 256;

    private double c;
    private double f;

    private int red;
    private int green;
    private int blue;

    private double a;
    private double b;
    private double d;
    private double e;

    public AffineTransformation(Random random) {
        generate(random);
    }

    private static boolean isValid(double a, double b, double d, double e) {
        double aSumD = a * a + d * d;
        double bSumE = b * b + e * e;
        boolean firstCondition = aSumD < 1;
        boolean secondCondition = bSumE < 1;
        boolean thirdCondition = aSumD + bSumE < 1 + Math.pow(a * e + b * d, 2);

        return firstCondition && secondCondition && thirdCondition;
    }

    private void generate(Random random) {
        do {
            a = random.nextDouble(-1, 1);
            b = random.nextDouble(-1, 1);
            d = random.nextDouble(-1, 1);
            e = random.nextDouble(-1, 1);
        } while (isValid(a, b, d, e));
        c = random.nextDouble(-1, 1);
        f = random.nextDouble(-1, 1);
        red = random.nextInt(RGB_BOUND);
        green = random.nextInt(RGB_BOUND);
        blue = random.nextInt(RGB_BOUND);
    }

    public Point getTransformation(Point point) {

        double x = a * point.x() + b * point.y() + c * 2 - 1;
        double y = d * point.x() + e * point.y() + f * 2 - 1;

        return new Point(x, y);
    }
}

