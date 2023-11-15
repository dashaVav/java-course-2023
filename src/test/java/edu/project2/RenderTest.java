package edu.project2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RenderTest {

    @Test
    public void testRenderMaze() {
        String expectedMaze =
            """
                █████
                █ █ █
                █ █ █
                █   █
                █████
                """;

        String renderedMaze = Renderer.render(MazeUtilsTest.createManualMaze());
        assertEquals(expectedMaze, renderedMaze);
    }

    @Test
    public void testRenderMazeWithPath() {
        String expectedMaze =
            """
                █████
                █·█·█
                █·█·█
                █···█
                █████
                """;

        String renderedMaze = Renderer.render(MazeUtilsTest.createManualMaze(), MazeUtilsTest.createPathManual());
        assertEquals(expectedMaze, renderedMaze);
    }
}
