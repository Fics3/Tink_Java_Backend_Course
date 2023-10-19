package edu.hw2;

import edu.hw2.Task2.Rectangle;
import edu.hw2.Task2.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.within;

public class Task2Test {

    static Arguments[] rectangles() {
        return new Arguments[] {
            Arguments.of(new Rectangle()),
            Arguments.of(new Square())
        };
    }

    @ParameterizedTest
    @DisplayName("Test from Task2 that shows Liskov substitution principe")
    @MethodSource("rectangles")
    void rectangleArea(Rectangle rect) {
        rect = rect.setWidth(20).setHeight(10);
        assertThat(rect.area()).isEqualTo(200.0);
    }

    @Test
    @DisplayName("Test of finding area of Rectangle")
    public void areaRectangle_shouldReturnRectangleArea() {
        // Arrange
        Rectangle rectangle = new Rectangle(4, 5);

        // Act
        double area = rectangle.area();

        // Assert
        assertThat(area).isEqualTo(20.0, within(0.01));
    }

    @Test
    @DisplayName("Test of finding area of Square")
    public void areaSquare_shouldReturnCorrectSquareArea() {
        // Arrange
        Square square = new Square(4);

        // Act
        double area = square.area();

        // Assert
        assertThat(area).isEqualTo(16.0, within(0.01));
    }

    @Test
    @DisplayName("Test for correct setWidth of Square that change both sides")
    public void setWidthSquare_shouldChangeBothSquareSide() {
        // Arrange
        Square square = new Square();

        // Act
        Rectangle rectangle = square.setWidth(3);

        // Assert
        assertThat(rectangle.area()).isEqualTo(9.0, within(0.01));
    }

    @Test
    @DisplayName("Test for correct setHeight of Square that change both sides")
    public void setHeightSquare_shouldChangeBothSquareSide() {
        // Arrange
        Square square = new Square();

        // Act
        Rectangle rectangle = square.setHeight(2);

        // Assert
        assertThat(rectangle.area()).isEqualTo(4.0, within(0.01));
    }
}
