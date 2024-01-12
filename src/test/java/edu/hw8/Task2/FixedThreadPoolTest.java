package edu.hw8.Task2;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class FixedThreadPoolTest {

    private FixedThreadPool threadPool;

    @BeforeEach
    void setUp() {
        threadPool = new FixedThreadPool(3);
        threadPool.start();
    }

    @AfterEach
    void tearDown() throws Exception {
        threadPool.close();
    }

    @Test
    @DisplayName("execute - One task")
    void execute_ExecuteTask_OneTask() {
        // Arrange
        AtomicInteger counter = new AtomicInteger(0);
        Runnable task = counter::incrementAndGet;

        // Act
        threadPool.execute(task);

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Assert
        assertThat(counter.get()).isEqualTo(1);
    }

    @Test
    @DisplayName("execute - Multiple tasks")
    void execute_ExecuteTask_MultipleTasks() throws InterruptedException {
        // Arrange
        int taskCount = 100;
        CountDownLatch latch = new CountDownLatch(taskCount);

        // Act
        for (int i = 0; i < taskCount; i++) {
            threadPool.execute(latch::countDown);
            // Assert
        }

        latch.await();


        assertThat(latch.getCount()).isZero();
    }
}
