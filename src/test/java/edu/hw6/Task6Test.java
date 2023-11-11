package edu.hw6;

import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw6.Task6.scanPorts;
import static org.assertj.core.api.Assertions.assertThat;

@Log4j2
class Task6Test {

    private final StringBuilder logOutput = new StringBuilder();

    private Appender mockAppender;

    @BeforeEach
    void setUp() {
        LoggerContext ctx = LoggerContext.getContext(false);

        mockAppender = new AbstractAppender("MockAppender", null, null) {
            @Override
            public void append(LogEvent event) {
                logOutput(event.getMessage().getFormattedMessage());
            }
        };
        mockAppender.start();
        ctx.getRootLogger().addAppender(mockAppender);
    }

    @AfterEach
    void tearDown() {
        LoggerContext.getContext(false).getRootLogger().removeAppender(mockAppender);
    }

    @Test
    @DisplayName("Test for non-empty log output")
    void scanPorts_shouldCheckLogNonEmptyLogOutput() {

        // Act
        scanPorts();

        //Assert
        assertThat(logOutput).isNotEmpty();
    }

    @Test
    @DisplayName("Test that output has 3 start column")
    void scanPorts_shouldCheckDefaultColumns() {
        //Arrange
        String expected = "\nProtocol Port Service\n";

        // Act
        scanPorts();

        //Assert
        assertThat(logOutput).startsWith(expected);
    }

    private void logOutput(String logMessage) {
        logOutput.append(logMessage).append(System.lineSeparator());
    }

}
