package edu.project2;

import edu.project2.Maze.Cell;
import edu.project2.Maze.Coordinate;
import edu.project2.Maze.Maze;
import edu.project2.Maze.MazeSolvers.DeadEndFiller;
import edu.project2.Maze.MazeSolvers.ShortestPathFinder;
import edu.project2.Maze.MazeSolvers.Solver;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class SolverTest {

    static Arguments[] solvers() {
        return new Arguments[] {
            Arguments.of(new DeadEndFiller()),
            Arguments.of(new ShortestPathFinder())
        };
    }

    @ParameterizedTest
    @DisplayName("Test for maze with path to the end")
    @MethodSource("solvers")
    public void solve_shouldFindPathInMaze(Solver solver) {
        // Arrange
        Maze maze = createKnownMaze();

        Coordinate start = new Coordinate(1, 1);
        Coordinate end = new Coordinate(2, 3);
        // Act
        boolean result = solver.solve(maze, start, end);

        // Assert
        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @DisplayName("Test for maze without path to the end")
    @MethodSource("solvers")
    public void solve_shouldNotFindPathInBlockedMaze(Solver solver) {
        // Arrange
        Maze maze = createBlockedMaze();

        Coordinate start = new Coordinate(0, 1);
        Coordinate end = new Coordinate(9, 8);

        // Act
        boolean result = solver.solve(maze, start, end);

        // Assert
        assertThat(result).isFalse();
    }

    @ParameterizedTest
    @DisplayName("Test for start in wall")
    @MethodSource("solvers")
    public void solve_shouldHandleInputStartInWall(Solver solver) {
        // Arrange
        Maze maze = createKnownMaze();

        Coordinate start = new Coordinate(0, 0);
        Coordinate end = new Coordinate(2, 3);

        // Act
        boolean result = solver.solve(maze, start, end);

        // Assert
        assertThat(result).isFalse();
    }

    @ParameterizedTest
    @DisplayName("Test for end in wall")
    @MethodSource("solvers")
    public void solve_shouldHandleInputEndInWall(Solver solver) {
        // Arrange
        Maze maze = createKnownMaze();

        Coordinate start = new Coordinate(1, 1);
        Coordinate end = new Coordinate(4, 4);

        // Act
        boolean result = solver.solve(maze, start, end);

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
