package edu.project4.BaseObjects;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;

public class PixelTest {

    @ParameterizedTest
    @DisplayName("normal - should equals expected normal")
    @CsvSource({"10,1", "20,1.3010299956639813", "30,1.4771212547196624"})
    void normal_NormalOfPixelCount_ShouldEqualExpected(int hitCount, double expectedNormal) {
        //Arrange
        Pixel pixel = new Pixel(255, 255, 255, hitCount);

        //Act
        double normal = pixel.normal();

        //Assert
        assertThat(normal).isEqualTo(expectedNormal);

    }
}
