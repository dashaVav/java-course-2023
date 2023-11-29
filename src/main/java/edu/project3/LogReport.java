package edu.project3;

import java.net.URI;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogReport {
    private final String file;
    private final OffsetDateTime from;
    private final OffsetDateTime to;
    private long totalNumberOfRequests;
    private Map<String, Long> mostRequestedResources;
    private int averageServerResponseSize;
    private Map<Integer, Long> mostCommonResponseCodes;

    public LogReport(String path, String fromString, String toString) {
        if (path == null) {
            throw new RuntimeException("Path must be specified");
        }
        from = parseTime(fromString);
        to = parseTime(toString);
        file = path.substring(path.lastIndexOf("/") + 1);

        reportFromTo(path);
    }

    private OffsetDateTime parseTime(String time) {
        if (time == null) {
            return null;
        }

        LocalDate localDate = LocalDate.parse(time, DateTimeFormatter.ISO_LOCAL_DATE);
        return localDate.atStartOfDay().atOffset(ZoneOffset.UTC);
    }

    private void reportFromTo(String path) {
        List<Log> streamLog;
        if (path.startsWith("http")) {
            try {
                streamLog = ReceiveData.request(new URI(path));
            } catch (Exception e) {
                throw new RuntimeException("Invalid uri");
            }
        } else {
            streamLog = ReceiveData.read(Path.of(path));
        }

        totalNumberOfRequests = LogAnalyzerUtils.totalNumberOfRequests(streamLog, from, to);
        mostRequestedResources = LogAnalyzerUtils.mostRequestedResources(streamLog, from, to);
        averageServerResponseSize = LogAnalyzerUtils.averageServerResponseSize(streamLog, from, to);
        mostCommonResponseCodes = LogAnalyzerUtils.mostCommonResponseCodes(streamLog, from, to);
    }
}
