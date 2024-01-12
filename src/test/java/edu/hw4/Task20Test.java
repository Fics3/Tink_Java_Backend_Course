package edu.hw4;

import edu.hw4.Task19.ValidationError;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw4.Task20.convertErrorsToStr;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task20Test {

    @Test
    @DisplayName("Test for converting errors to a concatenated string")
    void convertErrorsToStr_shouldConvertErrorsToString() {
        // Arrange
        Map<String, Set<ValidationError>> errorsMap = Map.of(
            "Cat1", Set.of(ValidationError.NEG_AGE),
            "Dog1", Set.of(),
            "Fish1", Set.of(ValidationError.NEG_HEIGHT),
            "", Set.of(ValidationError.NAME_EMPTY)
        );

        // Expected result
        Map<String, String> expectedErrorsToString = new HashMap<>();
        expectedErrorsToString.put("Cat1", "Negative age");
        expectedErrorsToString.put("Dog1", "");
        expectedErrorsToString.put("Fish1", "Negative height");
        expectedErrorsToString.put("", "Name empty or null");

        // Act
        var result = convertErrorsToStr(errorsMap);

        // Assert
        assertThat(result).isEqualTo(expectedErrorsToString);
    }

    @Test
    @DisplayName("Test for converting empty errors map")
    void convertErrorsToStr_shouldHandleEmptyErrorsMap() {
        // Arrange
        Map<String, Set<ValidationError>> errorsMap = Map.of();

        // Expected result should also be an empty map
        Map<String, String> expectedErrorsToString = Map.of();

        // Act
        var result = convertErrorsToStr(errorsMap);

        // Assert
        assertThat(result).isEqualTo(expectedErrorsToString);
    }
}
