package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static edu.hw5.Task7.isStartEqualsEnd;
import static edu.hw5.Task7.isStringSizeBetweenOneAndThree;
import static edu.hw5.Task7.isThirdZeroAndLengthMoreThree;
import static org.assertj.core.api.Assertions.assertThat;

public class Task7Test {

    @ParameterizedTest
    @DisplayName("Test isThirdZeroAndLengthMoreThree for valid cases")
    @CsvSource({
        "00000",
        "110100"
    })
    public void isThirdZeroAndLengthMoreThree_shouldReturnTrue(String input) {
        // Act
        boolean result = isThirdZeroAndLengthMoreThree(input);

        // Assert
        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @DisplayName("Test isThirdZeroAndLengthMoreThree for invalid cases")
    @CsvSource({
        "0110",
        "1010"
    })
    public void isThirdZeroAndLengthMoreThree_shouldReturnFalse(String input) {
        // Act
        boolean result = isThirdZeroAndLengthMoreThree(input);

        // Assert
        assertThat(result).isFalse();
    }

    @ParameterizedTest
    @DisplayName("Test isStartEqualsEnd for valid cases")
    @CsvSource({
        "010",
        "1",
        "01010"
    })
    public void isStartEqualsEnd_shouldReturnTrue(String input) {
        // Act
        boolean result = isStartEqualsEnd(input);

        // Assert
        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @DisplayName("Test isStartEqualsEnd for invalid cases")
    @CsvSource({
        "100",
        "010111"
    })
    public void isStartEqualsEnd_shouldReturnFalse(String input) {
        // Act
        boolean result = isStartEqualsEnd(input);

        // Assert
        assertThat(result).isFalse();
    }

    @ParameterizedTest
    @DisplayName("Test isStringSizeBetweenOneAndThree for valid cases")
    @CsvSource({
        "0",
        "1",
        "010",
        "101"
    })
    public void isStringSizeBetweenOneAndThree_shouldReturnTrue(String input) {
        // Act
        boolean result = isStringSizeBetweenOneAndThree(input);

        // Assert
        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @DisplayName("Test isStringSizeBetweenOneAndThree for invalid cases")
    @CsvSource({
        "0010",
        "101010"
    })
    public void isStringSizeBetweenOneAndThree_shouldReturnFalse(String input) {
        // Act
        boolean result = isStringSizeBetweenOneAndThree(input);

        // Assert
        assertThat(result).isFalse();
    }
}
