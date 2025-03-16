package com.hezhangjian.collections.util;

public interface BooleanList {
    // Query Operations

    int size();

    boolean isEmpty();

    boolean[] toArray();

    boolean[] toArray(boolean clone);

    // Modify Operations

    boolean add(boolean value);

    // Positional Access Operations

    boolean get(int index);

    boolean set(int index, boolean value);

    void add(int index, boolean value);
}
