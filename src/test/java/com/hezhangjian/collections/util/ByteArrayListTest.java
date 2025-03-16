package com.hezhangjian.collections.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ByteArrayListTest {
    @Test
    void testAddItems() {
        ByteArrayList list = new ByteArrayList();
        list.add((byte) 1);
        list.add((byte) 2);
        list.add((byte) 3);
        list.add((byte) 4);
        list.add((byte) 5);
        Assertions.assertArrayEquals(new byte[] {1, 2, 3, 4, 5}, list.toArray());
    }
}
