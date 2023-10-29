package edu.hw3;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public final class Task2 {
    private Task2() {
    }

    private final static String ERROR_MESSAGE = "Invalid sequence of square brackets";

    private static boolean isValidSequence(StringBuilder sequence) {
        Stack<Character> stack = new Stack<>();
        for (char c : sequence.toString().toCharArray()) {
            if (c == '(') {
                stack.push(c);
            } else {
                if (!stack.isEmpty() && stack.peek() == '(') {
                    stack.pop();
                }
            }
        }
        return stack.isEmpty();
    }

    public static List<String> clusterize(String input) {
        List<String> result = new ArrayList<>();
        StringBuilder queue = new StringBuilder();

        int count = 0;
        for (char c : input.toCharArray()) {
            if (c == '(') {
                count++;
            } else {
                count--;
            }
            queue.append(c);

            if (count == 0) {
                if (isValidSequence(queue)) {
                    result.add(queue.toString());
                    queue = new StringBuilder();
                } else {
                    throw new IllegalArgumentException(ERROR_MESSAGE);
                }
            }
        }

        if (count != 0) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }

        return result;
    }
}
