package edu.project2.Maze.MazeGenerators;

import edu.project2.Maze.Cell;
import edu.project2.Maze.Coordinate;
import edu.project2.Maze.Maze;
import java.util.Random;
import java.util.Stack;

public class DFSGenMaze implements MazeGenerator {

    private static final int[] DIRECTION_X = {0, 1, 0, -1};
    private static final int[] DIRECTION_Y = {-1, 0, 1, 0};
    private final int[] directions = {0, 1, 2, 3};
    private final Random random;
    private final Stack<Coordinate> stack;

    public DFSGenMaze() {
        random = new Random();
        stack = new Stack<>();
    }

    @Override
    public Maze generate(int width, int height) {
        if (width < 1 || height < 1) {
            throw new IllegalArgumentException("Negative parameters");
        }
        Maze maze = new Maze(height, width);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                maze.setCell(i, j, Cell.WALL);
            }
        }

        int startX = random.nextInt(width);
        int startY = random.nextInt(height);
        Coordinate startCoordinate = new Coordinate(startX, startY);

        generateMaze(startCoordinate, maze);

        return maze;
    }

    private void generateMaze(Coordinate startCoordinate, Maze maze) {
        stack.push(startCoordinate);
        maze.setCell(startCoordinate.x(), startCoordinate.y(), Cell.EMPTY);

        while (!stack.isEmpty()) {
            Coordinate currentCoordinate = stack.peek();
            boolean found = false;

            shuffleArray(directions);

            for (int randomDir : directions) {
                int newX = currentCoordinate.x() + DIRECTION_X[randomDir] * 2;
                int newY = currentCoordinate.y() + DIRECTION_Y[randomDir] * 2;
                Coordinate newCoordinate = new Coordinate(newX, newY);

                if (maze.isValidCell(newX, newY) && maze.getCell(newX, newY) == Cell.WALL) {
                    maze.setCell(
                        currentCoordinate.x() + DIRECTION_X[randomDir],
                        currentCoordinate.y() + DIRECTION_Y[randomDir],
                        Cell.EMPTY
                    );
                    maze.setCell(newX, newY, Cell.EMPTY);

                    stack.push(newCoordinate);

                    found = true;
                    break;
                }
            }

            if (!found) {
                stack.pop();
            }
        }
    }

    private void shuffleArray(int[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            int temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }
}
