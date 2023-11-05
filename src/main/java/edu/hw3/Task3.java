package edu.hw3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Task3 {
    private Task3() {
    }

    public static <T> Map<T, Integer> freqDict(List<T> list) {
        Map<T, Integer> map = new HashMap<>();
        for (T i : list) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        return map;
    }
}
