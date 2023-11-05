package edu.project2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public final class Generator {
    private Generator() {
    }

    public enum Direction {
        LEFT, RIGHT, UP, DOWN
    }

    private static Cell[][] createArray(int width, int height) {
        return IntStream.range(0, height)
            .mapToObj(row -> IntStream.range(0, width)
                .mapToObj(col -> new Cell(row, col))
                .toArray(Cell[]::new))
            .toArray(Cell[][]::new);
    }

    public static Maze generateMaze(int width, int height) {
        Cell[][] grid = createArray(width, height);
        Random random = new Random();

        int startX = random.nextInt(width);
        int startY = random.nextInt(height);

        grid[random.nextInt(width)][random.nextInt(height)].setType(Cell.Type.PASSAGE);

        List<Coordinate> stack = new ArrayList<>();
        stack.add(new Coordinate(startX, startY));

        while (!stack.isEmpty()) {
            int col = stack.get(stack.size() - 1).col();
            int row = stack.get(stack.size() - 1).row();

            List<Direction> directions = new ArrayList<>();
            if (col > 1 && grid[row][col - 2].getType().equals(Cell.Type.WALL)) {
                directions.add(Direction.LEFT);
            }
            if (col < width - 2 && grid[row][col + 2].getType().equals(Cell.Type.WALL)) {
                directions.add(Direction.RIGHT);
            }
            if (row > 1 && grid[row - 2][col].getType().equals(Cell.Type.WALL)) {
                directions.add(Direction.UP);
            }
            if (row < height - 2 && grid[row + 2][col].getType().equals(Cell.Type.WALL)) {
                directions.add(Direction.DOWN);
            }

            if (!directions.isEmpty()) {
                Direction direction = directions.get(random.nextInt(directions.size()));

                int newCol = col;
                int newRow = row;

                if (direction.equals(Direction.LEFT)) {
                    newCol -= 2;
                } else if (direction.equals(Direction.RIGHT)) {
                    newCol += 2;
                } else if (direction.equals(Direction.UP)) {
                    newRow -= 2;
                } else if (direction.equals(Direction.DOWN)) {
                    newRow += 2;
                }

                grid[newRow][newCol].setType(Cell.Type.PASSAGE);

                int wallCol = (col + newCol) / 2;
                int wallRow = (row + newRow) / 2;
                grid[wallRow][wallCol].setType(Cell.Type.PASSAGE);

                stack.add(new Coordinate(newCol, newRow));
            } else {
                stack.remove(stack.size() - 1);
            }
        }
        return new Maze(height, width, grid);
    }
}
