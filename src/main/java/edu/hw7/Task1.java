package edu.hw7;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Getter
public class Task1 {

    private final AtomicInteger count = new AtomicInteger(0);

    public void threadCounter(int limit, int threadCount) {

        List<Thread> threads = createTreads(limit, threadCount);

        for (int i = 0; i < threadCount; i++) {
            threads.get(i).start();
        }

        synchronizeThreads(threads, threadCount);

        log.info(count);
    }

    private List<Thread> createTreads(int limit, int threadCount) {
        ArrayList<Thread> threads = new ArrayList<>();
        for (int i = 0; i < threadCount; i++) {
            threads.add(new Thread(() -> {
                for (int j = 0; j < limit; j++) {
                    count.incrementAndGet();
                }
            }));
        }
        return threads;
    }

    private void synchronizeThreads(List<Thread> threads, int threadCount) {
        for (int i = 0; i < threadCount; i++) {
            try {
                threads.get(i).join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
