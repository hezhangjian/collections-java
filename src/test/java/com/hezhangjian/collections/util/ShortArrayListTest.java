package com.hezhangjian.collections.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ShortArrayListTest {
    @Test
    void testAddItems() {
        ShortArrayList list = new ShortArrayList();
        list.add((short) 1);
        list.add((short) 2);
        list.add((short) 3);
        list.add((short) 4);
        list.add((short) 5);
        Assertions.assertArrayEquals(new short[] {1, 2, 3, 4, 5}, list.toArray());
    }
}
