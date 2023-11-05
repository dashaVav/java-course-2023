package edu.hw3;

public final class Task4 {
    private Task4() {
    }

    private final static int MIN_ROMAN = 1;
    private final static int MAX_ROMAN = 3999;

    private final static int[] ARABIC_VALUES = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    private final static String[] ROMAN_SYMBOLS =
        {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

    public static String convertToRoman(int number) {
        int oldNumber = number;
        if (number < MIN_ROMAN || number > MAX_ROMAN) {
            throw new IllegalArgumentException("Invalid number");
        }

        StringBuilder romanNumber = new StringBuilder();

        for (int i = 0; i < ARABIC_VALUES.length; i++) {
            while (oldNumber >= ARABIC_VALUES[i]) {
                romanNumber.append(ROMAN_SYMBOLS[i]);
                oldNumber -= ARABIC_VALUES[i];
            }
        }
        return romanNumber.toString();
    }
}
