package edu.hw2;

import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public interface Connection extends AutoCloseable {
    void execute(String command);

    Logger LOGGER = LogManager.getLogger();

    record StableConnection() implements Connection {

        @Override
        public void execute(String command) {
            LOGGER.info(command);
        }

        @Override
        public void close() {
            LOGGER.info("Stable connection closed");
        }
    }

    record FaultyConnection() implements Connection {

        @Override
        public void execute(String command) {
            if (new Random().nextInt(2) == 0) {
                LOGGER.info(command);
            } else {
                throw new ConnectionException("Connection error");
            }
        }

        @Override
        public void close() {
            LOGGER.info("Faulty connection closed");
        }
    }
}
