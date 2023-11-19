package edu.project3;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class LogParser {
    private LogParser() {
    }

    @SuppressWarnings("checkstyle:MagicNumber")
    public static Log parse(String logLine) {
        String regex = "^(\\S+) (\\S+) \\S+ \\[([^\\]]*)\\] \"([^\"]+)\" (\\d+) (\\d+) \"([^\"]+)\" \"([^\"]+)\"$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(logLine);

        if (!matcher.find()) {
            throw new RuntimeException("Erroneous log format");
        }

        String remoteAddr = matcher.group(1);
        String remoteUser = matcher.group(2);

        OffsetDateTime timeLocal = null;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[dd/MMM/yyyy:HH:mm:ss Z]", Locale.ENGLISH);
            timeLocal = OffsetDateTime.parse(matcher.group(3), formatter);
        } catch (Exception ignored) {
        }

        String request = matcher.group(4);
        int status = Integer.parseInt(matcher.group(5));
        int bodyBytesSent = Integer.parseInt(matcher.group(6));
        String httpReferer = matcher.group(7);
        String httpUserAgent = matcher.group(8);

        return new Log(remoteAddr, remoteUser, timeLocal, request, status, bodyBytesSent, httpReferer, httpUserAgent);
    }
}
