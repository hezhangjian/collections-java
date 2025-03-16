package com.hezhangjian.collections.util;

import java.util.Arrays;

public class CharArrayList implements CharList {
    private static final int DEFAULT_CAPACITY = 10;

    private static final char[] DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA = {};

    char[] elementData;

    private int size;

    public CharArrayList() {
        this.elementData = DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA;
    }

    public CharArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new char[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
    }

    private char[] grow(int minCapacity) {
        int oldCapacity = elementData.length;
        if (oldCapacity > 0 || elementData != DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA) {
            int newCapacity = ListUtil.newLength(oldCapacity,
                    minCapacity - oldCapacity, /* minimum growth */
                    oldCapacity >> 1           /* preferred growth */);
            return elementData = Arrays.copyOf(elementData, newCapacity);
        } else {
            return elementData = new char[Math.max(DEFAULT_CAPACITY, minCapacity)];
        }
    }

    private char[] grow() {
        return grow(size + 1);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public char[] toArray() {
        return Arrays.copyOf(elementData, size);
    }

    @Override
    public char[] toArray(boolean clone) {
        return clone ? Arrays.copyOf(elementData, size) : elementData;
    }

    @Override
    public boolean add(char value) {
        add(value, elementData, size);
        return true;
    }

    @Override
    public char get(int index) {
        ListUtil.rangeCheck(index, size);
        return elementData[index];
    }

    @Override
    public char set(int index, char value) {
        ListUtil.rangeCheck(index, size);
        char oldValue = elementData[index];
        elementData[index] = value;
        return oldValue;
    }

    @Override
    public void add(int index, char value) {
        ListUtil.rangeCheck(index, size);
        final int s;
        char[] elementData;
        if ((s = size) == (elementData = this.elementData).length) {
            elementData = grow();
        }
        System.arraycopy(elementData, index,
                elementData, index + 1,
                s - index);
        elementData[index] = value;
        size = s + 1;
    }

    private void add(char value, char[] elementData, int s) {
        if (s == elementData.length) {
            elementData = grow();
        }
        elementData[s] = value;
        size = s + 1;
    }
}
