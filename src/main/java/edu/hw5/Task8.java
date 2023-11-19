package edu.hw5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Task8 {
    private Task8() {
    }

    public static boolean task1(String string) {
        Pattern pattern = Pattern.compile("^(00|01|10|11)*[10]$");
        Matcher matcher = pattern.matcher(string);

        return matcher.find();
    }

    public static boolean task4(String string) {
        Pattern pattern = Pattern.compile("^(?!11$|111$)[0-1]+$");
        Matcher matcher = pattern.matcher(string);

        return matcher.find();
    }

    public static boolean task5(String string) {
        Pattern pattern = Pattern.compile("(^(10|11)+1*$)|(^(10|11)*1+$)");
        Matcher matcher = pattern.matcher(string);

        return matcher.find();
    }

    public static boolean task7(String string) {
        Pattern pattern = Pattern.compile("(?!.*11)(^[01]*$)");
        Matcher matcher = pattern.matcher(string);

        return matcher.find();
    }

}
