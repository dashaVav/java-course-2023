package edu.hw8.task1.client;

import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ClientApplication {
    private ClientApplication() {
    }

    private final static Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) {
        LOGGER.info("Input word:");
        Scanner scanner = new Scanner(System.in);
        String word = scanner.nextLine();

        String sentence = Client.run(word);

        LOGGER.info(sentence);
    }
}
