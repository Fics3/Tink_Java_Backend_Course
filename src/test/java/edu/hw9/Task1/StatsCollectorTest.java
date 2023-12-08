package edu.hw9.Task1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class StatsCollectorTest {

    StatsCollector statsCollector;

    @BeforeEach
    void setUp() {
        statsCollector = new StatsCollector();
    }

    @RepeatedTest(5)
    @DisplayName("push - no Threads")
    void push_PushMetric_NoThreads() {
        // Arrange
        String metricName = "metric";
        double[] statsArray = {1.2, 1, 1.4};

        //Act
        statsCollector.push(metricName, statsArray);

        //Arrange
        assertThat(statsCollector.getStatMap().size()).isOne();
    }

    @RepeatedTest(5)
    @DisplayName("push - Multiple threads")
    void push_PushMetric_MultipleThreads() throws InterruptedException {
        // Arrange
        double[] statsArray = {1.2, 1, 1.4};
        int threadsCount = 10;
        int metricsCount = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(threadsCount);

        //Act
        for (int i = 0; i < metricsCount; i++) {
            String metricName = String.valueOf(i);
            executorService.submit(() -> statsCollector.push(metricName, statsArray));
        }

        executorService.awaitTermination(2, TimeUnit.SECONDS);

        //Arrange
        assertThat(statsCollector.getStatMap().size()).isEqualTo(metricsCount);
    }

    @RepeatedTest(5)
    @DisplayName("stats - Aggregate metrics")
    void stats_AggregateMetrics_SizeEqualMetricsCount() throws InterruptedException {
        // Arrange
        double[] statsArray = {1.2, 1, 1.4};
        int threadsCount = 10;
        int metricsCount = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(threadsCount);

        //Act
        for (int i = 0; i < metricsCount; i++) {
            String metricName = String.valueOf(i);
            executorService.submit(() -> statsCollector.push(metricName, statsArray));
        }

        executorService.awaitTermination(2, TimeUnit.SECONDS);

        var stats = statsCollector.stats();

        //Arrange
        assertThat(stats.size()).isEqualTo(metricsCount);
    }

    @Test
    @DisplayName("stats - Metrics aggregated correctly")
    void stats_AggregateMetrics_ShouldReturnAggregatedMetrics() {
        // Arrange
        double[] statsArray = {1.2, 1, 1.4};
        String metricName = "metric";

        //Act
        statsCollector.push(metricName, statsArray);
        statsCollector.push(metricName, statsArray);
        var stats = statsCollector.stats();

        //Arrange
        assertThat(stats.get(0).metric()).isEqualTo(metricName);
        assertThat(stats.get(0).sum()).isEqualTo(7.2);
        assertThat(stats.get(0).average()).isEqualTo(1.2);
        assertThat(stats.get(0).max()).isEqualTo(1.4);
        assertThat(stats.get(0).min()).isEqualTo(1);
    }
}
