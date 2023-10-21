package edu.hw2.task3;

import java.util.Random;

public class DefaultConnectionManager implements ConnectionManager {

    @Override
    public Connection getConnection() {
        if (new Random().nextInt(2) == 0) {
            return new StableConnection();
        } else {
            return new FaultyConnection();
        }
    }
}
