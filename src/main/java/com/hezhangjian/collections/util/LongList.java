package com.hezhangjian.collections.util;

public interface LongList {
    // Query Operations

    int size();

    boolean isEmpty();

    long[] toArray();

    long[] toArray(boolean clone);

    // Modify Operations

    boolean add(long value);

    // Positional Access Operations

    long get(int index);

    long set(int index, long value);

    void add(int index, long value);
}
