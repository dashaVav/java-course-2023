package edu.project2;

import java.util.ArrayList;
import java.util.List;

public final class MazeUtilsTest {
    static Maze createManualMaze() {
        int width = 5;
        int height = 5;
        Cell[][] grid = new Cell[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Cell cellWall = new Cell(i, j);
                cellWall.setType(Cell.Type.WALL);
                grid[i][j] = cellWall;
            }
        }

        for (int i = 1; i < 4; i++) {
            Cell cellPassage = new Cell(i, 1);
            cellPassage.setType(Cell.Type.PASSAGE);
            grid[i][1] = cellPassage;
        }

        for (int i = 1; i < 4; i++) {
            Cell cellPassage = new Cell(3, i);
            cellPassage.setType(Cell.Type.PASSAGE);
            grid[i][3] = cellPassage;
        }

        Cell cellPassage = new Cell(3, 2);
        cellPassage.setType(Cell.Type.PASSAGE);
        grid[3][2] = cellPassage;

        return new Maze(height, width, grid);
    }

    static List<Coordinate> createPathManual() {
        List<Coordinate> coordinates = new ArrayList<>();
        coordinates.add(new Coordinate(1, 1));
        coordinates.add(new Coordinate(1, 2));
        coordinates.add(new Coordinate(1, 3));
        coordinates.add(new Coordinate(2, 3));
        coordinates.add(new Coordinate(3, 3));
        coordinates.add(new Coordinate(3, 2));
        coordinates.add(new Coordinate(3, 1));
        return coordinates;
    }
}
