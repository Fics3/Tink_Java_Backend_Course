package edu.project4.BaseObjects;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class RectangleTest {
    @Test
    @DisplayName("contains - Point inside")
    void contains_PointInsideRectangle_True() {
        // Arrange
        Rectangle rectangle = new Rectangle(0, 0, 10, 10);
        Point pointInside = new Point(5, 4);

        // Act
        var result = rectangle.contains(pointInside);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("contains - Point outside")
    void contains_PointOutsideRectangle_False() {
        // Arrange
        Rectangle rectangle = new Rectangle(0, 0, 5, 5);
        Point pointOutside = new Point(15, 15);

        // Act
        var result = rectangle.contains(pointOutside);

        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("getXMax - correct value")
    void getXMax_ValidRectangle_ReturnsCorrectValue() {
        // Arrange
        Rectangle rectangle = new Rectangle(10, 10, 5, 5);

        // Act
        double xMax = rectangle.getXMax();

        // Assert
        assertThat(xMax).isEqualTo(12.5);
    }

    @Test
    @DisplayName("getXMin - correct value")
    void getXMin_ValidRectangle_ReturnsCorrectValue() {
        // Arrange
        Rectangle rectangle = new Rectangle(10, 10, 5, 5);

        // Act
        double xMin = rectangle.getXMin();

        // Assert
        assertThat(xMin).isEqualTo(7.5);
    }

    @Test
    @DisplayName("getYMax - correct value")
    void getYMax_ValidRectangle_ReturnsCorrectValue() {
        // Arrange
        Rectangle rectangle = new Rectangle(10, 10, 5, 5);

        // Act
        double yMax = rectangle.getYMax();

        // Assert
        assertThat(yMax).isEqualTo(12.5);
    }

    @Test
    @DisplayName("getYMin - correct value")
    void getYMin_ValidRectangle_ReturnsCorrectValue() {
        // Arrange
        Rectangle rectangle = new Rectangle(10, 10, 5, 5);

        // Act
        double yMin = rectangle.getYMin();

        // Assert
        assertThat(yMin).isEqualTo(7.5);
    }
}
