package edu.project2.Maze;

import lombok.extern.log4j.Log4j2;

@Log4j2
public final class MazePrinter {

    private static final char WALL_CHAR = '█';
    private static final char EMPTY_CHAR = ' ';
    private static final char WAY_CHAR = '*';

    private MazePrinter() {

    }

    public static void prettyPrint(Maze maze) {
        StringBuilder mazeState = new StringBuilder();
        mazeState.append("\n");

        for (int i = 0; i < maze.getHeight(); i++) {
            for (int j = 0; j < maze.getWidth(); j++) {
                Cell cell = maze.getCell(i, j);
                char cellSymbol;

                if (cell == Cell.WALL) {
                    cellSymbol = WALL_CHAR;
                } else if (cell == Cell.WAY) {
                    cellSymbol = WAY_CHAR;
                } else {
                    cellSymbol = EMPTY_CHAR;
                }
                mazeState.append(cellSymbol);
            }
            mazeState.append('\n');
        }

        log.info(mazeState.toString());
    }
}
