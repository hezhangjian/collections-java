package com.hezhangjian.collections.util;

public interface IntList {
    // Query Operations

    int size();

    boolean isEmpty();

    int[] toArray();

    int[] toArray(boolean clone);

    // Modify Operations

    boolean add(int value);

    // Positional Access Operations

    int get(int index);

    int set(int index, int value);

    void add(int index, int value);
}
