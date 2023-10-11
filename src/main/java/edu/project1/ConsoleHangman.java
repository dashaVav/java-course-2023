package edu.project1;

import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConsoleHangman {
    private final static Logger LOGGER = LogManager.getLogger();

    public void run() {
        Dictionary dictionary = new Dictionary();
        Session session = new Session(dictionary.randomWord());
        Scanner scanner = new Scanner(System.in);

        while (true) {
            LOGGER.info("Guess a letter:");

            if (!scanner.hasNext()) {
                session.giveUp();
            }
            String input = scanner.nextLine();

            if (input.length() != 1) {
                continue;
            }

            GuessResult resultOfGuess = tryGuess(session, input);
            printState(resultOfGuess);

            if (resultOfGuess instanceof GuessResult.Win || resultOfGuess instanceof GuessResult.Defeat) {
                break;
            }
        }
    }

    private GuessResult tryGuess(Session session, String input) {
        return session.guess(input.charAt(0));
    }

    private void printState(GuessResult guess) {
        String[] messages = guess.message().split("\n");

        LOGGER.info(messages[0]);

        LOGGER.info("The word: " + String.valueOf(guess.state()));

        if (guess instanceof GuessResult.Win || guess instanceof GuessResult.Defeat) {
            LOGGER.info(messages[1]);
        }
    }
}
