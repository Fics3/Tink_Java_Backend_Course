
package edu.hw9.Task3;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class DeadEndFillerThreads implements Solver {

    private static final int[] DIRECTION_X = {0, 1, 0, -1};
    private static final int[] DIRECTION_Y = {-1, 0, 1, 0};
    private final int[] directions = {0, 1, 2, 3};

    @Override
    public boolean solve(Maze maze, Coordinate start, Coordinate end) {
        try (ForkJoinPool forkJoinPool = new ForkJoinPool()) {
            return forkJoinPool.invoke(new FillDeadEndsTask(maze, start, end, directions));
        }
    }

    private static class FillDeadEndsTask extends RecursiveTask<Boolean> {
        private final Maze maze;
        private final Coordinate current;
        private final Coordinate end;
        private final int[] directions;

        FillDeadEndsTask(Maze maze, Coordinate current, Coordinate end, int[] directions) {
            this.maze = maze;
            this.current = current;
            this.end = end;
            this.directions = directions;
        }

        @Override
        protected Boolean compute() {
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
                    FillDeadEndsTask subtask = new FillDeadEndsTask(maze, new Coordinate(newX, newY), end, directions);
                    subtask.fork();
                    if (subtask.join()) {
                        return true;
                    }
                }
            }

            maze.setCell(x, y, Cell.WALL);
            return false;
        }
    }

}


