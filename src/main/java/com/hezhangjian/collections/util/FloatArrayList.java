package com.hezhangjian.collections.util;

import java.util.Arrays;

public class FloatArrayList implements FloatList {
    private static final int DEFAULT_CAPACITY = 10;

    private static final float[] DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA = {};

    float[] elementData;

    private int size;

    public FloatArrayList() {
        this.elementData = DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA;
    }

    public FloatArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new float[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
    }

    private float[] grow(int minCapacity) {
        int oldCapacity = elementData.length;
        if (oldCapacity > 0 || elementData != DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA) {
            int newCapacity = ListUtil.newLength(oldCapacity,
                    minCapacity - oldCapacity, /* minimum growth */
                    oldCapacity >> 1           /* preferred growth */);
            return elementData = Arrays.copyOf(elementData, newCapacity);
        } else {
            return elementData = new float[Math.max(DEFAULT_CAPACITY, minCapacity)];
        }
    }

    private float[] grow() {
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
    public float[] toArray() {
        return Arrays.copyOf(elementData, size);
    }

    @Override
    public float[] toArray(boolean clone) {
        return clone ? Arrays.copyOf(elementData, size) : elementData;
    }

    @Override
    public boolean add(float value) {
        add(value, elementData, size);
        return true;
    }

    @Override
    public float get(int index) {
        ListUtil.rangeCheck(index, size);
        return elementData[index];
    }

    @Override
    public float set(int index, float value) {
        ListUtil.rangeCheck(index, size);
        float oldValue = elementData[index];
        elementData[index] = value;
        return oldValue;
    }

    @Override
    public void add(int index, float value) {
        ListUtil.rangeCheck(index, size);
        final int s;
        float[] elementData;
        if ((s = size) == (elementData = this.elementData).length) {
            elementData = grow();
        }
        System.arraycopy(elementData, index,
                elementData, index + 1,
                s - index);
        elementData[index] = value;
        size = s + 1;
    }

    private void add(float value, float[] elementData, int s) {
        if (s == elementData.length) {
            elementData = grow();
        }
        elementData[s] = value;
        size = s + 1;
    }
}
