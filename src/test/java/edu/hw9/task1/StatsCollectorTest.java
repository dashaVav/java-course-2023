package edu.hw9.task1;

import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StatsCollectorTest {
    @Test
    void testSingleMetric() {
        StatsCollector collector = new StatsCollector();

        collector.push("metric1", new double[] {0.1, 0.05, 1.4, 5.1, 0.3});
        collector.push("metric1", new double[] {100, 0.05, 1.4, 5.1, 0.3});

        List<StatsResult> results = collector.stats();

        assertEquals(1, results.size());
        StatsResult result = results.get(0);

        assertEquals("metric1", result.metricName());
        assertEquals(113.8, result.sum(), 0.001);
        assertEquals(11.38, result.average(), 0.001);
        assertEquals(100.0, result.max(), 0.001);
        assertEquals(0.05, result.min(), 0.001);
    }

    @Test
    void testMultipleMetrics() {
        StatsCollector collector = new StatsCollector();

        collector.push("metric1", new double[] {0.1, 0.05, 1.4, 5.1, 0.3});
        collector.push("metric1", new double[] {100, 0.05, 1.4, 5.1, 0.3});

        collector.push("metric2", new double[] {2.0, 1.0, 3.0});

        List<StatsResult> results = collector.stats();

        assertEquals(2, results.size());

        for (StatsResult result : results) {
            if ("metric1".equals(result.metricName())) {
                assertEquals("metric1", result.metricName());
                assertEquals(113.8, result.sum(), 0.001);
                assertEquals(11.38, result.average(), 0.001);
                assertEquals(100.0, result.max(), 0.001);
                assertEquals(0.05, result.min(), 0.001);
            } else if ("metric2".equals(result.metricName())) {
                assertEquals(6.0, result.sum(), 0.001);
                assertEquals(2.0, result.average(), 0.001);
                assertEquals(3.0, result.max(), 0.001);
                assertEquals(1.0, result.min(), 0.001);
            }
        }
    }
}
