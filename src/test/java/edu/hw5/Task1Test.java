package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw5.Task1.calculateSessionDuration;
import static org.assertj.core.api.Assertions.assertThat;

public class Task1Test {

    @Test
    @DisplayName("Test for session in same day")
    void calculateSessionDuration_shouldReturnDurationOfSessionInOneDay() {
        //Arrange
        String session = "2022-03-12, 20:20 - 2022-03-12, 23:50";

        String expected = "3ч30м";

        //Act
        String result = calculateSessionDuration(session);

        //Assert
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("Test for session in next day")
    void calculateSessionDuration_shouldReturnDurationOfSessionInNextDay() {
        //Arrange
        String session = "2022-03-12, 20:20 - 2022-03-13, 23:50";

        String expected = "27ч30м";//3:30 + 24:00

        //Act
        String result = calculateSessionDuration(session);

        //Assert
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("Test for session in next month")
    void calculateSessionDuration_shouldReturnDurationOfSessionInNextMonth() {
        //Arrange
        String session = "2022-03-12, 20:20 - 2022-04-12, 23:50";

        String expected = "747ч30м";//3:30 + 24*31:00

        //Act
        var result = calculateSessionDuration(session);

        //Assert
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("Test for session in next year")
    void calculateSessionDuration_shouldReturnDurationOfSessionInNextYear() {
        //Arrange
        String session = "2022-03-12, 20:20 - 2023-03-12, 23:50";

        String expected = "8763ч30м";//3:30 + 8760 hours in year

        //Act
        var result = calculateSessionDuration(session);

        //Assert
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("Test for session end earlier than start")
    void calculateSessionDuration_shouldHandleInputOfEndEarlyStart() {
        //Arrange
        String session = "2022-03-12, 20:20 - 2022-03-12, 20:00";

        String expected = "0ч0м";//3:30 + 8760 hours in year

        //Act
        var result = calculateSessionDuration(session);

        //Assert
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("Test for session with incorrect date format")
    void calculateSessionDuration_shouldThrowExceptionIfSessionNotInFormat() {
        //Arrange
        String session = "2022,03,12, 20:20 _ 2022,03,12, 20:00";

        //Act
        try {
            calculateSessionDuration(session);
        }
        catch (Exception e) {
            //Assert
            assertThat(e.getMessage()).contains("Incorrect date format");
        }
    }

}
