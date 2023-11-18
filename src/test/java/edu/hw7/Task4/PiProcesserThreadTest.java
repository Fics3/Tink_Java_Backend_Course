package edu.hw7.Task4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.concurrent.atomic.AtomicInteger;
import static org.assertj.core.api.Assertions.assertThat;

public class PiProcesserThreadTest {
    @Test
    @DisplayName("Thread Run - Check Circle Count and Total Count")
    void threadRun_CheckCounts() {
        // Arrange
        long start = 0;
        long end = 10000;
        AtomicInteger circleCount = new AtomicInteger(0);
        AtomicInteger totalCount = new AtomicInteger(0);

        // Act
        PiProcessorThread piProcessorThread = new PiProcessorThread(start, end, circleCount, totalCount);
        piProcessorThread.start();

        // Assert
        assertThat(circleCount.get()).isGreaterThanOrEqualTo(0);
        assertThat(totalCount.get()).isEqualTo(end - start);
    }
}
