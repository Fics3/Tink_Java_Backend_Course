package edu.hw2.Task3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

public class PopularCommandExecutorTest {

    @Test
    @DisplayName("Test that throw exception by using all attempts")
    void updatePackages_shouldThrowExceptionAfterUseAllAttempts() {
        // Arrange
        int maxAttempts = 2;
        FaultyConnectionManager faultyConnectionManager = new FaultyConnectionManager();
        PopularCommandExecutor popularCommandExecutor =
            new PopularCommandExecutor(faultyConnectionManager, maxAttempts);

        // Act
        for (int i = 0; i < 10; i++) {
            try {
                popularCommandExecutor.updatePackages();
            } catch (Exception e) {
                // Assert
                assertThat(e.getMessage()).contains("Failed to execute command after " + maxAttempts + " attempts");
            }
        }
    }

    @Test
    @DisplayName("Test that can't use all attempts and throw exceptions")
    void updatePackages_shouldNotThrowExceptionAfterUseAllAttempts() {
        // Arrange
        int maxAttempts = 2;
        double zeroChanceOfFaultyException = 0;
        DefaultConnectionManager defaultConnectionManager = new DefaultConnectionManager(zeroChanceOfFaultyException);
        PopularCommandExecutor popularCommandExecutor =
            new PopularCommandExecutor(defaultConnectionManager, maxAttempts);

        // Act
        for (int i = 0; i < 10; i++) {
            assertThatCode(popularCommandExecutor::updatePackages).doesNotThrowAnyException();
        }
    }
}
