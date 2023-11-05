package edu.project2;

import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolverTest {
    @Test
    void testSolveMaze() {
        List<Coordinate> path = MazeUtilsTest.createPathManual();
        Maze maze = MazeUtilsTest.createManualMaze();

        List<Coordinate> result = Solver.solveMaze(maze, new Coordinate(1, 1), new Coordinate(3, 1));

        assertEquals(path, result);
    }
}
