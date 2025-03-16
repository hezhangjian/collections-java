package com.hezhangjian.collections.util;

public interface DoubleList {
    // Query Operations

    int size();

    boolean isEmpty();

    double[] toArray();

    double[] toArray(boolean clone);

    // Modify Operations

    boolean add(double value);

    // Positional Access Operations

    double get(int index);

    double set(int index, double value);

    void add(int index, double value);
}
