package edu.project2.Maze.MazeGenerators;

import edu.project2.Maze.Cell;
import edu.project2.Maze.Coordinate;
import edu.project2.Maze.Maze;
import java.util.Random;
import java.util.Stack;

public class HuntAndKill implements MazeGenerator {

    private static final int[] DIRECTION_X = {0, 1, 0, -1};
    private static final int[] DIRECTION_Y = {-1, 0, 1, 0};
    private final int[] directions = {0, 1, 2, 3};
    private Maze maze;
    private final Random random;
    private Coordinate currentCoordinate;
    private final Stack<Coordinate> stack;

    public HuntAndKill() {
        random = new Random();
        stack = new Stack<>();
    }

    @Override
    public Maze generate(int height, int width) {
        if (width < 1 || height < 1) {
            throw new IllegalArgumentException("Negative parameters");
        }
        maze = new Maze(height, width);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                maze.setCell(i, j, Cell.WALL);
            }
        }

        int startX = random.nextInt(height);
        int startY = random.nextInt(width);
        currentCoordinate = new Coordinate(startX, startY);

        huntAndKill(currentCoordinate);

        return maze;
    }

    private void huntAndKill(Coordinate startCoordinate) {
        currentCoordinate = startCoordinate;

        boolean done = false;

        while (!done) {
            maze.setCell(currentCoordinate.x(), currentCoordinate.y(), Cell.EMPTY);

            shuffleArray(directions);

            boolean found = hunt();

            if (!found) {
                done = true;

                while (!stack.isEmpty()) {
                    currentCoordinate = stack.pop();

                    shuffleArray(directions);

                    for (int neighborDir : directions) {
                        int neighbourX = currentCoordinate.x() + DIRECTION_X[neighborDir];
                        int neighbourY = currentCoordinate.y() + DIRECTION_Y[neighborDir];
                        Coordinate neighbor = new Coordinate(neighbourX, neighbourY);

                        if (maze.isValidCell(neighbourX, neighbourY)
                            && maze.getCell(neighbourX, neighbourY) == Cell.EMPTY) {
                            done = false;
                            break;
                        }
                    }

                    if (!done) {
                        break;
                    }
                }
            }
        }
    }

    private boolean hunt() {
        for (int direction : directions) {
            int nextX = currentCoordinate.x() + DIRECTION_X[direction] * 2;
            int nextY = currentCoordinate.y() + DIRECTION_Y[direction] * 2;
            Coordinate nextCoordinate = new Coordinate(nextX, nextY);

            if (maze.isValidCell(nextX, nextY)) {
                if (maze.getCell(nextX, nextY) == Cell.WALL) {
                    maze.setCell(
                        currentCoordinate.x() + DIRECTION_X[direction],
                        currentCoordinate.y() + DIRECTION_Y[direction],
                        Cell.EMPTY
                    );
                    stack.push(currentCoordinate);
                    currentCoordinate = nextCoordinate;
                    return true;
                }
            }
        }
        return false;
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
