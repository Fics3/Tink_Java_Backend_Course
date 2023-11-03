package edu.hw5.Task3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.time.LocalDate;
import static org.assertj.core.api.Assertions.assertThat;

public class DaysAgoFormatterTest {

    @ParameterizedTest
    @DisplayName("Test with correct format input")
    @CsvSource({"20 days ago,20", "1 day ago,1"})
    void parse_shouldReturnParsedOptional(String input, int subtrahend) {
        //Arrange
        ChainDateFormatter formatter = new DaysAgoFormatter();
        LocalDate expected = LocalDate.now().minusDays(subtrahend);

        //Act
        var result = formatter.parse(input);

        //Assert
        assertThat(result.isPresent()).isTrue();
        assertThat(result.get()).isEqualTo(expected);

    }

    @Test
    @DisplayName("Test with incorrect format input and no link")
    void parse_shouldHandleNotCorrectFormat() {
        //Arrange
        ChainDateFormatter formatter = new DaysAgoFormatter();

        //Act
        var result = formatter.parse("22 days");

        //Assert
        assertThat(result.isPresent()).isFalse();

    }
}
