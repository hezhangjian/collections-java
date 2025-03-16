package com.hezhangjian.collections.util;

import java.util.Arrays;

public class ShortArrayList implements ShortList {
    private static final int DEFAULT_CAPACITY = 10;

    private static final short[] DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA = {};

    short[] elementData;

    private int size;

    public ShortArrayList() {
        this.elementData = DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA;
    }

    public ShortArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new short[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
    }

    private short[] grow(int minCapacity) {
        int oldCapacity = elementData.length;
        if (oldCapacity > 0 || elementData != DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA) {
            int newCapacity = ListUtil.newLength(oldCapacity,
                    minCapacity - oldCapacity, /* minimum growth */
                    oldCapacity >> 1           /* preferred growth */);
            return elementData = Arrays.copyOf(elementData, newCapacity);
        } else {
            return elementData = new short[Math.max(DEFAULT_CAPACITY, minCapacity)];
        }
    }

    private short[] grow() {
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
    public short[] toArray() {
        return Arrays.copyOf(elementData, size);
    }

    @Override
    public short[] toArray(boolean clone) {
        return clone ? Arrays.copyOf(elementData, size) : elementData;
    }

    @Override
    public boolean add(short value) {
        add(value, elementData, size);
        return true;
    }

    @Override
    public short get(int index) {
        ListUtil.rangeCheck(index, size);
        return elementData[index];
    }

    @Override
    public short set(int index, short value) {
        ListUtil.rangeCheck(index, size);
        short oldValue = elementData[index];
        elementData[index] = value;
        return oldValue;
    }

    @Override
    public void add(int index, short value) {
        ListUtil.rangeCheck(index, size);
        final int s;
        short[] elementData;
        if ((s = size) == (elementData = this.elementData).length) {
            elementData = grow();
        }
        System.arraycopy(elementData, index,
                elementData, index + 1,
                s - index);
        elementData[index] = value;
        size = s + 1;
    }

    private void add(short value, short[] elementData, int s) {
        if (s == elementData.length) {
            elementData = grow();
        }
        elementData[s] = value;
        size = s + 1;
    }
}
