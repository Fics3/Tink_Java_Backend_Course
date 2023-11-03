package edu.hw5.Task3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.assertj.core.api.Assertions.assertThat;

public class YearMonthDayFormatterTest {

    @Test
    @DisplayName("Test with correct format input")
    void parse_shouldReturnParsedOptional() {
        //Arrange
        ChainDateFormatter formatter = new YearMonthDayFormatter();
        LocalDate expected = LocalDate.of(2022, 10, 15);

        //Act
        var result = formatter.parse("2022-10-15");
        //Assert
        assertThat(result.isPresent()).isTrue();
        assertThat(result.get()).isEqualTo(expected);

    }

    @Test
    @DisplayName("Test with incorrect format input and no link")
    void parse_shouldHandleNotCorrectFormat() {
        //Arrange
        ChainDateFormatter formatter = new YearMonthDayFormatter();

        //Act
        var result = formatter.parse("2022/12/15");

        //Assert
        assertThat(result.isPresent()).isFalse();

    }

}
