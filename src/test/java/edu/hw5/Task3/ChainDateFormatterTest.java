package edu.hw5.Task3;

import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;

public class ChainDateFormatterTest {
    ChainDateFormatter chainDateFormatter;

    @BeforeEach
    void setUp() {
        //First class
        chainDateFormatter = new DaysAgoFormatter();

        //Create new classes
        RelativeDayFormatter relativeDayFormatter = new RelativeDayFormatter();
        YearMonthDayFormatter yearMonthDayFormatter = new YearMonthDayFormatter();
        DayMonthYearFormatter dayMonthYearFormatter = new DayMonthYearFormatter();

        //Linking
        chainDateFormatter.linkWith(relativeDayFormatter);
        relativeDayFormatter.linkWith(yearMonthDayFormatter);
        yearMonthDayFormatter.linkWith(dayMonthYearFormatter);
    }

    @ParameterizedTest
    @DisplayName("Test for days ago formatter")
    @CsvSource({"20 days ago,20", "1 day ago,1"})
    void parse_shouldWorkWithDaysAgoFormat(String input, int subtrahend) {
        //Arrange
        LocalDate expected = LocalDate.now().minusDays(subtrahend);

        //Act
        var result = chainDateFormatter.parse(input);

        //Assert
        assertThat(result.isPresent()).isTrue();
        assertThat(result.get()).isEqualTo(expected);

    }

    @ParameterizedTest
    @DisplayName("Test for relative formatter")
    @CsvSource({"yesterday,-1", "today,0", "tomorrow,1"})
    void parse_shouldWorkWithRelativeFormat(String input, int summand) {
        //Arrange
        ChainDateFormatter formatter = new RelativeDayFormatter();
        LocalDate expected = LocalDate.now().plusDays(summand);

        //Act
        var result = formatter.parse(input);

        //Assert
        assertThat(result.isPresent()).isTrue();
        assertThat(result.get()).isEqualTo(expected);

    }

    @Test
    @DisplayName("Test for yyyy-MM-dd formatter")
    void parse_shouldWorkWithYearMonthDayFormat() {
        //Arrange
        ChainDateFormatter formatter = new YearMonthDayFormatter();
        LocalDate expected = LocalDate.of(2022, 10, 21);

        //Act
        var result = formatter.parse("2022-10-21");
        //Assert
        assertThat(result.isPresent()).isTrue();
        assertThat(result.get()).isEqualTo(expected);

    }

    @Test
    @DisplayName("Test with dd/MM/yyyy")
    void parse_shouldWorkWithDayMonthYearFormatter() {
        //Arrange
        ChainDateFormatter formatter = new DayMonthYearFormatter();
        LocalDate expected = LocalDate.of(2022, 10, 15);

        //Act
        var result = formatter.parse("15/10/2022");

        //Assert
        assertThat(result.isPresent()).isTrue();
        assertThat(result.get()).isEqualTo(expected);

    }

    @Test
    @DisplayName("Test with incorrect format input and all links")
    void parse_shouldHandleNotCorrectFormat() {
        //Arrange
        ChainDateFormatter formatter = new DayMonthYearFormatter();

        //Act
        var result = formatter.parse("15-10");

        //Assert
        assertThat(result.isPresent()).isFalse();

    }

}
