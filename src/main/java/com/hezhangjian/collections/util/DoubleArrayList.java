package com.hezhangjian.collections.util;

import java.util.Arrays;

public class DoubleArrayList implements DoubleList {
    private static final int DEFAULT_CAPACITY = 10;

    private static final double[] DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA = {};

    double[] elementData;

    private int size;

    public DoubleArrayList() {
        this.elementData = DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA;
    }

    public DoubleArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new double[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
    }

    private double[] grow(int minCapacity) {
        int oldCapacity = elementData.length;
        if (oldCapacity > 0 || elementData != DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA) {
            int newCapacity = ListUtil.newLength(oldCapacity,
                    minCapacity - oldCapacity, /* minimum growth */
                    oldCapacity >> 1           /* preferred growth */);
            return elementData = Arrays.copyOf(elementData, newCapacity);
        } else {
            return elementData = new double[Math.max(DEFAULT_CAPACITY, minCapacity)];
        }
    }

    private double[] grow() {
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
    public double[] toArray() {
        return Arrays.copyOf(elementData, size);
    }

    @Override
    public double[] toArray(boolean clone) {
        return clone ? Arrays.copyOf(elementData, size) : elementData;
    }

    @Override
    public boolean add(double value) {
        add(value, elementData, size);
        return true;
    }

    @Override
    public double get(int index) {
        ListUtil.rangeCheck(index, size);
        return elementData[index];
    }

    @Override
    public double set(int index, double value) {
        ListUtil.rangeCheck(index, size);
        double oldValue = elementData[index];
        elementData[index] = value;
        return oldValue;
    }

    @Override
    public void add(int index, double value) {
        ListUtil.rangeCheck(index, size);
        final int s;
        double[] elementData;
        if ((s = size) == (elementData = this.elementData).length) {
            elementData = grow();
        }
        System.arraycopy(elementData, index,
                elementData, index + 1,
                s - index);
        elementData[index] = value;
        size = s + 1;
    }

    private void add(double value, double[] elementData, int s) {
        if (s == elementData.length) {
            elementData = grow();
        }
        elementData[s] = value;
        size = s + 1;
    }
}
