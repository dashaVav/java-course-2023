package edu.hw2;

import edu.hw2.Connection.FaultyConnection;
import edu.hw2.Connection.StableConnection;
import java.util.Random;

public interface ConnectionManager {

    Connection getConnection();

    record DefaultConnectionManager() implements ConnectionManager {

        @Override
        public Connection getConnection() {
            if (new Random().nextInt(2) == 0) {
                return new StableConnection();
            } else {
                return new FaultyConnection();
            }
        }
    }

    record FaultyConnectionManager() implements ConnectionManager {
        @Override
        public Connection getConnection() {
            return new FaultyConnection();
        }
    }
}
