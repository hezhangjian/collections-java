package com.hezhangjian.collections.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class IntArrayListTest {
    @Test
    void testAddItems() {
        IntArrayList list = new IntArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        Assertions.assertArrayEquals(new int[] {1, 2, 3, 4, 5}, list.toArray());
    }
}
