package edu.hw9.task3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DFSUndirectedGraph {
    private final int vertices;
    private final List<Integer>[] adjacencyList;
    private final Set<Integer> visited;

    public DFSUndirectedGraph(int vertices) {
        this.vertices = vertices;
        this.adjacencyList = new List[vertices];
        for (int i = 0; i < vertices; i++) {
            adjacencyList[i] = new ArrayList<>();
        }
        this.visited = Collections.synchronizedSet(new HashSet<>());
    }

    public void addEdge(int vertex, int neighbor) {
        adjacencyList[vertex].add(neighbor);
        adjacencyList[neighbor].add(vertex);
    }

    public List<Integer> dfs(int start, int end) {
        try (ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime()
            .availableProcessors())) {
            Deque<Integer> stack = new ConcurrentLinkedDeque<>();
            Map<Integer, Integer> predecessors = new HashMap<>();

            stack.push(start);
            visited.add(start);

            while (!stack.isEmpty() || visited.size() != vertices) {
                if (stack.isEmpty()) {
                    continue;
                }
                int currentPoint = stack.pop();

                if (currentPoint == end) {
                    executorService.shutdown();
                    return buildPath(predecessors, start, end);
                }

                executorService.execute(() -> {
                    for (int neighbor : adjacencyList[currentPoint]) {
                        if (!visited.contains(neighbor)) {
                            stack.push(neighbor);
                            visited.add(neighbor);
                            predecessors.put(neighbor, currentPoint);
                        }
                    }
                });
            }
        }
        return List.of();
    }

    private List<Integer> buildPath(Map<Integer, Integer> predecessors, int start, int end) {
        List<Integer> path = new ArrayList<>();
        int currentPoint = end;

        while (currentPoint != start) {
            path.add(currentPoint);
            currentPoint = predecessors.get(currentPoint);
        }

        path.add(start);
        Collections.reverse(path);
        return path;
    }
}
