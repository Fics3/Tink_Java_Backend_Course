package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static edu.hw5.Task8.isEveryUnevenIsOne;
import static edu.hw5.Task8.isNoConsecutiveOne;
import static edu.hw5.Task8.isNotBinaryThreeOrSeven;
import static edu.hw5.Task8.isOneEvenOrZeroUneven;
import static edu.hw5.Task8.isStringUneven;
import static edu.hw5.Task8.isTwoOrMore0AndOneOrZero1;
import static edu.hw5.Task8.isZeroCountMultipleOfThree;
import static org.assertj.core.api.Assertions.assertThat;

public class Task8Test {

    @ParameterizedTest
    @DisplayName("Test isStringUneven for valid cases")
    @CsvSource({
        "010",
        "110"
    })
    public void isStringUneven_shouldReturnTrue(String input) {
        // Act
        boolean result = isStringUneven(input);

        // Assert
        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @DisplayName("Test isStringUneven for invalid cases")
    @CsvSource({
        "0111",
        "0011",
        "101110",
        "11100101"
    })
    public void isStringUneven_shouldReturnFalse(String input) {
        // Act
        boolean result = isStringUneven(input);

        // Assert
        assertThat(result).isFalse();
    }

    @ParameterizedTest
    @DisplayName("Test isOneEvenOrZeroUneven for valid cases")
    @CsvSource({
        "010",
        "011",
        "1001",
        "101111"
    })
    public void isOneEvenOrZeroUneven_shouldReturnTrue(String input) {
        // Act
        boolean result = isOneEvenOrZeroUneven(input);

        // Assert
        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @DisplayName("Test isOneEvenOrZeroUneven for invalid cases")
    @CsvSource({
        "00",
        "0001",
        "1001010",
        "10111"
    })
    public void isOneEvenOrZeroUneven_shouldReturnFalse(String input) {
        // Act
        boolean result = isOneEvenOrZeroUneven(input);

        // Assert
        assertThat(result).isFalse();
    }

    @ParameterizedTest
    @DisplayName("Test isZeroCountMultipleOfThree for valid cases")
    @CsvSource({
        "000",
        "0100",
        "00101000",
        "1100111110",
        "101010"
    })
    public void isZeroCountMultipleOfThree_shouldReturnTrue(String input) {
        // Act
        boolean result = isZeroCountMultipleOfThree(input);

        // Assert
        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @DisplayName("Test isZeroCountMultipleOfThree for invalid cases")
    @CsvSource({
        "00",
        "01",
        "001",
        "011",
        "1001",
        "100"
    })
    public void isZeroCountMultipleOfThree_shouldReturnFalse(String input) {
        // Act
        boolean result = isZeroCountMultipleOfThree(input);

        // Assert
        assertThat(result).isFalse();
    }

    @ParameterizedTest
    @DisplayName("Test isNotBinaryThreeOrSeven for valid cases")
    @CsvSource({
        "0",
        "1",
        "010",
        "101"
    })
    public void isNotBinaryThreeOrSeven_shouldReturnTrue(String input) {
        // Act
        boolean result = isNotBinaryThreeOrSeven(input);

        // Assert
        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @DisplayName("Test isNotBinaryThreeOrSeven for invalid cases")
    @CsvSource({
        "11",
        "111"
    })
    public void isNotBinaryThreeOrSeven_shouldReturnFalse(String input) {
        // Act
        boolean result = isNotBinaryThreeOrSeven(input);

        // Assert
        assertThat(result).isFalse();
    }

    @ParameterizedTest
    @DisplayName("Test isEveryUnevenIsOne for valid cases")
    @CsvSource({
        "101",
        "110",
        "10101",
        "1110",
        "1"
    })
    public void isEveryUnevenIsOne_shouldReturnTrue(String input) {
        // Act
        boolean result = isEveryUnevenIsOne(input);

        // Assert
        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @DisplayName("Test isEveryUnevenIsOne for invalid cases")
    @CsvSource({
        "00",
        "01",
        "001",
        "011",
        "1001",
        "100"
    })
    public void isEveryUnevenIsOne_shouldReturnFalse(String input) {
        // Act
        boolean result = isEveryUnevenIsOne(input);

        // Assert
        assertThat(result).isFalse();
    }

    @ParameterizedTest
    @DisplayName("Test isTwoOrMore0AndOneOrZero1 for valid cases")
    @CsvSource({
        "010",
        "100",
        "010000",
        "0001000"
    })
    public void isTwoOrMore0AndOneOrZero1_shouldReturnTrue(String input) {
        // Act
        boolean result = isTwoOrMore0AndOneOrZero1(input);

        // Assert
        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @DisplayName("Test isTwoOrMore0AndOneOrZero1 for invalid cases")
    @CsvSource({
        "0",
        "1",
        "11",
        "01010",
        "11000"
    })
    public void isTwoOrMore0AndOneOrZero1_shouldReturnFalse(String input) {
        // Act
        boolean result = isTwoOrMore0AndOneOrZero1(input);

        // Assert
        assertThat(result).isFalse();
    }

    @ParameterizedTest
    @DisplayName("Test isNoConsecutiveOne for valid cases")
    @CsvSource({
        "0",
        "1",
        "00",
        "01",
        "010",
        "101",
        "0001001010",
        "10001"
    })
    public void isNoConsecutiveOne_shouldReturnTrue(String input) {
        // Act
        boolean result = isNoConsecutiveOne(input);

        // Assert
        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @DisplayName("Test isNoConsecutiveOne for invalid cases")
    @CsvSource({
        "0011",
        "011",
        "1100",
        "10110",
        "1101",
        "1110",
        "1011"
    })
    public void isNoConsecutiveOne_shouldReturnFalse(String input) {
        // Act
        boolean result = isNoConsecutiveOne(input);

        // Assert
        assertThat(result).isFalse();
    }
}
