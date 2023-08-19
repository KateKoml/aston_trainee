package com.example.customlist;

import java.util.Comparator;

public interface CustomList<E> extends Iterable<E> {
    boolean isEmpty();

    int size();

    boolean add(E element);

    boolean add(int index, E element);

    void remove(E element);

    E get(int index);

    int indexOf(E element);

    void set(int index, E element);

    boolean contains(E element);

    void clear();

    void sort();

    void sortDescending();

    void quicksort(Comparator<? super E> comparator);

    E[] toArray();
}
