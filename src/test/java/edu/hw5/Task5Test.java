package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;

public class Task5Test {

    @ParameterizedTest
    @DisplayName("Test for valid license")
    @CsvSource({
        "А123БВ45",
        "А123БВ451"
    })
    public void isValidLicensePlate_shouldReturnTrueIfLicenseInCorrectFormat(String licensePlate) {
        // Act
        boolean result = Task5.isValidLicensePlate(licensePlate);

        // Assert
        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @DisplayName("Test for invalid license")
    @CsvSource({
        "AB123CD45",
        "А12БВ45"
    })
    public void isValidLicensePlate_shouldReturnFalseIfLicenseInIncorrectFormat(String licensePlate) {
        // Act
        boolean result = Task5.isValidLicensePlate(licensePlate);

        // Assert
        assertThat(result).isFalse();
    }
}
