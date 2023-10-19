package edu.hw2.Task3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

public class StableConnectionTest {

    @Test
    @DisplayName("Test for not throwing ConnectionException by StableConnection")
    public void execute_checkingThatExceptionNotThrowing() {
        // Arrange
        StableConnection stableConnection = new StableConnection();
        for (int i = 0; i < 10; i++) {
            // Act and assert
            assertThatCode(() -> stableConnection.execute("echo 'Hello, World!'")).doesNotThrowAnyException();
        }

        // Act and assert
        assertThatCode(stableConnection::close).doesNotThrowAnyException();
    }
}

