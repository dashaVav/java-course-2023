package edu.hw5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Task7 {
    private Task7() {
    }

    public static boolean task1(String string) {
        Pattern pattern = Pattern.compile("^[01]{2}0[01]*$");
        Matcher matcher = pattern.matcher(string);

        return matcher.find();
    }

    public static boolean task2(String string) {
        Pattern pattern = Pattern.compile("^(0[01]*0|1[01]*1|0|1)$");
        Matcher matcher = pattern.matcher(string);

        return matcher.find();
    }

    public static boolean task3(String string) {
        Pattern pattern = Pattern.compile("^[01]{1,3}$");
        Matcher matcher = pattern.matcher(string);

        return matcher.find();
    }
}
