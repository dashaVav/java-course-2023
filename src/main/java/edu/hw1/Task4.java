package edu.hw1;

import java.util.Objects;

public final class Task4 {
    private Task4() {
    }

    public static String fixString(String wrongString) {
        Objects.requireNonNull(wrongString);

        char[] rightString = wrongString.toCharArray();

        for (int i = 0; i < rightString.length - 1; i += 2) {
            var temp = rightString[i];
            rightString[i] = rightString[i + 1];
            rightString[i + 1] = temp;
        }

        return new String(rightString);
    }

}
