package edu.project4;

import edu.project4.BaseObjects.FractalImage;
import edu.project4.BaseObjects.Pixel;
import edu.project4.Transformations.AffineTransformation;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.project4.GeneratorUtils.gammaCorrection;
import static edu.project4.GeneratorUtils.generateAffines;
import static org.assertj.core.api.Assertions.assertThat;

public class GeneratorUtilsTest {

    @Test
    @DisplayName("generate Affines - Generates the specified number of affines")
    void generateAffines_GeneratesCorrectNumberOfAffines() {
        // Arrange
        int countAff = 5;
        Random random = new Random();

        // Act
        List<AffineTransformation> affineTransformations = generateAffines(countAff, random);

        // Assert
        assertThat(affineTransformations).isNotNull();
        assertThat(affineTransformations.size()).isEqualTo(countAff);
    }

    @Test
    @DisplayName("gammaCorrection - adjusts pixel values")
    void gammaCorrection_AdjustsPixelValues() {
        // Arrange
        Pixel newPixel = new Pixel(255, 255, 255, 10);
        FractalImage fractalImage = FractalImage.create(2, 2);
        fractalImage.updatePixel(1, 1, newPixel);

        // Act
        gammaCorrection(fractalImage);

        // Assert
        assertThat(fractalImage.pixel(1, 1).normal()).isNotEqualTo(newPixel.normal());
    }

}
