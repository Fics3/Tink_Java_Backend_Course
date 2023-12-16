package edu.hw8.Task2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static edu.hw8.Task2.MultiThreadFibonacci.countFibonacciParallel;
import static org.assertj.core.api.Assertions.assertThat;

public class MultiThreadFibonacciTest {
    @Test
    @DisplayName("countFibonacciParallel - Threads equals last fibonacci value ")
    void countFibonacciParallel_CalculateFibonacciValues_ThreadsEqualsLastValue() {
        //Arrange
        int lastFibonacciValue = 10;
        int threadsCount = 10;

        //Act
        var result = countFibonacciParallel(lastFibonacciValue, threadsCount);

        //Assert
        assertThat(result.get(9)).isEqualTo(34);
    }

    @Test
    @DisplayName("countFibonacciParallel - Threads less than fibonacci value ")
    void countFibonacciParallel_CalculateFibonacciValues_ThreadsLessLastValue() {
        //Arrange
        int lastFibonacciValue = 10;
        int threadsCount = 4;

        //Act
        var result = countFibonacciParallel(lastFibonacciValue, threadsCount);

        //Assert
        assertThat(result.get(9)).isEqualTo(34);
    }
}
