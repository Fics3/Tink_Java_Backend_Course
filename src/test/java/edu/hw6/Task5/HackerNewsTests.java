package edu.hw6.Task5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw6.Task5.HackerNews.hackerNewsTopStories;
import static edu.hw6.Task5.HackerNews.newsTitle;
import static org.assertj.core.api.Assertions.assertThat;

public class HackerNewsTests {

    @Test
    @DisplayName("Test hackerNewsTopStories")
    void hackerNewsTopStories_shouldReturnNonEmptyArray() {
        // Act
        long[] topStories = hackerNewsTopStories();

        // Assert
        assertThat(topStories).isNotEmpty();
    }

    @Test
    @DisplayName("Test newsTitle with valid id")
    void newsTitle_shouldReturnTitleIfIdIsValid() {
        // Arrange
        long validId = 37570037;

        // Act
        String title = newsTitle(validId);

        // Assert
        assertThat(title).isEqualTo("JDK 21 Release Notes");

    }

    @Test
    @DisplayName("Test newsTitle with invalid id")
    void newsTitle_shouldReturnNullIfIdIsInvalid() {
        // Arrange
        long invalidId = -1; // Assuming -1 is an invalid ID

        // Act
        String title = newsTitle(invalidId);

        // Assert
        assertThat(title).isNull();
    }
}
