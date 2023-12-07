package edu.hw5.Task3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.time.LocalDate;
import static org.assertj.core.api.Assertions.assertThat;

public class RelativeDayFormatterTest {

    @ParameterizedTest
    @DisplayName("Test with correct format input")
    @CsvSource({"yesterday,-1", "today,0", "tomorrow,1"})
    void parse_shouldReturnParsedOptional(String input, int summand) {
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
    @DisplayName("Test with incorrect format input and no link")
    void parse_shouldHandleNotCorrectFormat() {
        //Arrange
        ChainDateFormatter formatter = new RelativeDayFormatter();

        //Act
        var result = formatter.parse("tday");

        //Assert
        assertThat(result.isPresent()).isFalse();

    }
}
