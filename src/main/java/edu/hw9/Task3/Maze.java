package edu.hw9.Task3;

import lombok.Getter;

public class Maze {
    @Getter
    private final int height;
    @Getter private final int width;
    private final Cell[][] maze;

    public Maze(int height, int width) {
        this.height = height;
        this.width = width;
        maze = new Cell[height][width];
    }

    public boolean isValidCell(int x, int y) {
        return x >= 0 && x < height - 1 && y >= 0 && y < width - 1;
    }

    public void setCell(int x, int y, Cell value) {
        maze[x][y] = value;
    }

    public Cell getCell(int x, int y) {
        return maze[x][y];
    }
}
