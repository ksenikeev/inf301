package ru.itis.inf301.lab2_4;

import java.util.Iterator;

public class MyList<T> implements List301<T>, Iterable<T> {

    private Object[] array = new Object[16];
    private int size = 0;

    @Override
    public void add(T e) {
        array[size++] = e;
    }

    @Override
    public void delete(int index) throws IndexOutOfBoundsException {
        System.arraycopy(array, index + 1, array, index, (--size) - index);
    }

    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        return (T)array[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<T> {

        private int currentPosition = 0;

        @Override
        public boolean hasNext() {
            return currentPosition < size;
        }

        @Override
        public T next() {
            return (T)array[currentPosition++];
        }
    }
}
