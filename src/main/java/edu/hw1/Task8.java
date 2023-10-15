package edu.hw1;

final class Task8 {

    private static final int[][] POSSIBLE_MOVES = {
        {2, +1}, {2, -1},
        {-2, +1}, {-2, -1},
        {1, 2}, {1, -2},
        {-1, +2}, {-1, -2}
    };

    private Task8() {

    }

    private static boolean isAvailable(int[][] board, int x, int y) {
        return (x >= 0) && (y >= 0) && (x < board.length) && (y < board.length);
    }

    public static boolean knightBoardCapture(int[][] board) {

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 1) {
                    for (int[] move : POSSIBLE_MOVES) {
                        int x = i + move[0];
                        int y = j + move[1];
                        if (isAvailable(board, x, y) && board[x][y] == 1) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

}
