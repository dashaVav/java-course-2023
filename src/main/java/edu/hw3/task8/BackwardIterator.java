package edu.hw3.task8;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class BackwardIterator<T> implements Iterator<T> {
    private final T[] elements;
    private int currentIndex;

    @SuppressWarnings("unchecked") public BackwardIterator(List<T> collection) {
        this.elements = (T[]) collection.toArray();
        this.currentIndex = elements.length - 1;
    }

    @Override public boolean hasNext() {
        return currentIndex >= 0;
    }

    @Override public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No next element");
        }
        return elements[currentIndex--];
    }
}
