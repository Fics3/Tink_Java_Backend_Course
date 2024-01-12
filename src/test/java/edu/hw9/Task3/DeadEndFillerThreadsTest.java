package edu.hw9.Task3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import static org.assertj.core.api.Assertions.assertThat;

public class DeadEndFillerThreadsTest {

    @RepeatedTest(10)
    @DisplayName("solve - Possible maze")
    void solve_SolveMaze_PossibleMaze() {
        // Arrange
        DeadEndFillerThreads deadEndFillerThreads = new DeadEndFillerThreads();

        Maze maze = createKnownMaze();

        Coordinate start = new Coordinate(1, 1);
        Coordinate end = new Coordinate(2, 3);
        // Act
        boolean result = deadEndFillerThreads.solve(maze, start, end);

        // Assert
        assertThat(result).isTrue();
    }

    @RepeatedTest(10)
    @DisplayName("solve - Impossible maze")
    void solve_SolveMaze_ImpossibleMaze() {
        // Arrange
        DeadEndFillerThreads deadEndFillerThreads = new DeadEndFillerThreads();

        Maze maze = createBlockedMaze();

        Coordinate start = new Coordinate(1, 1);
        Coordinate end = new Coordinate(2, 3);
        // Act
        boolean result = deadEndFillerThreads.solve(maze, start, end);

        // Assert
        assertThat(result).isFalse();
    }

    @RepeatedTest(10)
    @DisplayName("solve - End in")
    void solve_HandelInput_EndInWall() {
        // Arrange
        DeadEndFillerThreads deadEndFillerThreads = new DeadEndFillerThreads();

        Maze maze = createKnownMaze();

        Coordinate start = new Coordinate(1, 1);
        Coordinate end = new Coordinate(4, 4);
        // Act
        boolean result = deadEndFillerThreads.solve(maze, start, end);

        // Assert
        assertThat(result).isFalse();
    }

    private Maze createKnownMaze() {
        Maze maze = new Maze(5, 5);
        maze.setCell(0, 0, Cell.WALL);
        maze.setCell(0, 1, Cell.WALL);
        maze.setCell(0, 2, Cell.WALL);
        maze.setCell(0, 3, Cell.WALL);
        maze.setCell(0, 4, Cell.WALL);

        maze.setCell(1, 0, Cell.WALL);
        maze.setCell(1, 1, Cell.EMPTY);
        maze.setCell(1, 2, Cell.EMPTY);
        maze.setCell(1, 3, Cell.WALL);
        maze.setCell(1, 4, Cell.WALL);

        maze.setCell(2, 0, Cell.WALL);
        maze.setCell(2, 1, Cell.WALL);
        maze.setCell(2, 2, Cell.EMPTY);
        maze.setCell(2, 3, Cell.EMPTY);
        maze.setCell(2, 4, Cell.WALL);

        maze.setCell(3, 0, Cell.WALL);
        maze.setCell(3, 1, Cell.WALL);
        maze.setCell(3, 2, Cell.WALL);
        maze.setCell(3, 3, Cell.WALL);
        maze.setCell(3, 4, Cell.WALL);

        maze.setCell(4, 0, Cell.WALL);
        maze.setCell(4, 1, Cell.WALL);
        maze.setCell(4, 2, Cell.WALL);
        maze.setCell(4, 3, Cell.WALL);
        maze.setCell(4, 4, Cell.WALL);
        return maze;
    }
    // █████
    // █  ██
    // ██  █
    // █████
    // █████

    private Maze createBlockedMaze() {
        Maze maze = new Maze(10, 10);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                maze.setCell(i, j, Cell.WALL);
            }
        }
        return maze;
    }
    //Full blocked maze
}
