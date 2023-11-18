package edu.hw7.Task4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withinPercentage;

class PiCalculatorMultiThreadTest {

    @ParameterizedTest
    @DisplayName("Calculate Pi - Close to Actual Value")
    @CsvSource({"10000, 1, 2", "100000, 1, 4", "1000000, 1, 8", "10000000, 1, 16"})
    void calculatePi_CloseToActualValue(int n, double margin, int threadCount) {
        // Arrange
        PiCalculatorMultiThread calculator = new PiCalculatorMultiThread();
        double expectedPi = Math.PI;

        for (int i = 0; i < 100; i++) {
            // Act
            double result = calculator.calculatePi(n, threadCount);

            // Assert
            assertThat(result).isCloseTo(expectedPi, withinPercentage(margin));
        }
    }

}
