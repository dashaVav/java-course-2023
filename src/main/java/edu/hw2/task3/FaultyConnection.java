package edu.hw2.task3;

import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FaultyConnection implements Connection {
    Logger logger = LogManager.getLogger();

    @Override
    public void execute(String command) {
        if (new Random().nextInt(2) == 0) {
            logger.info(command);
        } else {
            throw new ConnectionException("Connection error");
        }
    }

    @Override
    public void close() {
        logger.info("Faulty connection closed");
    }
}
