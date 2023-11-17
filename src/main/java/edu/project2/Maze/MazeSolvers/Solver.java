package edu.project2.Maze.MazeSolvers;

import edu.project2.Maze.Coordinate;
import edu.project2.Maze.Maze;

public interface Solver {
    boolean solve(Maze maze, Coordinate start, Coordinate end);
}
