package edu.hw7;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task4Test {
    @Test
    void testPiCalculationAccuracySingleThread() {
        int simulations = 1000000;
        double calculatedPi = Task4SingleThread.calculationOfPiUsingMonteCarloMethod(simulations);

        double exactPi = Math.PI;

        double tolerance = 0.01;

        assertTrue(Math.abs(calculatedPi - exactPi) < tolerance);
    }
    @Test
    void testPiCalculationAccuracyMultiThread() {
        int simulations = 1000000;
        double calculatedPi = Task4MultiThread.calculationOfPiUsingMonteCarloMethod(1, simulations);

        double exactPi = Math.PI;

        double tolerance = 0.01;

        assertTrue(Math.abs(calculatedPi - exactPi) < tolerance);
    }

    @Test
    void testPerformanceWithDifferentThreadCounts() {
        int simulations = 10_000_000;

        long startTimeSingleThread = System.nanoTime();
        Task4MultiThread.calculationOfPiUsingMonteCarloMethod(1, simulations);
        long endTimeSingleThread = System.nanoTime();

        long startTimeTwoThreads = System.nanoTime();
        Task4MultiThread.calculationOfPiUsingMonteCarloMethod(2, simulations);
        long endTimeTwoThreads = System.nanoTime();

        long startTimeFourThreads = System.nanoTime();
        Task4MultiThread.calculationOfPiUsingMonteCarloMethod(4, simulations);
        long endTimeFourThreads = System.nanoTime();

        System.out.println("Time spent on 1 thread and 10 million simulations: " +
            (endTimeSingleThread - startTimeSingleThread) / 1_000_000 + " milliseconds");
        System.out.println("Time spent on 2 threads and 10 million simulations: " +
            (endTimeTwoThreads - startTimeTwoThreads) / 1_000_000 + " milliseconds");
        System.out.println("Time spent on 4 threads and 10 million simulations: " +
            (endTimeFourThreads - startTimeFourThreads) / 1_000_000 + " milliseconds");
    }

    @Test
    void testAccuracyWithDifferentSimulations() {
        int[] simulations = {10_000_000, 100_000_000, 1_000_000_000};

        for (int simulation : simulations) {
            double calculatedPi = Task4MultiThread.calculationOfPiUsingMonteCarloMethod(4, simulation);
            double exactPi = Math.PI;
            double accuracy = (1 - Math.abs(calculatedPi - exactPi) / exactPi) * 100;
            System.out.println(String.format("Accuracy in %d simulations: ", simulation) + accuracy);
        }
    }
}
