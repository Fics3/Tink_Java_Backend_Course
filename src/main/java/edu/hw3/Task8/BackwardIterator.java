package edu.hw3.Task8;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class BackwardIterator<T> implements Iterator<T> {

    private final List<T> collection;
    private int currentIndex;

    public BackwardIterator(List<T> collection) {
        this.collection = collection;
        this.currentIndex = collection.size() - 1;
    }

    @Override
    public boolean hasNext() {
        return currentIndex >= 0;
    }

    @Override
    public T next() {
        if (hasNext()) {
            T element = collection.get(currentIndex);
            currentIndex--;
            return element;
        } else {
            throw new NoSuchElementException("Error");
        }
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
