package edu.project3;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LogParserTest {
    @Test
    void parseValidLogLine() {
        String logLine = "93.180.71.3 - - [17/May/2015:08:05:32 +0000] \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\"";
        Log log = LogParser.parse(logLine);

        assertEquals("93.180.71.3", log.remoteAddr());
        assertEquals("-", log.remoteUser());
        assertEquals("GET /downloads/product_1 HTTP/1.1", log.request());
        assertEquals(304, log.status());
        assertEquals(0, log.bodyBytesSent());
        assertEquals("-", log.httpReferer());
        assertEquals("Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)", log.httpUserAgent());
    }

    @Test
    void parseInvalidLogLine() {
        String logLine = "Invalid log line";
        assertThrows(RuntimeException.class, () -> LogParser.parse(logLine));
    }

    @Test
    void parseLogWithInvalidDateFormat() {
        String logLine = "93.180.71.3 - - [InvalidDate] \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\"";
        Log log = LogParser.parse(logLine);

        assertNull(log.timeLocal());
    }

    @Test
    void parseLogWithNullDate() {
        String logLine = "93.180.71.3 - - [] \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\"";
        Log log = LogParser.parse(logLine);
        assertNull(log.timeLocal());
    }
}
