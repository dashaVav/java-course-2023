package edu.project3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LogAnalyzerUtilsTest {
    private List<Log> logs;

    @BeforeEach
    void setUp() {
        logs = Arrays.asList(
            new Log("93.180.71.3", "-", null, "GET /downloads/product_1 HTTP/1.1", 304, 0, "-", "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)"),
            new Log("192.168.1.1", "-", null, "GET /downloads/product_2 HTTP/1.1", 200, 500, "-", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3"),
            new Log("93.180.71.3", "-", null, "GET /downloads/product_1 HTTP/1.1", 404, 0, "-", "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)"),
            new Log("192.168.1.1", "-", null, "GET /downloads/product_3 HTTP/1.1", 200, 800, "-", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3"),
            new Log("93.180.71.3", "-", null, "GET /downloads/product_1 HTTP/1.1", 404, 0, "-", "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)")
        );
    }

    @Test
    void totalNumberOfRequests() {
        long result = LogAnalyzerUtils.totalNumberOfRequests(logs, null, null);
        assertEquals(5, result);
    }

    @Test
    void mostRequestedResources() {
        Map<String, Long> result = LogAnalyzerUtils.mostRequestedResources(logs, null, null);

        assertEquals(3, result.size());
        assertEquals(3, result.get("/downloads/product_1"));
        assertEquals(1, result.get("/downloads/product_2"));
        assertEquals(1, result.get("/downloads/product_3"));
    }

    @Test
    void averageServerResponseSize() {
        int result = LogAnalyzerUtils.averageServerResponseSize(logs, null, null);
        assertEquals(260, result);
    }

    @Test
    void mostCommonResponseCodes() {
        Map<Integer, Long> result = LogAnalyzerUtils.mostCommonResponseCodes(logs, null, null);

        assertEquals(3, result.size());
        assertEquals(2, result.get(404));
        assertEquals(2, result.get(200));
        assertEquals(1, result.get(304));
    }
}
