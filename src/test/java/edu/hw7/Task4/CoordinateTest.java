package edu.hw7.Task4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class CoordinateTest {

    @Test
    @DisplayName("Calculate Distance - Same Coordinates")
    void calculateDistance_SameCoordinates_ShouldBeZero() {
        // Arrange
        Coordinate coordinate = new Coordinate(1.0, 2.0);

        // Act
        double distance = coordinate.calculateDistance(coordinate);

        // Assert
        assertThat(distance).isEqualTo(0.0);
    }

    @Test
    @DisplayName("Calculate Distance - Different Coordinates")
    void calculateDistance_DifferentCoordinates_ShouldBeCorrect() {
        // Arrange
        Coordinate coordinate1 = new Coordinate(1.0, 2.0);
        Coordinate coordinate2 = new Coordinate(4.0, 6.0);

        // Act
        double distance = coordinate1.calculateDistance(coordinate2);

        // Assert
        assertThat(distance).isEqualTo(5.0);
    }


}
