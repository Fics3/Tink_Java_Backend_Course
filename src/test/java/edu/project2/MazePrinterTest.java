package edu.project2;

import edu.project2.Maze.Cell;
import edu.project2.Maze.Maze;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.project2.Maze.MazePrinter.prettyPrint;
import static org.assertj.core.api.Assertions.assertThat;

@Log4j2
public class MazePrinterTest {
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

    //Logger clean
    @AfterEach
    void tearDown() {
        LoggerContext.getContext(false).getRootLogger().removeAppender(mockAppender);
    }

    @Test
    @DisplayName("Print empty maze")
    void prettyPrint_shouldPrintEmptyMaze() {
        // Arrange
        Maze maze = new Maze(3, 3);

        // Act
        prettyPrint(maze);

        // Assert
        String expectedOutput =
            "\n" +
                "   \n" +
                "   \n" +
                "   \n" +
                "\n";

        String logOutput = getLogOutput();
        assertThat(logOutput).isEqualTo(expectedOutput);
    }

    @Test
    @DisplayName("Print maze with only walls")
    void prettyPrint_shouldPrintFullWallsMaze() {
        // Arrange
        Maze maze = new Maze(3, 3);
        for (int i = 0; i < maze.getHeight(); i++) {
            for (int j = 0; j < maze.getWidth(); j++) {
                maze.setCell(i, j, Cell.WALL);
            }
        }

        // Act
        prettyPrint(maze);

        // Assert
        String expectedOutput =
            "\n" +
                "███\n" +
                "███\n" +
                "███\n" +
                "\n";

        String logOutput = getLogOutput();
        assertThat(logOutput).isEqualTo(expectedOutput);
    }

    @Test
    @DisplayName("Print not empty maze")
    void prettyPrint_shouldPrintNotEmptyMaze() {
        // Arrange
        Maze maze = new Maze(5, 5);
        maze.setCell(0, 0, Cell.WALL);
        maze.setCell(1, 1, Cell.WALL);
        maze.setCell(2, 2, Cell.WAY);

        // Act
        prettyPrint(maze);

        // Assert
        String expectedOutput =
            "\n" +
                "█    \n" +
                " █   \n" +
                "  *  \n" +
                "     \n" +
                "     \n" +
                "\n";

        String logOutput = getLogOutput();
        assertThat(logOutput).isEqualTo(expectedOutput);
    }

    private void logOutput(String logMessage) {
        logOutput.append(logMessage).append(System.lineSeparator());
    }

    // Метод для получения фактического вывода из мок-аппендера
    private String getLogOutput() {
        return logOutput.toString();
    }
}
