package edu.hw8.task3;

public class PasswordGenerator {
    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private final int length;
    private final int[] indexes;

    public PasswordGenerator(int length) {
        this.length = length;
        this.indexes = new int[length];
    }

    public synchronized String nextPassword() {
        if (nextIndexes()) {
            StringBuilder password = new StringBuilder();
            for (int index : indexes) {
                password.append(CHARACTERS.charAt(index));
            }
            return password.toString();
        } else {
            return null;
        }
    }

    private boolean nextIndexes() {
        for (int i = length - 1; i >= 0; i--) {
            if (indexes[i] < CHARACTERS.length() - 1) {
                indexes[i]++;
                return true;
            } else {
                indexes[i] = 0;
            }
        }
        return false;
    }
}

