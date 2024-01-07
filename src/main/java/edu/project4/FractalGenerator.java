package edu.project4;

import edu.project4.BaseObjects.FractalImage;
import edu.project4.BaseObjects.Rectangle;

@FunctionalInterface
public interface FractalGenerator {

    FractalImage render(int sample, Rectangle field, int iterPerSample, int eq, int symmetry);
}
