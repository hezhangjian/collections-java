package com.hezhangjian.collections.util;

import java.util.Arrays;

public class ByteArrayList implements ByteList {
    private static final int DEFAULT_CAPACITY = 10;

    private static final byte[] DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA = {};

    byte[] elementData;

    private int size;

    public ByteArrayList() {
        this.elementData = DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA;
    }

    public ByteArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new byte[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
    }

    private byte[] grow(int minCapacity) {
        int oldCapacity = elementData.length;
        if (oldCapacity > 0 || elementData != DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA) {
            int newCapacity = ListUtil.newLength(oldCapacity,
                    minCapacity - oldCapacity, /* minimum growth */
                    oldCapacity >> 1           /* preferred growth */);
            return elementData = Arrays.copyOf(elementData, newCapacity);
        } else {
            return elementData = new byte[Math.max(DEFAULT_CAPACITY, minCapacity)];
        }
    }

    private byte[] grow() {
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
    public byte[] toArray() {
        return Arrays.copyOf(elementData, size);
    }

    @Override
    public byte[] toArray(boolean clone) {
        return clone ? Arrays.copyOf(elementData, size) : elementData;
    }

    @Override
    public boolean add(byte value) {
        add(value, elementData, size);
        return true;
    }

    @Override
    public byte get(int index) {
        ListUtil.rangeCheck(index, size);
        return elementData[index];
    }

    @Override
    public byte set(int index, byte value) {
        ListUtil.rangeCheck(index, size);
        byte oldValue = elementData[index];
        elementData[index] = value;
        return oldValue;
    }

    @Override
    public void add(int index, byte value) {
        ListUtil.rangeCheck(index, size);
        final int s;
        byte[] elementData;
        if ((s = size) == (elementData = this.elementData).length) {
            elementData = grow();
        }
        System.arraycopy(elementData, index,
                elementData, index + 1,
                s - index);
        elementData[index] = value;
        size = s + 1;
    }

    private void add(byte value, byte[] elementData, int s) {
        if (s == elementData.length) {
            elementData = grow();
        }
        elementData[s] = value;
        size = s + 1;
    }
}
