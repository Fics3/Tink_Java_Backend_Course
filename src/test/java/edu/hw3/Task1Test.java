package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw3.Task1.atbash;
import static org.assertj.core.api.Assertions.assertThat;

public class Task1Test {

    @Test
    @DisplayName("Test for non chars in input")
    void atbash_shouldNotEncodeNotChars() {
        //Arrange
        String stringWithoutChars = "1!<)";
        //Act
        String result = atbash(stringWithoutChars);

        //Assert
        assertThat(result).isEqualTo(stringWithoutChars);
    }

    @Test
    @DisplayName("Test for empty string")
    void atbash_shouldReturnEmptyString() {
        //Arrange
        String emptyString = "";
        //Act
        String result = atbash(emptyString);

        //Assert
        assertThat(result).isEqualTo(emptyString);
    }

    @Test
    @DisplayName("Test for encoding chars in lower case")
    void atbash_shouldReturnInLowerCaseResult() {
        //Arrange
        String lowerCaseString = "abc";
        //Act
        String result = atbash(lowerCaseString);

        //Assert
        assertThat(result).isEqualTo("zyx");
    }

    @Test
    @DisplayName("Test for encoding chars in upper case")
    void atbash_shouldReturnInUpperCaseResult() {
        //Arrange
        String upperCaseString = "ABC";
        //Act
        String result = atbash(upperCaseString);

        //Assert
        assertThat(result).isEqualTo("ZYX");
    }

    @Test
    @DisplayName("Test for encoding chars in mixed case")
    void atbash_shouldReturnResultWithoutChangingCaseOfChars() {
        //Arrange
        String upperCaseString = "Hello, World!";
        //Act
        String result = atbash(upperCaseString);

        //Assert
        assertThat(result).isEqualTo("Svool, Dliow!");
        // H - 8 -> 26 - 8 + 1 = 19 = S
        // E - 5 -> 26 - 5 + 1 = 22 = V
        // L - 12 -> 26 - 12 + 1 = 15 = O
        // O - 15 -> 26 - 15 + 1 = 12 = L
    }
}
