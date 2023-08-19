package com.example.customlist;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A custom implementation of a dynamic array-based list.
 *
 * @param <E> the type of elements stored in the list
 */
public class CustomArrayList<E> implements CustomList<E> {
    private E[] elements;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * Creates a new CustomArrayList instance with the default initial capacity.
     */
    public CustomArrayList() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Constructs a new CustomArrayList instance with the specified initial capacity.
     *
     * @param initSize the initial capacity of the list
     * @throws IllegalArgumentException if the initial capacity is not positive
     */
    public CustomArrayList(int initSize) {
        if (initSize < 0) {
            throw new IllegalArgumentException("illegal size:" + initSize);
        }
        this.elements = (E[]) new Comparable[initSize];
    }

    /**
     * Returns true if this list contains no elements.
     *
     * @return true if this list contains no elements
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Increases the capacity of this list if necessary to ensure that it can hold at least the
     * specified number of elements.
     *
     * @param requiredCapacity the minimum required capacity
     */
    private void ensureCapacity(int requiredCapacity) {
        if (requiredCapacity > elements.length) {
            Object[] oldElements = elements;
            int newCapacity = (size * 3) / 2 + 1;
            elements = (E[]) Arrays.copyOf(oldElements, newCapacity);
        }
    }

    /**
     * Appends the specified element to the end of this list.
     *
     * @param element the element to be appended to this list
     * @return true (as specified by Collection.add(E))
     */
    @Override
    public boolean add(E element) {
        ensureCapacity(this.size + 1);
        elements[this.size++] = element;
        return true;
    }

    /**
     * Throws an IllegalArgumentException if the specified index is out of range (index < 0 || index >= size()).
     *
     * @param index the index to check
     */
    private void checkRange(int index) {
        if (index < 0 || index >= (this.size + 1)) {
            throw new IllegalArgumentException("illegal index:" + index);
        }
    }

    /**
     * Inserts the specified element at the specified position in this list.
     *
     * @param index   the index at which to insert the element
     * @param element the element to insert
     * @return true (as specified by Collection.add(E))
     * @throws IllegalArgumentException if the index is out of range (index < 0 || index > size())
     */
    @Override
    public boolean add(int index, E element) {
        checkRange(index);
        ensureCapacity(this.size + 1);
        System.arraycopy(this.elements, index, elements, index + 1, size - index);
        elements[index] = element;
        this.size++;
        return true;
    }

    /**
     * Removes the first occurrence of the specified element from this list, if it is present.
     *
     * @param element the element to be removed from this list, if present
     */
    @Override
    public void remove(E element) {
        if (element == null) {
            for (int i = 0; i < this.size; i++) {
                if (this.elements[i] == null) {
                    fastRemove(i);
                    return;
                }
            }
        } else {
            for (int i = 0; i < this.size; i++) {
                if (element.equals(this.elements[i])) {
                    fastRemove(i);
                    return;
                }
            }
        }
    }

