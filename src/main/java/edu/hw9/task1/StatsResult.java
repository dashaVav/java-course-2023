package edu.hw9.task1;

public record StatsResult(String metricName, double sum, double average, double max, double min) {
    public StatsResult add(StatsResult another) {
        if (!another.metricName().equals(metricName)) {
            throw new IllegalArgumentException();
        }
        return new StatsResult(metricName, another.sum() + sum, (another.average() + average) / 2,
            Math.max(another.max(), max), Math.min(another.min(), min)
        );
    }
}
