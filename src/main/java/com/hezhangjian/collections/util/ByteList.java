package com.hezhangjian.collections.util;

public interface ByteList {
    // Query Operations

    int size();

    boolean isEmpty();

    byte[] toArray();

    byte[] toArray(boolean clone);

    // Modify Operations

    boolean add(byte value);

    // Positional Access Operations

    byte get(int index);

    byte set(int index, byte value);

    void add(int index, byte value);
}
