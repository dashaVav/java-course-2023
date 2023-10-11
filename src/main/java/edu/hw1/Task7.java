package edu.hw1;

public final class Task7 {
    private Task7() {
    }

    public static int rotateLeft(int n, int shift) {
        if (n <= 0 || shift < 0) {
            throw new IllegalArgumentException();
        }

        String binaryString = Integer.toBinaryString(n);
        int moduloShift = shift % binaryString.length();

        String rotatedBinary = binaryString.substring(moduloShift) + binaryString.substring(0, moduloShift);

        return Integer.parseInt(rotatedBinary, 2);
    }

    public static int rotateRight(int n, int shift) {
        if (n <= 0 || shift < 0) {
            throw new IllegalArgumentException();
        }

        String binaryString = Integer.toBinaryString(n);
        int moduloShift = shift % binaryString.length();

        String rotatedBinary = binaryString.substring(binaryString.length() - moduloShift)
            + binaryString.substring(0, binaryString.length() - moduloShift);

        return Integer.parseInt(rotatedBinary, 2);
    }
}
