package edu.project1;

import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConsoleHangman {
    private final static Logger LOGGER = LogManager.getLogger();
    private final String hiddenWord;
    private final static int MIN_LEN_HIDDEN_WORD = 5;

    ConsoleHangman(Dictionary dictionary) {
        hiddenWord = dictionary.randomWord();
    }

    private boolean isValidWord() {
        return hiddenWord.length() >= MIN_LEN_HIDDEN_WORD;
    }

    public void run() {
        if (!isValidWord()) {
            LOGGER.info("Invalid word length!");
            return;
        }

        Session session = new Session(hiddenWord);
        Scanner scanner = new Scanner(System.in);

        GuessResult resultOfGuess = null;

        while (!(resultOfGuess instanceof GuessResult.Defeat)
            && !(resultOfGuess instanceof GuessResult.Win)
            && !(resultOfGuess instanceof GuessResult.Exit)) {

            LOGGER.info("Guess a letter:");
            if (scanner.hasNext()) {
                String input = scanner.nextLine();
                if (input.length() != 1) {
                    continue;
                }
                resultOfGuess = tryGuess(session, input);
            } else {
                resultOfGuess = session.giveUp();
                printState(resultOfGuess);
            }
        }
    }

    private GuessResult tryGuess(Session session, String input) {
        GuessResult result = session.guess(input.charAt(0));
        printState(result);
        return result;
    }

    private void printState(GuessResult guess) {
        String[] messages = guess.message().split("\n");
        for (String mes : messages) {
            LOGGER.info(mes);
        }
    }
}
