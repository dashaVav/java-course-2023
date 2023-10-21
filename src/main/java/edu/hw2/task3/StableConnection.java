package edu.hw2.task3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StableConnection implements Connection {
    Logger logger = LogManager.getLogger();

    @Override
    public void execute(String command) {
        logger.info(command);
    }

    @Override
    public void close() {
        logger.info("Stable connection closed");
    }
}
