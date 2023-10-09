package edu.hw1;

public final class Task7 {
    private Task7() {
    }

    public static int rotateLeft(int n, int shift) {
        if (n <= 0 || shift < 0) {
            throw new IllegalArgumentException();
        }

        String binaryString = Integer.toBinaryString(Math.abs(n));
        String rotatedBinary = binaryString.substring(shift) + binaryString.substring(0, shift);

        return Integer.parseInt(rotatedBinary, 2);
    }

    public static int rotateRight(int n, int shift) {
        if (n <= 0 || shift < 0) {
            throw new IllegalArgumentException();
        }

        String binaryString = Integer.toBinaryString(n);
        String rotatedBinary = binaryString.substring(binaryString.length() - shift)
            + binaryString.substring(0, binaryString.length() - shift);

        return Integer.parseInt(rotatedBinary, 2);
    }
}
