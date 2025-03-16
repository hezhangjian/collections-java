package com.hezhangjian.collections.util;

public interface FloatList {
    // Query Operations

    int size();

    boolean isEmpty();

    float[] toArray();

    float[] toArray(boolean clone);

    // Modify Operations

    boolean add(float value);

    // Positional Access Operations

    float get(int index);

    float set(int index, float value);

    void add(int index, float value);
}
