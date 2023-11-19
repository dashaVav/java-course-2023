package edu.hw5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Task5 {
    private Task5() {
    }

    public static boolean carNumberValidator(String carNumber) {
        Pattern pattern = Pattern.compile("^[АВЕКМНОРСТУХ]\\d{3}[АВЕКМНОРСТУХ]{2}\\d{3}$");
        Matcher matcher = pattern.matcher(carNumber);

        return matcher.find();
    }

}
