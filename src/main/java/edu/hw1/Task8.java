package edu.hw1;

import java.util.Objects;

public final class Task8 {
    private Task8() {
    }

    private static final int SIZE_OF_CHESS_BOARD = 8;
    private static final int[] DX = {-2, -2, -1, -1, 1, 1, 2, 2};
    private static final int[] DY = {-1, 1, -2, 2, -2, 2, -1, 1};

    public static boolean isAttacked(int[][] board, int x, int y) {

        for (int i = 0; i < SIZE_OF_CHESS_BOARD; i++) {
            int newX = x + DX[i];
            int newY = y + DY[i];
            if (newX >= 0 && newX < SIZE_OF_CHESS_BOARD && newY >= 0 && newY < SIZE_OF_CHESS_BOARD
                && board[newX][newY] == 1) {
                return true;
            }
        }

        return false;
    }

    private static boolean isWrongSizeOfBoard(int[][] board) {
        if (board.length != SIZE_OF_CHESS_BOARD) {
            return true;
        }
        for (int i = 0; i < SIZE_OF_CHESS_BOARD; i++) {
            if (board[i].length != SIZE_OF_CHESS_BOARD) {
                return true;
            }
        }
        return false;
    }

    public static boolean knightBoardCapture(int[][] board) {
        Objects.requireNonNull(board);

        if (isWrongSizeOfBoard(board)) {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < SIZE_OF_CHESS_BOARD; i++) {
            for (int j = 0; j < SIZE_OF_CHESS_BOARD; j++) {
                if (board[i][j] == 1 && isAttacked(board, i, j)) {
                    return false;
                }
            }
        }
        return true;
    }
}
