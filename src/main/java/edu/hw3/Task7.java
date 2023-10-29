package edu.hw3;

import java.util.Comparator;

class Task7<T extends Comparable<T>> implements Comparator<T> {
    @Override
    public int compare(T s1, T s2) {
        if (s1 == null) {
            return (s2 == null) ? 0 : -1;
        }
        if (s2 == null) {
            return 1;
        }
        return s1.compareTo(s2);
    }
}
