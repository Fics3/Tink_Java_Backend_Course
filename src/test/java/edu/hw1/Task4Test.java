package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static edu.hw1.Task4.fixString;
import static org.assertj.core.api.Assertions.assertThat;

public class Task4Test {

    @ParameterizedTest
    @DisplayName("Test with even string length")
    @CsvSource({"hTsii  s aimex dpus rtni.g,This is a mixed up string.", "ababab,bababa"})
    void fixString_shouldFixStringWithEvenLength(String cursedEvenLenStr, String expected) {
        // Act
        String result = fixString(cursedEvenLenStr);

        // Assert
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("Test with uneven string length")
    @CsvSource({"badce,abcde", "ebebb,bebeb"})
    void fixString_shouldFixStringWithUnevenLength(String cursedUnevenLenStr, String expected) {
        // Act
        String result = fixString(cursedUnevenLenStr);

        // Assert
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("Test with null input")
    void fixString_nullStringCase() {
        // Arrange
        String nullSrt = null;

        // Act
        String result = fixString(nullSrt);

        // Assert
        assertThat(result).isEqualTo(null);
    }

    @Test
    @DisplayName("Test with empty string")
    void fixString_emptyStringCase() {
        // Arrange
        String emptyStr = "";

        // Act
        String result = fixString(emptyStr);

        // Assert
        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("Test with one character")
    void fixString_shouldFixStringsWithOneChar() {
        // Arrange
        String oneCharStr = ")";

        // Act
        String result = fixString(oneCharStr);

        // Assert
        assertThat(result).isEqualTo(")");
    }

}
