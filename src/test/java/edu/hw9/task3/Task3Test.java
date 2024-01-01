package edu.hw9.task3;

import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class Task3Test {
    @Test
    void testFindPath() {
        DFSUndirectedGraph graph = new DFSUndirectedGraph(6);

        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 5);

        List<Integer> path = graph.dfs(0, 3);

        assertNotNull(path);
        assertEquals(List.of(0, 1, 3), path);
    }

    @Test
    void testFindPathNoPath() {
        DFSUndirectedGraph graph = new DFSUndirectedGraph(6);

        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 5);

        List<Integer> path = graph.dfs(4, 5);

        assertNotNull(path);
        assertEquals(List.of(4, 1, 0, 2, 5), path);
    }

    @Test
    void testFindPathInvalidVertex() {
        DFSUndirectedGraph graph = new DFSUndirectedGraph(6);

        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 5);

        List<Integer> path = graph.dfs(0, 10);

        assertNotNull(path);
        assertEquals(List.of(), path);
    }
}
