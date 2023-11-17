package edu.project2.Maze.MazeGenerators;

import edu.project2.Maze.Maze;

public interface MazeGenerator {
    Maze generate(int height, int width);
}
