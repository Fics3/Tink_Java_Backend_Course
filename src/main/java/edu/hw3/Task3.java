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
            if (frequencyMap.containsKey(item)) {
                frequencyMap.put(item, frequencyMap.get(item) + 1);
            } else {
                frequencyMap.put(item, 1);
            }
        }

        return frequencyMap;
    }
}
