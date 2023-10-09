package edu.hw1;

public final class Task2 {
    private static final int DIV = 10;

    private Task2() {
    }

    public static int countDigits(int number) {
        int result = 0;
        int unsignedNumber = Math.abs(number);
        do {
            result++;
            unsignedNumber /= DIV;
        } while (unsignedNumber > 0);

        return result;
    }
}
