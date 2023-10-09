package edu.hw1;

import java.util.Arrays;
import java.util.Objects;

public final class Task3 {
    private Task3() {
    }

    public static boolean isNestable(int[] a1, int[] a2) {
        Objects.requireNonNull(a1);
        Objects.requireNonNull(a2);

        if (a1.length == 0 || a2.length == 0) {
            return false;
        }

        return Arrays.stream(a1).min().getAsInt() > Arrays.stream(a2).min().getAsInt()
            && Arrays.stream(a1).max().getAsInt() < Arrays.stream(a2).max().getAsInt();
    }
}
