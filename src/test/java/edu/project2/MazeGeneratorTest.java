package edu.project2;

import edu.project2.Maze.Maze;
import edu.project2.Maze.MazeGenerators.DFSGenMaze;
import edu.project2.Maze.MazeGenerators.HuntAndKill;
import edu.project2.Maze.MazeGenerators.MazeGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;

public class MazeGeneratorTest {

    static Arguments[] generators() {
        return new Arguments[] {
            Arguments.of(new HuntAndKill()),
            Arguments.of(new DFSGenMaze())
        };
    }

    @ParameterizedTest
    @DisplayName("After generate maze size equals give size")
    @MethodSource("generators")
    public void generateMaze_generatedMazeSizeShouldEqualsGivenSize(MazeGenerator mazeGenerator) {
        //Assert
        int width = 10;
        int height = 10;

        // Act
        Maze maze = mazeGenerator.generate(height, width);

        // Assert
        assertThat(maze.getHeight()).isEqualTo(height);
        assertThat(maze.getWidth()).isEqualTo(width);
    }

    @ParameterizedTest
    @DisplayName("Test for negative height input")
    @MethodSource("generators")
    public void generateMaze_shouldThrowExceptionWhenNegativeHeight(MazeGenerator mazeGenerator) {
        //Assert
        int width = 10;
        int height = -10;
        // Act
        try {
            mazeGenerator.generate(height, width);
        } catch (Exception e) {
            assertThat(e.getMessage()).isEqualTo("Negative parameters");
        }
    }

    @ParameterizedTest
    @DisplayName("Test for negative width input")
    @MethodSource("generators")
    public void generateMaze_shouldThrowExceptionWhenNegativeWidth(MazeGenerator mazeGenerator) {
        //Assert
        int width = -10;
        int height = 10;

        // Act
        try {
            mazeGenerator.generate(height, width);
        } catch (Exception e) {
            assertThat(e.getMessage()).isEqualTo("Negative parameters");
        }
    }

}
