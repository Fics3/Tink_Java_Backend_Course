package edu.project4.Transformations;

import edu.project4.BaseObjects.Point;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class TransformationTest {

    static Arguments[] transformators() {
        return new Arguments[] {
            Arguments.of(new HeartTransformation()),
            Arguments.of(new PolarTransformation()),
            Arguments.of(new SphericTransformation())
        };
    }

    @ParameterizedTest
    @DisplayName("apply - Result no equal input")
    @MethodSource("transformators")
    public void generateMaze_eneratedMazeSizeShouldEqualsGivenSize(Transformation transformation) {
        //Assert
        Point point = new Point(1, 1);

        // Act
        var result = transformation.apply(point);

        // Assert
        assertThat(result.x()).isNotEqualTo(point.x());
        assertThat(result.y()).isNotEqualTo(point.y());
    }

}
