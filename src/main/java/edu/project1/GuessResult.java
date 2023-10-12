package edu.project1;

import org.jetbrains.annotations.NotNull;

sealed interface GuessResult {
    @NotNull String state();

    int attempt();

    int maxAttempts();

    @NotNull String message();

    String[] LIST_OF_PRINTS = {"Missed, mistake %d out of %d.\n", "Hit!\n", "The word: %s\n"};

    record Exit(String state, int attempt, int maxAttempts) implements GuessResult {
        @Override
        public @NotNull String message() {
            return "You gave up!";
        }
    }

    record Defeat(String state, int attempt, int maxAttempts) implements GuessResult {
        @Override
        public @NotNull String message() {
            return String.format(LIST_OF_PRINTS[0], attempt(), maxAttempts())
                + String.format(LIST_OF_PRINTS[2], state()) + "You lost!";
        }
    }

    record Win(String state, int attempt, int maxAttempts) implements GuessResult {
        @Override
        public @NotNull String message() {
            return LIST_OF_PRINTS[1] + String.format(LIST_OF_PRINTS[2], state()) + "You won!";
        }
    }

    record SuccessfulGuess(String state, int attempt, int maxAttempts) implements GuessResult {
        @Override
        public @NotNull String message() {
            return LIST_OF_PRINTS[1] + String.format(LIST_OF_PRINTS[2], state());
        }
    }

    record FailedGuess(String state, int attempt, int maxAttempts) implements GuessResult {
        @Override
        public @NotNull String message() {
            return String.format(LIST_OF_PRINTS[0], attempt(), maxAttempts())
                + String.format(LIST_OF_PRINTS[2], state());
        }
    }
}
