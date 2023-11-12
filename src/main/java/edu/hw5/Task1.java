package edu.hw5;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public final class Task1 {
    private Task1() {
    }

    public static Duration calculateAverageTime(String[] data) {
        String regex = "^\\d{4}-\\d{2}-\\d{2}, \\d{2}:\\d{2} - \\d{4}-\\d{2}-\\d{2}, \\d{2}:\\d{2}$";
        Pattern pattern = Pattern.compile(regex);

        Duration totalDuration = Duration.ZERO;
        for (String entry : data) {
            if (!pattern.matcher(entry).find()) {
                throw new IllegalArgumentException();
            }

            String[] parts = entry.split(" - ");
            String start = parts[0];
            String end = parts[1];

            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm");
                Duration duration =
                    Duration.between(LocalDateTime.parse(start, formatter), LocalDateTime.parse(end, formatter));
                totalDuration = totalDuration.plus(duration);
            } catch (Exception e) {
                throw new IllegalArgumentException();
            }
        }

        if (data.length == 0) {
            return Duration.ZERO;
        }
        return totalDuration.dividedBy(data.length);
    }
}
