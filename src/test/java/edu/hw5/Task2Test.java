package edu.hw5;

import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static edu.hw5.Task2.findAllFridays13th;
import static edu.hw5.Task2.findNext13thFriday;
import static org.assertj.core.api.Assertions.assertThat;

public class Task2Test {

    @ParameterizedTest
    @DisplayName("Test for finding all fridays 13th in year")
    @CsvSource({"2024,2", "1925,3"})
    void findAllFridays13th_shouldFindAllFridays13thInYear(int year, int expectedSize) {
        //Act
        var result = findAllFridays13th(year);

        //Assert
        assertThat(result.size()).isEqualTo(expectedSize);
    }

    @Test
    @DisplayName("Test for negative year")
    void findAllFridays13th_shouldThrowExceptionNegativeInput() {
        //Arrange
        int negYear = -2024;

        //Act
        try {
            findAllFridays13th(negYear);
        } catch (Exception e) {
            assertThat(e.getMessage()).contains("year can't be negative");
        }
    }

    @ParameterizedTest
    @DisplayName("Test for find next friday 13th")
    @CsvSource({"2024,01,11,2024-09-13", "1925,02,14,1925-03-13"})
    void findNext13thFriday_shouldFindNextFriday13th(int year, int month, int day, LocalDate expected) {
        //Act
        var result = findNext13thFriday(LocalDate.of(year, month, day));

        //Assert
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("Test for find next friday 13th when start date is friday 13th")
    @CsvSource({"2024,9,13,2024-12-13", "1925,03,13,1925-11-13"})
    void findNext13thFriday_shouldFindNextFriday13thIfStartFriday13th(
        int year,
        int month,
        int day,
        LocalDate expected
    ) {
        //Act
        var result = findNext13thFriday(LocalDate.of(year, month, day));

        //Assert
        assertThat(result).isEqualTo(expected);
    }

}