    /**
     * Removes the element at the specified position in this list. Shifts any subsequent elements to the left (subtracts one
     * from their indices).
     *
     * @param index the index of the element to be removed
     * @throws IllegalArgumentException if the index is out of range (index < 0 || index >= size())
     */
    private void fastRemove(int index) {
        int movedNumber = this.size - index - 1;
        if (movedNumber > 0) {
            System.arraycopy(this.elements, index + 1, this.elements, index, movedNumber);
        }
        this.elements[--this.size] = null;
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index the index of the element to return
     * @return the element at the specified position in this list
     * @throws IllegalArgumentException if the index is out of range (index < 0 || index >= size())
     */
    @Override
    public E get(int index) {
        return this.elements[index];
    }

    /**
     * Returns the index of the first occurrence of the specified element in this list, or -1 if this list does not contain
     * the element.
     *
     * @param element the element to search for
     * @return the index of the first occurrence of the specified element in this list, or -1 if this list does not contain
     * the element
     */
    @Override
    public int indexOf(E element) {

        if (element == null) {
            for (int i = 0; i < this.elements.length; i++) {
                if (this.elements[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < this.elements.length; i++) {
                if (element.equals(this.elements[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Replaces the element at the specified position in this list with the specified element.
     *
     * @param index   the index of the element to replace
     * @param element the element to be stored at the specified position
     * @throws IllegalArgumentException if the index is out of range (index < 0 || index >= size())
     */
    @Override
    public void set(int index, E element) {
        checkRange(index);
        ensureCapacity(this.size + 1);
        this.elements[index] = element;
    }

    /**
     * Returns true if this list contains the specified element.
     *
     * @param element the element to be checked for containment in this list
     * @return true if this list contains the specified element
     */
    @Override
    public boolean contains(E element) {
        if (element == null) {
            for (E e : this.elements) {
                if (e == null) {
                    return true;
                }
            }
        } else {
            for (E e : this.elements) {
                if (element.equals(e)) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Removes all the elements from this list. The list will be empty after this call returns.
     */
    @Override
    public void clear() {
        for (int i = 0; i < this.size; i++) {
            this.elements[i] = null;
        }
        this.size = 0;
    }

    /**
     * Sorts this list into ascending order.
     */
    @Override
    public void sort() {
        Arrays.sort(elements, 0, size);
    }

    /**
     * Sorts this list into descending order.
     */
    @Override
    public void sortDescending() {
        Comparator<E> comparator = Collections.reverseOrder();
        Arrays.sort(elements, 0, size, comparator);
    }

    /**
     * Returns an iterator over the elements in this list in proper sequence.
     *
     * @return an iterator over the elements in this list in proper sequence
     */
    @Override
    public Iterator<E> iterator() {
        return new CustomIterator();
    }

    /**
     * Returns a string representation of this CustomArrayList object.
     *
     * @return a string representation of this CustomArrayList object
     */
    @Override
    public String toString() {
        return "CustomArrayList{" +
                "elements=" + Arrays.toString(elements) +
                ", size=" + size +
                '}';
    }

    /**
     * Returns an array containing all the elements in this list in proper sequence (from first to last element).
     *
     * @return an array containing all the elements in this list in proper sequence
     */
    @Override
    public E[] toArray() {
        return Arrays.copyOf(elements, size);
    }

    /**
     * Returns an iterator over the elements in this list in proper sequence.
     *
     * @return an iterator over the elements in this list in proper sequence
     */
    private class CustomIterator implements Iterator<E> {
        private int current = 0;

        @Override
        public boolean hasNext() {
            return current < size();
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return elements[current++];
        }
    }

    /**
     * Sorts the elements of the list in the order defined by the passed comparator.
     *
     * @param comparator the comparator that defines the order of sorting for the list elements
     * @throws NullPointerException if the passed comparator is null
     */
    @Override
    public void quicksort(Comparator<? super E> comparator) {
        quicksort(comparator, 0, size - 1);
    }

    /**
     * Recursive method for sorting the elements of the list in the order defined by the passed comparator.
     *
     * @param comparator the comparator that defines the order of sorting for the list elements
     * @param low        the index of the first element in the list to be sorted
     * @param high       the index of the last element in the list to be sorted
     */
    private void quicksort(Comparator<? super E> comparator, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(comparator, low, high);
            quicksort(comparator, low, pivotIndex - 1);
            quicksort(comparator, pivotIndex + 1, high);
        }
    }

    /**
     * Method for partitioning the list into two parts relative to a pivot element.
     *
     * @param comparator the comparator that defines the order of sorting for the list elements
     * @param low        the index of the first element in the list to be partitioned
     * @param high       the index of the last element in the list to be partitioned
     * @return the index of the pivot element after partitioning the list
     */
    private int partition(Comparator<? super E> comparator, int low, int high) {
        int mid = low + (high - low) / 2;
        E pivot = elements[mid];

        swap(mid, high);

        int i = low;
        for (int j = low; j < high; j++) {
            if (comparator.compare(elements[j], pivot) < 0) {
                swap(i, j);
                i++;
            }
        }

        swap(i, high);

        return i;
    }

    /**
     * Swaps the elements at the specified indices in this list.
     *
     * @param i the index of the first element to swap
     * @param j the index of the second element to swap
     */
    private void swap(int i, int j) {
        E temp = elements[i];
        elements[i] = elements[j];
        elements[j] = temp;
    }
}

