package edu.project2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public final class Solver {
    private Solver() {
    }

    public static List<Coordinate> solveMaze(Maze maze, Coordinate start, Coordinate end) {
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        boolean[][] visited = new boolean[maze.height()][maze.width()];

        Queue<Cell> queue = new LinkedList<>();
        queue.add(new Cell(start.col(), start.row()));

        while (!queue.isEmpty()) {
            Cell current = queue.poll();
            int x = current.getCol();
            int y = current.getRow();

            if (x == end.col() && y == end.row()) {
                return createFullPath(current);
            }

            for (int[] direction : directions) {
                int newX = x + direction[0];
                int newY = y + direction[1];

                if (newX >= 0 && newX < maze.width() && newY >= 0 && newY < maze.height()
                    && maze.grid()[newY][newX].getType().equals(Cell.Type.PASSAGE) && !visited[newY][newX]) {
                    Cell nextCell = new Cell(newX, newY);
                    nextCell.setPrevious(current);
                    queue.add(nextCell);
                    visited[newY][newX] = true;
                }
            }
        }

        return null;
    }

    private static List<Coordinate> createFullPath(Cell end) {
        Cell current = end;
        List<Coordinate> path = new ArrayList<>();

        while (current != null) {
            path.add(new Coordinate(current.getCol(), current.getRow()));
            current = current.getPrevious();
        }
        Collections.reverse(path);
        return path;
    }
}
