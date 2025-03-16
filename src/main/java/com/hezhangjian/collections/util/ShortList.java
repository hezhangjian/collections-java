package com.hezhangjian.collections.util;

public interface ShortList {
    // Query Operations

    int size();

    boolean isEmpty();

    short[] toArray();

    short[] toArray(boolean clone);

    // Modify Operations

    boolean add(short value);

    // Positional Access Operations

    short get(int index);

    short set(int index, short value);

    void add(int index, short value);
}
