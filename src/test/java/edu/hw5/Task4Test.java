package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static edu.hw5.Task4.checkPasswordForSpecialSymbols;
import static org.assertj.core.api.Assertions.assertThat;

public class Task4Test {

    @ParameterizedTest
    @DisplayName("Test with special symbols")
    @CsvSource({"MyP@ssw0rd", "$_$", "| l#v3 R&P"})
    public void checkPasswordForSpecialSymbols_shouldReturnTrueIfPasswordContainsSpecialSymbols(String validPassword) {
        // Act
        boolean resultValid = checkPasswordForSpecialSymbols(validPassword);

        // Assert
        assertThat(resultValid).isTrue();
    }

    @ParameterizedTest
    @DisplayName("Test for password without special symbols")
    @CsvSource({"Password123", "I love GUF"})
    public void checkPasswordForSpecialSymbols_shouldReturnFalseIfPasswordNotContainsSpecialSymbols(String invalidPassword) {
        // Act
        boolean resultInvalid = checkPasswordForSpecialSymbols(invalidPassword);

        // Assert
        assertThat(resultInvalid).isFalse();
    }

    @Test
    @DisplayName("Test for empty string")
    public void checkPasswordForSpecialSymbols_shouldReturnFalseIfEmptyString() {
        //Arrange
        String emptyString = "";

        //Act
        boolean result = checkPasswordForSpecialSymbols(emptyString);

        //Assert
        assertThat(result).isFalse();
    }
}
