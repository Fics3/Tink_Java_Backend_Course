package edu.hw8.Task3;

import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Getter final class PasswordCrackerThreads extends PasswordCracker {

    private final int threadPoolSize;

    private final ExecutorService executorService;

    PasswordCrackerThreads(int passwordSize, String filepath, int threadPoolSize) {
        super(passwordSize, filepath);
        this.threadPoolSize = threadPoolSize;
        executorService = Executors.newFixedThreadPool(threadPoolSize);

    }

    @Override
    public void crackPasswords() {
        int totalCombinations = (int) Math.pow(getAlphabet().length(), getMaxPasswordSize());
        int passwordsPerThread = totalCombinations / threadPoolSize;

        for (int i = 0; i < threadPoolSize; i++) {
            int startRange = i * passwordsPerThread;
            int endRange = (i + 1) * passwordsPerThread;

            executorService.submit(createCrackingTask(startRange, endRange));
        }

        executorService.shutdown();
        try {
            executorService.awaitTermination(1, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            log.error("Thread pool was interrupted during shutdown", e);
        }
    }

    private Runnable createCrackingTask(int startRange, int endRange) {
        return () -> {
            for (int count = startRange; count < endRange; count++) {
                String password = nextPassword(count);
                for (var entry : getPasswords().entrySet()) {
                    String actualHash = entry.getKey();
                    String username = entry.getValue();
                    if (Objects.equals(hashPassword(password), actualHash)) {
                        getCrackedPasswords().put(username, password);
                        break;
                    }
                }
            }
        };
    }

    private String nextPassword(int count) {
        StringBuilder password = new StringBuilder();
        int remainingIndex = count;
        for (int i = 0; i < getMaxPasswordSize(); i++) {
            int index = remainingIndex % getAlphabet().length();
            password.append(getAlphabet().charAt(index));
            remainingIndex = remainingIndex / getAlphabet().length();
        }

        padding(password);
        return password.toString();
    }

    private void padding(StringBuilder password) {
        while (password.length() < getMaxPasswordSize()) {
            password.insert(0, '0');
        }
    }

}
