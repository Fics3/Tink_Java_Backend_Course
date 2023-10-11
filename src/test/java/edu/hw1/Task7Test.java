package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static edu.hw1.Task7.rotateLeft;
import static edu.hw1.Task7.rotateRight;
import static org.assertj.core.api.Assertions.assertThat;

public class Task7Test {

    @ParameterizedTest
    @DisplayName("Test for rotating left with even number")
    @CsvSource({"16,1,1", "12,3,6"})
    void rotateLeft_shouldRotateLeftEvenNumbers(int evenNumber, int shift, int expected) {
        // Act
        int result = rotateLeft(evenNumber, shift);
        // Assert
        assertThat(result).isEqualTo(expected);
        //16 = 10000 <- 00001 = 1
        //12 = 1100 <- 1001 <- 0011 <- 0110
    }

    @ParameterizedTest
    @DisplayName("Test for rotating left with uneven number")
    @CsvSource({"17,2,6", "29,4,30"})
    void rotateLeft_shouldRotateLeftUnevenNumbers(int unevenNumber, int shift, int expected) {
        // Act
        int result = rotateLeft(unevenNumber, shift);
        // Assert
        assertThat(result).isEqualTo(expected);
        //17 = 10001 <- 00011 <- 00110 = 6
        //29 = 11101 <- 11011 <- 10111 <- 01111 <- 11110
    }

    @ParameterizedTest
    @DisplayName("Test for rotating right with even number")
    @CsvSource({"16,1,8", "12,3,9"})
    void rotateRight_shouldRotateRightEvenNumTest(int evenNumber, int shift, int expected) {
        // Arrange
        // Act
        int result = rotateRight(evenNumber, shift);

        // Assert
        assertThat(result).isEqualTo(expected);
        //16 = 10000 -> 01000 = 8
        //12 = 1100 -> 0110 -> 0011-> 1001 = 9
    }

    @ParameterizedTest
    @DisplayName("Test for rotating right with uneven number")
    @CsvSource({"17,2,12", "29,4,27"})
    void rotateRight_shouldRotateRightUnevenNumbers(int unevenNumber, int shift, int expected) {
        // Act
        int result = rotateRight(unevenNumber, shift);
        // Assert
        assertThat(result).isEqualTo(expected);
        //17 = 10001 -> 11000 -> 01100 = 12
        //29 = 11101 -> 11110 -> 01111 -> 10111 -> 11011 = 27
    }

    @Test
    @DisplayName("Test for rotating a number with only one digit")
    void rotateLeft_shouldRotateNumberWithOnlyOne() {
        // Arrange
        int input1 = 31;
        int shift1 = 3;

        // Act
        int result1 = rotateLeft(input1, shift1);

        // Assert
        assertThat(result1).isEqualTo(input1);
        //11111 -> 11111 -> 11111 -> 11111
    }
    @Test
    @DisplayName("Test for rotating a number with only one digit")
    void rotateRight_shouldRotateNumberWithOnlyOne() {
        // Arrange
        int input1 = 31;
        int shift1 = 3;

        // Act
        int result = rotateRight(input1, shift1);

        // Assert
        assertThat(result).isEqualTo(input1);
        //11111 <- 11111 <- 11111 <- 11111
    }

    @ParameterizedTest
    @DisplayName("Test for rotate left a full circle rotation")
    @CsvSource({"16,5,16", "17,5,17"})
    void rotateLeft_shouldMakeFullCircleRotate(int num, int shift, int expected) {
        // Act
        int result = rotateLeft(num, shift);

        // Assert
        assertThat(result).isEqualTo(expected);
        //16 = 10000 <- 00001 <- 00010 <- 00100 <- 01000 <- 10000
        //17 = 10001 <- 00011 <- 00110 <- 01100 <- 11000 <- 10001
    }

    @ParameterizedTest
    @DisplayName("Test for rotate right a full circle rotation")
    @CsvSource({"16,5,16", "17,5,17"})
    void rotateRight_shouldMakeFullCircleRotate(int num, int shift, int expected) {
        // Act
        int result = rotateRight(num, shift);

        // Assert
        assertThat(result).isEqualTo(expected);
        // 16 = 10000 -> 00001 -> 00010 -> 00100 -> 01000 -> 10000
        // 17 = 10001 -> 11000 -> 01100 -> 00110 -> 00011 -> 10001
    }

    @Test
    @DisplayName("Test for rotate left negative values")
    void rotateLeft_shouldHandleInvalidInputNegativeNumber() {
        // Arrange
        int input1 = -5;
        int shift1 = 1;

        // Act
        int result = rotateLeft(input1, shift1);

        // Assert
        assertThat(result).isEqualTo(-1);
    }

    @Test
    @DisplayName("Test for rotate right negative values")
    void rotateRight_shouldHandleInvalidInputNegativeNumber() {
        // Arrange
        int input1 = -5;
        int shift1 = 1;

        // Act
        int result = rotateRight(input1, shift1);

        // Assert
        assertThat(result).isEqualTo(-1);
    }

    @Test
    @DisplayName("Test for rotate left negative shift values")
    void rotateLeft_shouldHandleInvalidInputNegativeShift() {
        // Arrange
        int input1 = 4;
        int shift1 = -1;

        // Act
        int result = rotateLeft(input1, shift1);

        // Assert
        assertThat(result).isEqualTo(-1);

    }

    @Test
    @DisplayName("Test for rotate right negative shift values")
    void rotateRight_shouldHandleInvalidInputNegativeShift() {
        // Arrange
        int input1 = 4;
        int shift1 = -1;

        // Act
        int result = rotateRight(input1, shift1);

        // Assert
        assertThat(result).isEqualTo(-1);

    }

    @Test
    @DisplayName("Test for rottate left maximum int value")
    void rotateLeft_rotateRight_shouldRotateMaxInt() {
        // Arrange
        int input1 = Integer.MAX_VALUE;
        int shift1 = 1;

        // Act
        int result = rotateLeft(input1, shift1);

        // Assert
        assertThat(result).isEqualTo(Integer.MAX_VALUE);
    }

    @Test
    @DisplayName("Test for rotate right maximum int value")
    void rotateRight_shouldRotateMaxInt() {
        // Arrange
        int input1 = Integer.MAX_VALUE;
        int shift1 = 1;

        // Act
        int result = rotateRight(input1, shift1);

        // Assert
        assertThat(result).isEqualTo(Integer.MAX_VALUE);
    }

}
