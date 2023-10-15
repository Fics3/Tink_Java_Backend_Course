package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static edu.hw1.Task6.countK;
import static org.assertj.core.api.Assertions.assertThat;

public class Task6Test {

    @ParameterizedTest
    @DisplayName("Test for possible Kaprekar range")
    @CsvSource({"6621,5","1234,3"})
    void countK_shouldCountKaprekarConstInPossibleRange(int numInPossibleRange, int expected) {
        // Act
        int result = countK(numInPossibleRange);
        // Assert
        assertThat(result).isEqualTo(expected);
        //1.6621 - 1266 = 5355
        //2.5553 - 3555 = 1998
        //3.9981 - 1899 = 8082
        //4.8820 - 0288 = 8352
        //5.8532 - 2358 = 6174

        //1.4321 - 1234 = 3087
        //2.8730 - 0378 = 8352
        //3.8532 - 2358 = 6174
    }

    @Test
    @DisplayName("Test for Kaprekar constant")
    void countK_shouldCountKaprekarConstForKaprekarConst() {
        // Arrange
        int kaprekarConstNum = 6174;

        // Act
        int result = countK(kaprekarConstNum);

        // Assert
        assertThat(result).isEqualTo(0);
    }

    @Test
    @DisplayName("Test for impossible range below")
    void countK_shouldHandleInvalidInputRangeBelowKaprekarConst() {
        // Arrange
        int numBelowPossibleRange = 999;

        // Act
        int result = countK(numBelowPossibleRange);

        // Assert
        assertThat(result).isEqualTo(-1);
        //999 < 1000
    }

    @Test
    @DisplayName("Test for impossible range higher")
    void countK_shouldHandleInvalidInputRangeHigherKaprekarConst() {
        // Arrange
        int numHigherPossibleRange = 100000;

        // Act
        int result = countK(numHigherPossibleRange);

        // Assert
        assertThat(result).isEqualTo(-1);
        //100000 > 9999
    }

    @Test
    @DisplayName("Test for a number with similar digits")
    void countK_shouldHandleInvalidInputNumberWithAllSimilarDigits() {
        // Arrange
        int similarDigitsNum = 4444;

        // Act
        int result = countK(similarDigitsNum);

        // Assert
        assertThat(result).isEqualTo(-1);
        //4444-4444 = 0 -> -1
    }

}
