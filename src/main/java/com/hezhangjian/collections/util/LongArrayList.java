package com.hezhangjian.collections.util;

import java.util.Arrays;

public class LongArrayList implements LongList {
    private static final int DEFAULT_CAPACITY = 10;

    private static final long[] DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA = {};

    long[] elementData;

    private int size;

    public LongArrayList() {
        this.elementData = DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA;
    }

    public LongArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new long[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
    }

    private long[] grow(int minCapacity) {
        int oldCapacity = elementData.length;
        if (oldCapacity > 0 || elementData != DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA) {
            int newCapacity = ListUtil.newLength(oldCapacity,
                    minCapacity - oldCapacity, /* minimum growth */
                    oldCapacity >> 1           /* preferred growth */);
            return elementData = Arrays.copyOf(elementData, newCapacity);
        } else {
            return elementData = new long[Math.max(DEFAULT_CAPACITY, minCapacity)];
        }
    }

    private long[] grow() {
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
    public long[] toArray() {
        return Arrays.copyOf(elementData, size);
    }

    @Override
    public long[] toArray(boolean clone) {
        return clone ? Arrays.copyOf(elementData, size) : elementData;
    }

    @Override
    public boolean add(long value) {
        add(value, elementData, size);
        return true;
    }

    @Override
    public long get(int index) {
        ListUtil.rangeCheck(index, size);
        return elementData[index];
    }

    @Override
    public long set(int index, long value) {
        ListUtil.rangeCheck(index, size);
        long oldValue = elementData[index];
        elementData[index] = value;
        return oldValue;
    }

    @Override
    public void add(int index, long value) {
        ListUtil.rangeCheck(index, size);
        final int s;
        long[] elementData;
        if ((s = size) == (elementData = this.elementData).length) {
            elementData = grow();
        }
        System.arraycopy(elementData, index,
                elementData, index + 1,
                s - index);
        elementData[index] = value;
        size = s + 1;
    }

    private void add(long value, long[] elementData, int s) {
        if (s == elementData.length) {
            elementData = grow();
        }
        elementData[s] = value;
        size = s + 1;
    }
}
