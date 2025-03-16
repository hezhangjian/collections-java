package com.hezhangjian.collections.util;

import java.util.Arrays;

public class BooleanArrayList implements BooleanList {
    private static final int DEFAULT_CAPACITY = 10;

    private static final boolean[] DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA = {};

    boolean[] elementData;

    private int size;

    public BooleanArrayList() {
        this.elementData = DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA;
    }

    public BooleanArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new boolean[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
    }

    private boolean[] grow(int minCapacity) {
        int oldCapacity = elementData.length;
        if (oldCapacity > 0 || elementData != DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA) {
            int newCapacity = ListUtil.newLength(oldCapacity,
                    minCapacity - oldCapacity, /* minimum growth */
                    oldCapacity >> 1           /* preferred growth */);
            return elementData = Arrays.copyOf(elementData, newCapacity);
        } else {
            return elementData = new boolean[Math.max(DEFAULT_CAPACITY, minCapacity)];
        }
    }

    private boolean[] grow() {
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
    public boolean[] toArray() {
        return Arrays.copyOf(elementData, size);
    }

    @Override
    public boolean[] toArray(boolean clone) {
        return clone ? Arrays.copyOf(elementData, size) : elementData;
    }

    @Override
    public boolean add(boolean value) {
        add(value, elementData, size);
        return true;
    }

    @Override
    public boolean get(int index) {
        ListUtil.rangeCheck(index, size);
        return elementData[index];
    }

    @Override
    public boolean set(int index, boolean value) {
        ListUtil.rangeCheck(index, size);
        boolean oldValue = elementData[index];
        elementData[index] = value;
        return oldValue;
    }

    @Override
    public void add(int index, boolean value) {
        ListUtil.rangeCheck(index, size);
        final int s;
        boolean[] elementData;
        if ((s = size) == (elementData = this.elementData).length) {
            elementData = grow();
        }
        System.arraycopy(elementData, index,
                elementData, index + 1,
                s - index);
        elementData[index] = value;
        size = s + 1;
    }

    private void add(boolean value, boolean[] elementData, int s) {
        if (s == elementData.length) {
            elementData = grow();
        }
        elementData[s] = value;
        size = s + 1;
    }
}
