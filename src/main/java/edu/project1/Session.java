package edu.project1;

import java.util.Arrays;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
class Session {
    private final String answer;
    private String userAnswer;
    private final static int MAX_ATTEMPTS = 5;
    private int attempts;

    Session(String answer) {
        this.answer = answer;

        char[] str = new char[answer.length()];
        Arrays.fill(str, '*');
        userAnswer = String.valueOf(str);

        attempts = 0;
    }

    @NotNull GuessResult guess(char guess) {
        if (!answer.contains(String.valueOf(guess)) || userAnswer.contains(String.valueOf(guess))) {
            attempts++;
            if (attempts == MAX_ATTEMPTS) {
                return new GuessResult.Defeat(userAnswer, attempts, MAX_ATTEMPTS);
            }
            return new GuessResult.FailedGuess(userAnswer, attempts, MAX_ATTEMPTS);
        }

        char[] changedUserAnswer = userAnswer.toCharArray();
        for (int i = 0; i < answer.length(); i++) {
            if (answer.toCharArray()[i] == guess) {
                changedUserAnswer[i] = guess;
            }
        }
        userAnswer = String.valueOf(changedUserAnswer);

        if (answer.equals(userAnswer)) {
            return new GuessResult.Win(userAnswer, attempts, MAX_ATTEMPTS);
        }

        return new GuessResult.SuccessfulGuess(userAnswer, attempts, MAX_ATTEMPTS);
    }

    @NotNull GuessResult giveUp() {
        return new GuessResult.Exit(userAnswer, attempts, MAX_ATTEMPTS);
    }
}
