package edu.project1;

import org.jetbrains.annotations.NotNull;

sealed interface GuessResult {
    @NotNull String state();

    int attempt();

    @NotNull String message();

    String THE_WORD = "The word: %s\n";
    String MISSED = "Missed, mistake %d out of %d.\n";
    String HIT = "Hit!\n";
    int MAX_ATTEMPTS = 5;

    record Exit(String state, int attempt) implements GuessResult {
        @Override
        public @NotNull String message() {
            return "You gave up!";
        }
    }

    record Defeat(String state, int attempt) implements GuessResult {
        @Override
        public @NotNull String message() {
            return String.format(MISSED, attempt(), MAX_ATTEMPTS)
                + String.format(THE_WORD, state()) + "You lost!";
        }
    }

    record Win(String state, int attempt) implements GuessResult {
        @Override
        public @NotNull String message() {
            return  HIT + String.format(THE_WORD, state()) + "You won!";
        }
    }

    record SuccessfulGuess(String state, int attempt) implements GuessResult {
        @Override
        public @NotNull String message() {
            return HIT + String.format(THE_WORD, state());
        }
    }

    record FailedGuess(String state, int attempt) implements GuessResult {
        @Override
        public @NotNull String message() {
            return String.format(MISSED, attempt(), MAX_ATTEMPTS)
                + String.format(THE_WORD, state());
        }
    }
}
