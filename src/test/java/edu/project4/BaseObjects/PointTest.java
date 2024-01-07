package edu.project4.BaseObjects;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;

public class PointTest {

    @ParameterizedTest
    @DisplayName("rotate - should rotate on angle")
    @CsvSource({"10,10,-2.9505041818708264,-13.830926399658221,10", "20,20,-10.097263778284715,26.420546250820394,20",
        "30,30,34.268492219413375,-25.013405226158334,30"})
    void rotate_RotatePoint_EqualsExpected(double x, double y, double xE, double yE, double angel) {
        //Arrange
        Point point = new Point(x, y);

        //Act
        point = point.rotate(angel);

        //Assert
        assertThat(point.x()).isEqualTo(xE);
        assertThat(point.y()).isEqualTo(yE);

    }
}
