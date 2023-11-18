package edu.hw7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static edu.hw7.Task2.parallelFactorial;
import static org.assertj.core.api.Assertions.assertThat;

public class Task2Test {
    @Test
    @DisplayName("Calculate factorial for small value")
    void parallelFactorial_CalculateFactorialForSmallValue_ReturnsCorrectResult() {
        // Arrange
        int factorialValue = 5;
        BigInteger expectedResult = BigInteger.valueOf(120);

        // Act
        BigInteger result = parallelFactorial(factorialValue);

        // Assert
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("Calculate factorial for large value")
    void parallelFactorial_CalculateFactorialForLargeValue_ReturnsCorrectResult() {
        // Arrange
        int factorialValue = 20;
        BigInteger expectedResult = new BigInteger("2432902008176640000");

        // Act
        BigInteger result = parallelFactorial(factorialValue);

        // Assert
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("Calculate factorial for zero")
    void parallelFactorial_CalculateFactorialForZero_ReturnsOne() {
        // Arrange
        int factorialValue = 0;
        BigInteger expectedResult = BigInteger.ONE;

        // Act
        BigInteger result = parallelFactorial(factorialValue);

        // Assert
        assertThat(result).isEqualTo(expectedResult);
    }
}
