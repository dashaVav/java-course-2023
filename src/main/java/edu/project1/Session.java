package edu.project1;

import java.util.Arrays;
import org.jetbrains.annotations.NotNull;

class Session {
    private final String answer;
    private final char[] userAnswer;
    private final static int MAX_ATTEMPTS = 5;
    private int attempts;

    Session(String answer) {
        this.answer = answer;
        userAnswer = new char[answer.length()];
        Arrays.fill(userAnswer, '*');
        attempts = 0;
    }

    @NotNull GuessResult guess(char guess) {
        if (!answer.contains(String.valueOf(guess)) || Arrays.toString(userAnswer).contains(String.valueOf(guess))) {
            attempts++;
            if (attempts == MAX_ATTEMPTS) {
                return new GuessResult.Defeat(userAnswer, attempts, MAX_ATTEMPTS);
            }
            return new GuessResult.FailedGuess(
                userAnswer,
                attempts,
                MAX_ATTEMPTS
            );
        }

        for (int i = 0; i < answer.length(); i++) {
            if (answer.toCharArray()[i] == guess && userAnswer[i] == '*') {
                userAnswer[i] = guess;
            }
        }

        if (answer.equals(String.valueOf(userAnswer))) {
            return new GuessResult.Win(userAnswer, attempts, MAX_ATTEMPTS);
        }

        return new GuessResult.SuccessfulGuess(userAnswer, attempts, MAX_ATTEMPTS);

    }

    void giveUp() {
        System.exit(0);
    }
}
