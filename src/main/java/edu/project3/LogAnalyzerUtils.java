package edu.project3;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class LogAnalyzerUtils {
    private LogAnalyzerUtils() {
    }

    private static Stream<Log> filterLogs(List<Log> logs, OffsetDateTime from, OffsetDateTime to) {
        Stream<Log> logsNew;
        if (from == null && to == null) {
            logsNew = logs.stream();
        } else if (from == null) {
            logsNew = logs.stream().filter(log -> log.timeLocal().isBefore(to));
        } else if (to == null) {
            logsNew = logs.stream().filter(log -> log.timeLocal().isAfter(from));
        } else {
            logsNew = logs.stream().filter(log -> log.timeLocal().isAfter(from) && log.timeLocal().isBefore(to));
        }
        return logsNew;
    }

    public static long totalNumberOfRequests(List<Log> logs, OffsetDateTime from, OffsetDateTime to) {
        Stream<Log> logsNew = filterLogs(logs, from, to);
        return logsNew.count();
    }

    public static Map<String, Long> mostRequestedResources(
        List<Log> logs,
        OffsetDateTime from,
        OffsetDateTime to
    ) {
        Stream<Log> logsNew = filterLogs(logs, from, to);
        return logsNew
            .collect(Collectors.groupingBy(
                log -> log.request().split(" ")[1],
                Collectors.counting()
            ));
    }

    public static int averageServerResponseSize(List<Log> logs, OffsetDateTime from, OffsetDateTime to) {
        Stream<Log> logsNew = filterLogs(logs, from, to);
        return (int) logsNew
            .mapToLong(Log::bodyBytesSent)
            .average()
            .orElse(0.0);
    }

    public static Map<Integer, Long> mostCommonResponseCodes(List<Log> logs, OffsetDateTime from, OffsetDateTime to) {
        Stream<Log> logsNew = filterLogs(logs, from, to);
        return logsNew
            .collect(Collectors.groupingBy(Log::status, Collectors.counting()));
    }
}
