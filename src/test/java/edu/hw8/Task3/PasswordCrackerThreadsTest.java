package edu.hw8.Task3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PasswordCrackerThreadsTest {
    @Test
    @DisplayName("crackPasswords - Valid passwords")
    void crackPasswords_ValidPasswords_SuccessfullyCracked() {
        // Arrange
        PasswordCrackerThreads PasswordCrackerThreads =
            new PasswordCrackerThreads(4, "src/test/resources/hw8/Task3", 2);

        // Act
        PasswordCrackerThreads.crackPasswords();

        // Assert
        assertThat(PasswordCrackerThreads.getCrackedPasswords()).hasSize(2)
            .containsValues("m123", "aaaa");
    }

}
