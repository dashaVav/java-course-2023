package edu.hw1;

import java.util.Objects;

public final class Task1 {
    private static final int SECONDS = 60;

    private Task1() {
    }

    public static int minutesToSeconds(String time) {
        Objects.requireNonNull(time);
        if (time.split(":").length != 2) {
            return -1;
        }

        String[] nums = time.split(":");
        var regex = "\\d+";
        if (!nums[0].matches(regex) || !nums[1].matches(regex) || nums[0].length() < 2 || nums[1].length() != 2) {
            return -1;
        }

        int minutes = Integer.parseInt(nums[0]);
        int seconds = Integer.parseInt(nums[1]);

        if (seconds >= SECONDS) {
            return -1;
        }

        return minutes * SECONDS + seconds;
    }
}
