package edu.project2.Maze.MazeSolvers;

import edu.project2.Maze.Cell;
import edu.project2.Maze.Coordinate;
import edu.project2.Maze.Maze;

public class DeadEndFiller implements Solver {

    private static final int[] DIRECTION_X = {0, 1, 0, -1};
    private static final int[] DIRECTION_Y = {-1, 0, 1, 0};

    private final int[] directions = {0, 1, 2, 3};

    @Override
    public boolean solve(Maze maze, Coordinate start, Coordinate end) {
        return fillDeadEnds(maze, start, end, directions);
    }

    private boolean fillDeadEnds(Maze maze, Coordinate current, Coordinate end, int[] directions) {
        int x = current.x();
        int y = current.y();

        if (current.equals(end)) {
            return true;
        }

        maze.setCell(x, y, Cell.WAY);

        for (int direction : directions) {
            int newX = x + DIRECTION_X[direction];
            int newY = y + DIRECTION_Y[direction];

            if (maze.isValidCell(newX, newY) && maze.getCell(newX, newY) == Cell.EMPTY) {
                if (fillDeadEnds(maze, new Coordinate(newX, newY), end, directions)) {
                    return true;
                }
            }
        }
        //fill with wall if not way
        maze.setCell(x, y, Cell.WALL);
        return false;
    }
}
