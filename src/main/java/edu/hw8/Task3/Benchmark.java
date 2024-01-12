package edu.hw8.Task3;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import lombok.extern.log4j.Log4j2;

@Log4j2 final class Benchmark {

    private static final int MAX_PASSWORD_SIZE = 4;
    private static final int ATTEMPTS = 10;

    private Benchmark() {

    }

    @SuppressWarnings("MultipleStringLiterals")
    public static void raisingTimeByThreadsCount(int threads) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("src/main/java/edu/hw8/Task3/benchmarkAnswer.adoc"))) {
            writer.println("[options=\"header\"]");
            writer.println("|===");
            writer.println("| Потоки | Cреднее время(наносекунды) | Ускороение");

            double time = 0;
            for (int i = 0; i < ATTEMPTS; i++) {
                PasswordCracker cracker = new PasswordCracker(MAX_PASSWORD_SIZE, "src/main/java/edu/hw8/Task3/test");
                long start = System.nanoTime();
                cracker.crackPasswords();
                long end = System.nanoTime();
                time += end - start;
            }

            writer.println("| Без потоков | " + time / ATTEMPTS + " |");

            for (int i = 1; i <= threads; i++) {
                double timeThread = 0;
                for (int j = 0; j < ATTEMPTS; j++) {
                    PasswordCrackerThreads passwordCrackerThreads =
                        new PasswordCrackerThreads(MAX_PASSWORD_SIZE, "src/main/java/edu/hw8/Task3/test", i);
                    long startThread = System.nanoTime();
                    passwordCrackerThreads.crackPasswords();
                    long endThread = System.nanoTime();
                    timeThread += (endThread - startThread);
                }
                writer.println(
                    "| " + i + (i > 1 ? " потоков" : " поток") + " | " + timeThread / ATTEMPTS + " | "
                        + (time / ATTEMPTS) / (timeThread / ATTEMPTS));
            }

            writer.println("|===");
        } catch (IOException e) {
            log.error("Ошибка");
        }
    }

}
