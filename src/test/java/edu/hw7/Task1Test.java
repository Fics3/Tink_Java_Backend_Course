package edu.hw7;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Task1Test {

    private Task1 task;

    @BeforeEach
    void setUp() {
        task = new Task1();
    }

    @Test
    @DisplayName("Single Thread - Single Increment")
    void threadCounter_SingleThread_SingleIncrement() {
        // Act
        task.threadCounter(1, 1);

        // Assert
        assertThat(task.getCount()).hasValue(1);
    }

    @Test
    @DisplayName("Multiple Threads - Increment Limit")
    void threadCounter_MultipleThreads_IncrementLimit() {
        // Act
        task.threadCounter(100, 5);

        // Assert
        assertThat(task.getCount()).hasValue(500); // 100 increments per thread, 5 threads
    }

    @Test
    @DisplayName("Multiple Threads - Multiple Increments")
    void threadCounter_MultipleThreads_MultipleIncrements() {
        // Act
        task.threadCounter(5, 3);

        // Assert
        assertThat(task.getCount()).hasValue(15);
    }

    @Test
    @DisplayName("Zero Threads - Zero Increment")
    void threadCounter_ZeroThreads_ZeroIncrement() {
        // Act
        task.threadCounter(1, 0);

        // Assert
        assertThat(task.getCount()).hasValue(0);
    }

    @Test
    @DisplayName("Zero Threads - Zero Increment")
    void threadCounter_TwentyThreads_OneIncrement() {
        // Act
        task.threadCounter(1, 20);

        // Assert
        assertThat(task.getCount()).hasValue(20);
    }
}
