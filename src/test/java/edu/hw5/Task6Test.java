package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static edu.hw5.Task6.isSubsequence;
import static org.assertj.core.api.Assertions.assertThat;

public class Task6Test {

    @ParameterizedTest
    @DisplayName("Test for valid cases")
    @CsvSource({
        "Hello, Hel",         // 'Hel' is a subsequence of 'Hello'
        "OpenAI, pen",        // 'pen' is a subsequence of 'OpenAI'
        "123456, 23 "          // '23' is a subsequence of '123456'
    })
    public void isSubsequence_shouldReturnTrueIfSubstringIsSubsequence(String mainSequence, String substring) {
        // Act
        boolean result = isSubsequence(mainSequence, substring);

        // Assert
        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @DisplayName("Test for invalid cases")
    @CsvSource({
        "Test, ttt",         // 'ttt' is not a subsequence of 'Test'
        "Hello, Bye"         // 'Bye' is not a subsequence of 'Hello'
    })
    public void isSubsequence_shouldReturnFalseIfSubstringIsNotSubsequence(String mainSequence, String substring) {
        // Act
        boolean result = isSubsequence(mainSequence, substring);

        // Assert
        assertThat(result).isFalse();
    }
}
