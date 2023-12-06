package edu.hw7.task4;

import java.util.concurrent.ThreadLocalRandom;

public final class Task4SingleThread {
    private Task4SingleThread() {
    }

    private static final int WIDTH = 1000;
    private static final double PROBABILITY_OF_POINT_HITTING_CIRCLE = 4.0;

    public static double calculationOfPiUsingMonteCarloMethod(int simulations) {
        int circleCount = 0;
        for (int j = 0; j < simulations; j++) {
            double x = ThreadLocalRandom.current().nextDouble(-WIDTH / 2.0, WIDTH / 2.0);
            double y = ThreadLocalRandom.current().nextDouble(-WIDTH / 2.0, WIDTH / 2.0);
            if (x * x + y * y <= (WIDTH / 2.0) * (WIDTH / 2.0)) {
                circleCount++;
            }
        }
        return PROBABILITY_OF_POINT_HITTING_CIRCLE * circleCount / simulations;
    }
}
