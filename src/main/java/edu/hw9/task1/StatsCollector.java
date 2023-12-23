package edu.hw9.task1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;

public class StatsCollector {
    private final Map<String, CompletableFuture<StatsResult>> stats = new ConcurrentHashMap<>();

    public void push(String metricName, double[] values) {
        CompletableFuture<StatsResult> future = CompletableFuture.supplyAsync(() -> {
            double sum = Arrays.stream(values).sum();
            double average = sum / values.length;
            double max = Arrays.stream(values).max().orElse(Double.NaN);
            double min = Arrays.stream(values).min().orElse(Double.NaN);

            return new StatsResult(metricName, sum, average, max, min);
        });

        stats.merge(metricName, future, (existingFuture, newFutureToAdd) ->
            existingFuture.thenCombine(newFutureToAdd, StatsResult::add));
    }

    public List<StatsResult> stats() {
        List<StatsResult> results = new ArrayList<>();

        for (Map.Entry<String, CompletableFuture<StatsResult>> entry : stats.entrySet()) {
            CompletableFuture<StatsResult> future = entry.getValue();

            StatsResult result;
            try {
                result = future.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
            results.add(result);
        }
        return results;
    }
}

