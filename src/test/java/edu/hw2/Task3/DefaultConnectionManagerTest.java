package edu.hw2.Task3;

import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DefaultConnectionManagerTest {

    @Test
    @DisplayName("Test for check able to returning StableConnection")
    public void getConnection_shouldAlwaysReturnStableConnection() {
        // Arrange
        double zeroChanceOfFaultyException = 0;
        DefaultConnectionManager defaultConnectionManager = new DefaultConnectionManager(zeroChanceOfFaultyException);

        // Act and assert
        for (int i = 0; i < 10; i++) {
            // Act and assert
            AssertionsForClassTypes.assertThatCode(() -> defaultConnectionManager.getConnection()
                .execute("echo 'Hello, World!'")).doesNotThrowAnyException();
        }
        AssertionsForClassTypes.assertThatCode(defaultConnectionManager.getConnection()::close)
            .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Test for check able to returning FaultyConnection")
    public void getConnection_shouldAlwaysReturnFaultyConnection() {
        // Arrange
        double maxChanceOfFaultyException = 100;
        DefaultConnectionManager defaultConnectionManager = new DefaultConnectionManager(maxChanceOfFaultyException);

        // Act and assert
        for (int i = 0; i < 10; i++) {
            // Act
            try {
                defaultConnectionManager.getConnection().execute("echo 'Hello, World!'");
            } catch (ConnectionException connectionException) {
                // Assert
                assertThat(connectionException.getMessage()).contains("Failed to execute");
            }
        }
        AssertionsForClassTypes.assertThatCode(defaultConnectionManager.getConnection()::close)
            .doesNotThrowAnyException();
    }
}
