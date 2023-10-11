package edu.project1;

import org.jetbrains.annotations.NotNull;

sealed interface GuessResult {
    char[] state();

    @NotNull String message();

    String[] LIST = {"Missed, mistake %d out of %d.\n", "Hit!\n"};

    record Defeat(char[] state, int attempt, int maxAttempts) implements GuessResult {
        @Override
        public @NotNull String message() {
            return String.format(LIST[0], attempt(), maxAttempts()) + "You lost!";
        }
    }

    record Win(char[] state, int attempt, int maxAttempts) implements GuessResult {
        @Override
        public @NotNull String message() {
            return LIST[1] + "You won!";
        }

    }

    record SuccessfulGuess(char[] state, int attempt, int maxAttempts) implements GuessResult {
        @Override
        public @NotNull String message() {
            return LIST[1];
        }
    }

    record FailedGuess(char[] state, int attempt, int maxAttempts) implements GuessResult {
        @Override
        public @NotNull String message() {
            return String.format(LIST[0], attempt(), maxAttempts());
        }
    }
}
