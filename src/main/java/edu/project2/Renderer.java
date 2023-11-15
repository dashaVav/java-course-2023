package edu.project2;

import java.util.Collections;
import java.util.List;

public final class Renderer {
    private Renderer() {
    }

    public static String render(Maze maze) {
        return render(maze, Collections.emptyList());
    }

    public static String render(Maze maze, List<Coordinate> path) {
        if (path == null) {
            return "No path found";
        }

        markPath(maze, path);

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < maze.height(); i++) {
            for (int j = 0; j < maze.width(); j++) {
                if (maze.grid()[i][j].getType().equals(Cell.Type.WALL)) {
                    result.append("█");
                } else if (maze.grid()[i][j].getType().equals(Cell.Type.WAY)) {
                    result.append("·");
                } else {
                    result.append(" ");
                }
            }
            result.append("\n");
        }
        return result.toString();
    }

    public static void markPath(Maze maze, List<Coordinate> path) {
        for (Coordinate coordinate : path) {
            maze.grid()[coordinate.row()][coordinate.col()].setType(Cell.Type.WAY);
        }
    }
}
