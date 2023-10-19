package edu.hw2.Task3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

public class FaultyConnectionTest {

    @Test
    @DisplayName("Test of throwing ConnectionException by FaultyConnection")
    void execute_shouldCatchConnectionException() {
        // Arrange
        FaultyConnection faultyConnection = new FaultyConnection();

        for (int i = 0; i < 10; i++) {
            // Act
            try {
                faultyConnection.execute("echo 'Hello, World!'");
            } catch (ConnectionException e) {
                // Assert
                assertThat(e.getMessage()).contains("Failed to execute");
            }
        }
        assertThatCode(faultyConnection::close).doesNotThrowAnyException();
    }

}
