package edu.hw7.Task4;

import lombok.extern.log4j.Log4j2;

@Log4j2 final class PiCounterBenchmark {
    private static final int ITERATIONS = 100000000;
    private static final int NUM_THREADS = 10;
    private static final int NUM_EXPERIMENTS = 100;

    private PiCounterBenchmark() {

    }

    @SuppressWarnings("MagicNumber")
    public static void errorBenchmark() {
        double actualPi = Math.PI;

        for (int iterations : new int[] {10000, 100000, 1000000, 10000000}) {
            double totalSingleThreadResult = 0;

            for (int i = 0; i < NUM_EXPERIMENTS; i++) {
                double singleThreadResult = new PiCalculator().calculatePi(iterations);
                totalSingleThreadResult += singleThreadResult;
            }

            double averageSingleThreadResult = totalSingleThreadResult / NUM_EXPERIMENTS;
            double absoluteError = Math.abs(averageSingleThreadResult - actualPi);
            double relativeErrorPercentage = (absoluteError / actualPi) * 100;

            log.info("Single thread result (" + iterations + " iterations): " + averageSingleThreadResult);
            log.info("Absolute Error: " + absoluteError);
            log.info("Relative Error: " + relativeErrorPercentage + "%");
        }
    }

    public static void timeBenchmark() {
        double startSingle = System.currentTimeMillis();
        double resultSingle = new PiCalculator().calculatePi(ITERATIONS);
        double endSingle = System.currentTimeMillis();

        log.info(ITERATIONS + " One thread result: " + resultSingle);
        log.info(ITERATIONS + " Single-threaded execution time: " + (endSingle - startSingle) + " milliseconds");

        for (int i = 1; i <= NUM_THREADS; i++) {
            double totalSpeedup = 0;

            for (int j = 0; j < NUM_EXPERIMENTS; j++) {
                PiCalculatorMultiThread piCalculatorMultiThread = new PiCalculatorMultiThread();
                double startMulti = System.currentTimeMillis();
                double resultMulti = piCalculatorMultiThread.calculatePi(ITERATIONS, i);
                double endMulti = System.currentTimeMillis();

                double speedup = (endSingle - startSingle) / (endMulti - startMulti);
                totalSpeedup += speedup;
            }

            double averageSpeedup = totalSpeedup / NUM_EXPERIMENTS;
            log.info(ITERATIONS + " Average speedup with " + i + " threads: " + averageSpeedup);
        }
    }

}
