package edu.project3;

import java.time.OffsetDateTime;
import java.time.format.DateTimeParseException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class DateManagerTest {

    @Test
    @DisplayName("Format valid date string should return OffsetDateTime")
    void formatDate_ValidDateString_shouldReturnOffsetDateTime() {
        // Arrange
        String validDate = "10/Nov/2022:12:30:45 +0300";

        // Act
        OffsetDateTime result = DateManager.formatDate(validDate);

        // Assert
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("Format invalid date string should throw DateTimeParseException")
    void formatDate_InvalidDateString_shouldThrowException() {
        // Arrange
        String invalidDate = "InvalidDate";

        // Act and Assert
        //Act
        try {
            DateManager.formatDate(invalidDate);
        } catch (Exception e) {
            assertThat(e.getClass()).isEqualTo(DateTimeParseException.class);
        }
    }

    @Test
    @DisplayName("Check if date is in the future should return true")
    void isDateInRange_DateInFuture_shouldReturnTrue() {
        // Arrange
        OffsetDateTime currentDate = OffsetDateTime.now();
        OffsetDateTime futureDate = currentDate.plusDays(1);

        // Act
        boolean result = DateManager.isDateInRange(futureDate, null, null);

        // Assert
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Check if date is in the future should return true")
    void isDateInRange_DateBeforeStart_shouldReturnFalse() {
        // Arrange
        OffsetDateTime currentDate = OffsetDateTime.now();
        OffsetDateTime startDate = currentDate.plusDays(1);
        OffsetDateTime endDate = currentDate.plusDays(2);
        OffsetDateTime dateBeforeStart = currentDate.minusDays(1);

        // Act
        boolean result = DateManager.isDateInRange(dateBeforeStart, startDate, endDate);

        // Assert
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("Check if date is after end date should return false")
    void isDateInRange_DateAfterEnd_shouldReturnFalse() {
        // Arrange
        OffsetDateTime currentDate = OffsetDateTime.now();
        OffsetDateTime startDate = currentDate.minusDays(2);
        OffsetDateTime endDate = currentDate.minusDays(1);
        OffsetDateTime dateAfterEnd = currentDate.plusDays(343);

        // Act
        boolean result = DateManager.isDateInRange(dateAfterEnd, startDate, endDate);

        // Assert
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("Check if date is in range should return true")
    void isDateInRange_DateInRange_shouldReturnTrue() {
        // Arrange
        OffsetDateTime currentDate = OffsetDateTime.now();
        OffsetDateTime startDate = currentDate.minusDays(1);
        OffsetDateTime endDate = currentDate.plusDays(1);

        // Act
        boolean result = DateManager.isDateInRange(currentDate, startDate, endDate);

        // Assert
        assertThat(result).isTrue();
    }
}
