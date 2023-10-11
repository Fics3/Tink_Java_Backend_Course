package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static edu.hw1.Task5.isPalindromeDescendant;
import static org.assertj.core.api.Assertions.assertThat;

public class Task5Test {

    @Test
    @DisplayName("Test for already palindromes (should return true)")
    void isPalindromeDescendant_shouldCheckPositiveNumberAlreadyPalindrome() {
        // Arrange
        int alreadyPalindrome = 11;

        // Act
        boolean result = isPalindromeDescendant(alreadyPalindrome);

        // Assert
        assertThat(result).isTrue();
        // 11 is Palindrome
    }

    @Test
    @DisplayName("Test for descendent palindromes (should return true)")
    void isPalindromeDescendant_shouldCheckPositiveNumberDescendantPalindrome() {
        // Arrange
        int numWithDescendantPalindrome = 11211230;

        // Act
        boolean result = isPalindromeDescendant(numWithDescendantPalindrome);

        // Assert
        assertThat(result).isTrue();
        // 1+1 2+1 1+2 3+0 =2333 -> 2+3 3+3 = 56 -> 5+6 -> 11 Palindrome
    }

    @ParameterizedTest
    @DisplayName("Test for positive non-palindromes (should return false)")
    @ValueSource(ints = {12, 12345})
    void isPalindromeDescendant_shouldCheckPositiveNumberNonPalindromeTest(int numWithoutDescendantPalindrome) {
        // Act
        boolean result = isPalindromeDescendant(numWithoutDescendantPalindrome);
        // Assert
        assertThat(result).isFalse();
        //12 -> 1+2 = 3 < 10 nonPalindrome
        //12345 -> 1+2 3+4 5 = 375 -> 3+7 5 = 105 -> 1+0 5 = 15 -> 1+5 = 6 < 10 NonPalindrome
    }

    @ParameterizedTest
    @DisplayName("Test for one-digit positive numbers (should return false)")
    @ValueSource(ints = {5, 0})
    void isPalindromeDescendant_shouldCheckPositiveOneDigitNumber(int oneDigitNum) {
        // Act
        boolean result = isPalindromeDescendant(oneDigitNum);
        // Assert
        assertThat(result).isFalse();
        //5 < 10 nonPalindrome
        //0 < 10 nonPalindrome
    }

    @Test
    @DisplayName("Test for INT_MAX (should return false)")
    void isPalindromeDescendant_shouldCheckIntegerMaxValue() {
        // Arrange
        int intMaxValue = Integer.MAX_VALUE;

        // Act
        boolean result = isPalindromeDescendant(intMaxValue);

        // Assert
        assertThat(result).isFalse();
        // 2147483647 = 2+1 4+7 4+8 3+6 4+7 = 31112911 ->
        // -> 3+1 1+1 2+9 1+1 -> 42112 ->4+2 1+1 2 = 622 ->6+2 2 ->
        // 82 -> 8+2 = 10 -> 1+0 = 1 < 10 nonPalindrome
    }

    @Test
    @DisplayName("Test for INT_MIN (should return true)")
    void isPalindromeDescendant_shouldCheckIntegerMinValue() {
        // Arrange
        int intMinValue = Integer.MIN_VALUE;

        // Act
        boolean result = isPalindromeDescendant(intMinValue);

        // Assert
        assertThat(result).isTrue();
        // 2147483648 = 2+1 4+7 4+8 3+6 4+8 = 31112912 ->
        // -> 3+1 1+1 2+9 1+2 = 42113 -> 4+2 1+1 3 = 623 ->
        // -> 6+2 3 = 83 -> 8+3 = 11 isPalindrome
    }

    @Test
    @DisplayName("Test for negative already palindrome (should return true)")
    void isPalindromeDescendant_shouldCheckNegativeNumberAlreadyPalindrome() {
        // Arrange
        int negativeAlreadyPalindrome = -11;

        // Act
        boolean result = isPalindromeDescendant(negativeAlreadyPalindrome);

        // Assert
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Test for negative palindromes")
    void isPalindromeDescendant_shouldCheckNegativeNumberDescendentPalindrome() {
        // Arrange
        int negativeWithDescendentPalindrome = -11211230;

        // Act
        boolean result = isPalindromeDescendant(negativeWithDescendentPalindrome);

        // Assert
        assertThat(result).isTrue();
        // 1+1 2+1 1+2 3+0 =2333 -> 2+3 3+3 = 56 -> 5+6 -> 11 Palindrome
    }

    @ParameterizedTest
    @DisplayName("Test for negative non-palindromes")
    @ValueSource(ints = {-12, -12345})
    void isPalindromeDescendant_shouldCheckNegativeNumberNonPalindrome(int negativeWithoutDescendentPalindrome) {
        // Act
        boolean result = isPalindromeDescendant(negativeWithoutDescendentPalindrome);
        // Assert
        assertThat(result).isFalse();
        //12 -> 1+2 = 3 < 10 nonPalindrome
        //12345 -> 1+2 3+4 5 = 375 -> 3+7 5 = 105
        // -> 1+0 5 = 15 -> 1+5 = 6 < 10 NonPalindrome
    }

    @Test
    @DisplayName("Test for one-digit negative numbers (should return false)")
    void isPalindromeDescendant_shouldCheckNegativeOneDigitNumber() {
        // Arrange
        int negativeOneDigitNum = -5;

        // Act
        boolean result1 = isPalindromeDescendant(negativeOneDigitNum);

        // Assert
        assertThat(result1).isFalse();
        // 5 < 10 nonPalindrome
    }

}
