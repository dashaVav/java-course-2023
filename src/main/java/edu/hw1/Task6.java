package edu.hw1;

import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Task6 {
    private Task6() {
    }

    private static final int DIV = 10;
    private static final int UPPER_BOUND = 10000;
    private static final int LOWER_BOUND = 1000;
    private static final int KAPREKAR_NUMBER = 6174;
    private static final int LEN_KAPREKAR_NUMBER = 4;

    private static boolean isSymmetricNumber(int n) {
        int lastDigit = n % DIV;
        int changeN = n / DIV;
        while (changeN > 0) {
            if (lastDigit != changeN % DIV) {
                return false;
            }
            changeN /= DIV;
        }
        return true;
    }

    private static String savingZeros(String str) {
        StringBuilder result = new StringBuilder(str);
        while (result.length() < LEN_KAPREKAR_NUMBER) {
            result.insert(0, "0");
        }
        return result.toString();
    }

    private static int k(int n, int counter) {
        if (n == KAPREKAR_NUMBER) {
            return counter;
        }

        int strSort = Integer.parseInt(Stream.of(savingZeros(String.valueOf(n)).split(""))
            .sorted()
            .collect(Collectors.joining()));

        int strReverse = Integer.parseInt(Stream.of(savingZeros(String.valueOf(n)).split(""))
            .sorted(Collections.reverseOrder())
            .collect(Collectors.joining()));

        return k(strReverse - strSort, counter + 1);

    }

    public static int countK(int n) {
        if (isSymmetricNumber(n)) {
            return -1;
        }

        if (n <= LOWER_BOUND || n >= UPPER_BOUND) {
            return -1;
        }

        return k(n, 0);
    }
}
