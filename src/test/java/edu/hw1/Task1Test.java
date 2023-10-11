package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static edu.hw1.Task1.minutesToSeconds;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {

    @Test
    @DisplayName("Count seconds without minute")
    void minuteToSeconds_shouldCountSecondsWithoutMinute() {
        //arrange
        String secondsWithoutMinuteStr = "00:59";
        //act
        int actual = minutesToSeconds(secondsWithoutMinuteStr);
        //assert
        assertThat(actual).isEqualTo(59);
        //0*60+59
    }

    @Test
    @DisplayName("Count seconds with minutes")
    void minuteToSeconds_shouldCountSecondsWithMinute() {
        //arrange
        String minutesAndSecondsStr = "1231:32";
        //act
        int actual = minutesToSeconds(minutesAndSecondsStr);
        //assert
        assertThat(actual).isEqualTo(73892);
        //(1231*60) = 73860 + 32 = 73892
    }

    @Test
    @DisplayName("Invalid input with excessive seconds")
    void minuteToSeconds_shouldHandleInvalidInputWithExcessiveSeconds() {
        //arrange
        String excessiveSecondsStr = "30:592";
        //act
        int actual = minutesToSeconds(excessiveSecondsStr);
        //assert
        assertThat(actual).isEqualTo(-1);
        //592>=60
    }

    @ParameterizedTest
    @DisplayName("Invalid input with negative input")
    @CsvSource({"30:-2,-1", "-120:01,-1"})
    void minuteToSeconds_shouldHandleInvalidInputWithNegativeSeconds(String negativeStr, int expected) {
        //act
        int actual = minutesToSeconds(negativeStr);
        //assert
        assertThat(actual).isEqualTo(expected);
        //-2 < 0
        //-120 < 0
    }

    @ParameterizedTest
    @DisplayName("Invalid input with char is input")
    @CsvSource({"3420:ac,-1", "df:01,-1"})
    void minuteToSeconds_shouldHandleInvalidInputWithCharInSeconds(String charInStr, int expected) {
        //act
        int actual = minutesToSeconds(charInStr);
        //assert
        assertThat(actual).isEqualTo(expected);
    }

}
