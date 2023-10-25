package edu.project1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.project1.Dictionary.getRandomWord;
import static org.assertj.core.api.Assertions.assertThat;

public class DictionaryTest {
    @Test
    @DisplayName("Test for check that dictionary returns not null word")
    public void getRandomWord_shouldReturnNotNullWord() {
        // Arrange and Act
        String randomWord = getRandomWord();

        // Assert
        assertThat(randomWord).isNotNull();
    }

}
