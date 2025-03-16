package com.hezhangjian.collections.util;

public interface CharList {
    // Query Operations

    int size();

    boolean isEmpty();

    char[] toArray();

    char[] toArray(boolean clone);

    // Modify Operations

    boolean add(char value);

    // Positional Access Operations

    char get(int index);

    char set(int index, char value);

    void add(int index, char value);
}
