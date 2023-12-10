package edu.project4.BaseObjects;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class FractalImageTest {

    @Test
    @DisplayName("create - Should be expected size")
    void create_ValidDimensions_Success() {
        // Arrange
        int width = 800;
        int height = 600;

        // Act
        FractalImage fractalImage = FractalImage.create(width, height);

        // Assert
        assertThat(fractalImage.width()).isEqualTo(width);
        assertThat(fractalImage.height()).isEqualTo(height);
        assertThat(fractalImage.data().length).isEqualTo(width * height);
    }

    @Test
    @DisplayName("contains - Point inside image")
    void contains_PointInsideImage_True() {
        // Arrange
        FractalImage fractalImage = FractalImage.create(800, 600);

        // Act & Assert
        assertThat(fractalImage.contains(400, 300)).isTrue();
    }

    @Test
    @DisplayName("contains - Point outside image")
    void contains_PointOutsideImage_False() {
        // Arrange
        FractalImage fractalImage = FractalImage.create(800, 600);

        // Act & Assert
        assertThat(fractalImage.contains(1000, 800)).isFalse();
    }

    @Test
    @DisplayName("pixel - Returns expected pixel")
    void pixel_ValidCoordinates_ReturnsPixel() {
        // Arrange
        FractalImage fractalImage = FractalImage.create(800, 600);
        Pixel expectedPixel = new Pixel(255, 0, 0, 1);

        // Act
        fractalImage.updatePixel(100, 200, expectedPixel);
        Pixel actualPixel = fractalImage.pixel(100, 200);

        // Assert
        assertThat(actualPixel).isEqualTo(expectedPixel);
    }

    @Test
    @DisplayName("updatePixel - Pixel expected")
    void updatePixel_ValidCoordinates_PixelUpdated() {
        // Arrange
        FractalImage fractalImage = FractalImage.create(800, 600);
        Pixel newPixel = new Pixel(0, 255, 0, 1);

        // Act
        fractalImage.updatePixel(300, 400, newPixel);
        Pixel updatedPixel = fractalImage.pixel(300, 400);

        // Assert
        assertThat(updatedPixel).isEqualTo(newPixel);
    }

}
