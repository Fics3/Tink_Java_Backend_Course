package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static edu.hw1.Task2.countDigits;
import static org.assertj.core.api.Assertions.assertThat;

public class Task2Test {

    @ParameterizedTest
    @DisplayName("Count digits of numbers")
    @CsvSource({"228, 3", "3,1", "32343,5", Integer.MAX_VALUE + ", 10"})
    void countDigits_shouldCountDigitsOfPositiveNums(int input, int expected) {
        //act
        int actual = countDigits(input);
        //assert
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("Count digits of zero")
    void countDigits_shouldCountDigitOfZero() {
        //arrange
        int zeroNum = 0;
        //act
        int actual = countDigits(zeroNum);
        //assert
        assertThat(actual).isEqualTo(1);

    }

    @ParameterizedTest
    @DisplayName("Count digits of negative number")
    @CsvSource({"-228,3", "-3,1", "-32343,5"})
    void countDigits_shouldCountDigitOfNegativeNumber(int negativeNum, int expected) {
        //act
        int actual = countDigits(negativeNum);
        //assert
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("Count digits of min Integer")
    void countDigits_shouldCountDigitOfMinIntegerNumber() {
        //arrange
        int minInt = Integer.MIN_VALUE;
        //act
        int actual = countDigits(minInt);
        //assert
        assertThat(actual).isEqualTo(10);
    }

}
