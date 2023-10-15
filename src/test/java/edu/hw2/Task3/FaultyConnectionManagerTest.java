package edu.hw2.Task3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class FaultyConnectionManagerTest {

    @Test
    @DisplayName("Test for checking that always returns FaultyConnection")
    void getConnection_shouldAlwaysReturnFaultyConnection() {
        // Arrange
        ConnectionManager connectionManager = new FaultyConnectionManager();

        for (int i = 0; i < 10; i++) {
            // Act
            Connection connection = connectionManager.getConnection();

            //Assert
            assertThat(connection).isInstanceOf(FaultyConnection.class);
        }
    }
}
