package edu.project2.Maze.MazeSolvers;

import edu.project2.Maze.Cell;
import edu.project2.Maze.Coordinate;
import edu.project2.Maze.Maze;
import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathFinder implements Solver {
    private static final int[] DIRECTION_X = {0, 1, 0, -1};
    private static final int[] DIRECTION_Y = {-1, 0, 1, 0};

    @Override public boolean solve(Maze maze, Coordinate start, Coordinate end) {
        Queue<Coordinate> queue = new LinkedList<>();

        boolean[][] visited = new boolean[maze.getHeight()][maze.getWidth()];

        Coordinate[][] previous = new Coordinate[maze.getHeight()][maze.getWidth()];

        queue.add(start);
        visited[start.x()][start.y()] = true;

        while (!queue.isEmpty()) {
            Coordinate current = queue.poll();

            if (current.equals(end)) {
                reconstructPath(maze, previous, start, end);
                return true;
            }

            for (int i = 0; i < DIRECTION_X.length; i++) {
                int newX = current.x() + DIRECTION_X[i];
                int newY = current.y() + DIRECTION_Y[i];

                if (maze.isValidCell(newX, newY) && maze.getCell(newX, newY) == Cell.EMPTY && !visited[newX][newY]) {
                    queue.add(new Coordinate(newX, newY));
                    visited[newX][newY] = true;
                    previous[newX][newY] = current;
                }
            }
        }

        return false;
    }

    private void reconstructPath(Maze maze, Coordinate[][] previous, Coordinate start, Coordinate end) {
        Coordinate current = end;
        while (!current.equals(start)) {
            maze.setCell(current.x(), current.y(), Cell.WAY);
            current = previous[current.x()][current.y()];
        }
    }
}
