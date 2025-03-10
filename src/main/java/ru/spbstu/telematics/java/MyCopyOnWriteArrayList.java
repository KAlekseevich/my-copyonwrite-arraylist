package ru.spbstu.telematics.java;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.CopyOnWriteArrayList;

public class MyCopyOnWriteArrayList<T> extends CopyOnWriteArrayList<T> {

    private volatile T[] list;
    private volatile int size;

    public MyCopyOnWriteArrayList() {
        list = (T[]) new Object[0];
        size = 0;
    }

    @Override
    public synchronized int size() {
        return size;
    }

    @Override
    public synchronized boolean contains(Object element) {
        for (int i = 0; i < size; i++) {
            if ((list[i] == null && element == null) || (list[i] != null && list[i].equals(element))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public synchronized void add(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        T[] newList = (T[]) new Object[size + 1];

        for (int i = 0; i < index; i++) {
            newList[i] = list[i];
        }
        newList[index] = element;
        for (int i = index; i < size; i++) {
            newList[i + 1] = list[i];
        }
        list = newList;
        size++;
    }

    @Override
    public synchronized T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        T removedElement = list[index]; //сохраняем удаляемый элемент

        T[] newList = (T[]) new Object[size - 1];
        for (int i = 0; i < index; i++) {
            newList[i] = list[i];
        }
        for (int i = index + 1; i < size; i++) {
            newList[i - 1] = list[i];
        }
        list = newList;
        size--;
        return removedElement; //возвращаем удаленный элемент
    }


    @Override
    public synchronized void clear() {
        list = (T[]) new Object[0];
        size = 0;
    }

    @Override
    public synchronized String toString() {
        StringBuilder list2string = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            list2string.append(list[i]);
            if (i < size - 1) {
                list2string.append(", ");
            }
        }
        list2string.append("]");
        return list2string.toString();
    }

    @Override
    public synchronized T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return list[index];
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<T> {
        private final T[] currentList;
        private int currentIndex;

        public MyIterator() {
            this.currentList = (T[]) list;
            this.currentIndex = 0;
        }

        @Override
        public boolean hasNext() {
            return currentIndex < currentList.length;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return currentList[currentIndex++];
        }
    }
}
