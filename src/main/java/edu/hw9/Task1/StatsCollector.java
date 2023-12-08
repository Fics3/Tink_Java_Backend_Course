package edu.hw9.Task1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.Getter;

@Getter
public class StatsCollector {

    private final Map<String, List<Double>> statMap;

    public StatsCollector() {
        statMap = new ConcurrentHashMap<>();
    }

    public void push(String metricName, double[] statArray) {
        if (!statMap.containsKey(metricName)) {
            statMap.put(metricName, Collections.synchronizedList(new ArrayList<>()));
        }

        List<Double> metricData = statMap.get(metricName);

        synchronized (metricData) {
            for (var stat : statArray) {
                metricData.add(stat);
            }
        }

    }

    public List<MetricStats> stats() {
        List<MetricStats> metricList = new ArrayList<>();

        for (var metric : statMap.entrySet()) {
            String metricName = metric.getKey();
            double sum = countMetricSum(metricName);
            double avg = countAverage(metricName);
            double max = findMax(metricName);
            double min = findMin(metricName);

            metricList.add(new MetricStats(metricName, sum, avg, max, min));
        }
        return metricList;
    }

    private double countMetricSum(String metricName) {
        return statMap.get(metricName).parallelStream()
            .mapToDouble(Double::doubleValue)
            .sum();
    }

    private double countAverage(String metricName) {
        return statMap.get(metricName).parallelStream()
            .mapToDouble(Double::doubleValue)
            .average()
            .orElse(0);
    }

    private double findMax(String metricName) {
        return statMap.get(metricName).parallelStream()
            .max(Double::compareTo)
            .orElse(0.0);
    }

    private double findMin(String metricName) {
        return statMap.get(metricName).parallelStream()
            .min(Double::compareTo)
            .orElse(0.0);
    }

}
