package edu.hw1;

public final class Task5 {
    private Task5() {
    }

    private static final int LAST_DIGIT = 10;

    private static boolean isPalindrome(int number) {
        String numberStr = String.valueOf(Math.abs(number));
        String reverseStr = new StringBuilder(numberStr).reverse().toString();

        return numberStr.equals(reverseStr);
    }

    public static boolean isPalindromeDescendant(int number) {
        int lenNum = Task2.countDigits(number);
        if (lenNum < 2) {
            return false;
        }

        if (isPalindrome(number)) {
            return true;
        }

        int unsignedNumber = Math.abs(number);
        StringBuilder numberStr = new StringBuilder();

        int loopN = lenNum;
        for (int i = 0; i < loopN - 1; i += 2) {
            numberStr.append(unsignedNumber / (int) Math.pow(LAST_DIGIT, lenNum - 1)
                + (unsignedNumber % (int) Math.pow(LAST_DIGIT, lenNum - 1)) / (int) Math.pow(LAST_DIGIT, lenNum - 2));
            unsignedNumber %= (int) Math.pow(LAST_DIGIT, lenNum - 2);
            lenNum -= 2;
        }

        if (lenNum % 2 == 1) {
            numberStr.append(unsignedNumber);
        }

        return isPalindromeDescendant(Integer.parseInt(numberStr.toString()));
    }
}
