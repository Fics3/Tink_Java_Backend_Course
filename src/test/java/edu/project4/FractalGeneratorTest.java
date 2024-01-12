package edu.project4;

import edu.project4.BaseObjects.FractalImage;
import edu.project4.BaseObjects.Rectangle;
import edu.project4.Transformations.SphericTransformation;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class FractalGeneratorTest {

    static Arguments[] generators() {
        return new Arguments[] {
            Arguments.of(new ChaosGame(FractalImage.create(100, 100), List.of(new SphericTransformation()))),
            Arguments.of(new ChaosGameMultiThread(
                FractalImage.create(100, 100),
                List.of(new SphericTransformation()),
                1
            ))
        };
    }

    @ParameterizedTest
    @DisplayName("render - Generates fractal image with the specified parameters")
    @MethodSource("generators")
    void render_GeneratesFractal_ImageWithParameters(FractalGenerator fractalGenerator) {
        // Arrange
        Rectangle field = new Rectangle(0, 0, 10, 10);

        // Act
        FractalImage result = fractalGenerator.render(500, field, 500, 10, 3);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.height()).isEqualTo(100);
        assertThat(result.width()).isEqualTo(100);
    }

    @ParameterizedTest
    @DisplayName("render - Changes fractal pixels")
    @MethodSource("generators")
    void render_GeneratesFractal_ChangesPixels(FractalGenerator fractalGenerator) {
        // Arrange
        Rectangle field = new Rectangle(0, 0, 10, 10);

        // Act
        FractalImage result = fractalGenerator.render(500, field, 500, 10, 3);

        // Assert
        int countNotNull = 0;
        for (int i = 0; i < result.data().length; i++) {
            if (result.data()[i] != null) {
                countNotNull++;
            }
        }
        assertThat(countNotNull).isNotZero();
    }
}
