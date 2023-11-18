package edu.hw7.Task4;

import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;

class PiCalculatorTest {

    @ParameterizedTest
    @DisplayName("Calculate Pi - Close to Actual Value")
    @CsvSource({"10000,1", "100000,1", "1000000,1", "10000000,0.1"})
    void calculatePi_CloseToActualValue(int n, double margin) {
        // Arrange
        PiCalculator piCalculator = new PiCalculator();
        double expectedPi = Math.PI;

        for (int i = 0; i < 100; i++) {
            //Act
            double calculatedPi = piCalculator.calculatePi(n);

            // Assert
            assertThat(calculatedPi).isCloseTo(expectedPi, Percentage.withPercentage(margin));
        }

    }

}
