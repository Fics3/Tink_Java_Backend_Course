package edu.hw3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

final class Task3 {
    private Task3() {

    }

    public static Map<Object, Integer> freqDict(List<Object> list) {
        Map<Object, Integer> frequencyMap = new HashMap<>();

        for (Object item : list) {
            frequencyMap.put(item, frequencyMap.getOrDefault(item, 0) + 1);
        }

        return frequencyMap;
    }

}
