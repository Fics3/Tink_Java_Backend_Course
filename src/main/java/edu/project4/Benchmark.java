package edu.project4;

import edu.project4.BaseObjects.FractalImage;
import edu.project4.BaseObjects.Rectangle;
import edu.project4.Transformations.Transformation;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import lombok.extern.log4j.Log4j2;

@SuppressWarnings({"MagicNumbers", "ParameterNumber", "MultipleStringLiterals"})
@Log4j2 final class Benchmark {

    private Benchmark() {

    }

    private static void timeBenchmark(
        int width,
        int height,
        List<Transformation> transformations,
        int samples,
        Rectangle rectangle,
        int iterPerSample,
        int eq,
        int symmetry,
        int threadCount,
        int testCount
    ) {
        try (FileWriter writer = new FileWriter("src/main/java/edu/project3/benchmark.adoc")) {
            writer.write("= Результаты теста производительности\n\n");
            writer.write("[options=\"header\"]\n");
            writer.write("|===\n");
            writer.write("| Потоки | Среднее время (нс) | Ускорение\n");

            long baseTotalTime = 0;
            for (int i = 0; i < testCount; i++) {
                ChaosGame chaosGame = new ChaosGame(FractalImage.create(width, height), transformations);
                long baseStart = System.nanoTime();
                chaosGame.render(samples, rectangle, iterPerSample, eq, symmetry);
                long baseEnd = System.nanoTime();
                baseTotalTime += baseEnd - baseStart;
            }
            long baseAverageTime = baseTotalTime / testCount;
            writer.write("| Без потоков | " + baseAverageTime + " | -\n");

            for (int i = 1; i <= threadCount; i++) {
                long threadTotalTime = 0;
                for (int j = 0; j < testCount; j++) {
                    ChaosGameMultiThread chaosGameMultiThread =
                        new ChaosGameMultiThread(FractalImage.create(width, height), transformations, i);
                    long threadStart = System.nanoTime();
                    chaosGameMultiThread.render(samples, rectangle, iterPerSample, eq, symmetry);
                    long threadEnd = System.nanoTime();
                    threadTotalTime += threadEnd - threadStart;
                }
                long threadAverageTime = threadTotalTime / testCount;

                double speedup = (double) baseAverageTime / threadAverageTime;
                writer.write("| " + i + " | " + threadAverageTime + " | " + speedup + "\n");
            }
            writer.write("|===\n");
        } catch (IOException e) {
            log.error("error");
        }
    }

}
