package edu.project2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GeneratorTest {

    @Test
    public void testMazeCellTypes() {
        int width = 10;
        int height = 10;
        Maze maze = Generator.generateMaze(width, height);

        assertEquals(height, maze.height());
        assertEquals(width, maze.width());

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                Cell.Type cellType = maze.grid()[row][col].getType();
                assertTrue(cellType == Cell.Type.PASSAGE || cellType == Cell.Type.WALL);
            }
        }
    }
}
