package edu.hw3;

public final class Task1 {
    private Task1() {
    }

    public static String atbash(String input) {
        StringBuilder result = new StringBuilder();

        for (char c : input.toCharArray()) {
            if (Character.isLetter(c) && Character.UnicodeBlock.of(c).equals(Character.UnicodeBlock.BASIC_LATIN)) {
                if (Character.isUpperCase(c)) {
                    result.append((char) ('Z' - c + 'A'));
                } else {
                    result.append((char) ('z' - c + 'a'));
                }
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }
}

