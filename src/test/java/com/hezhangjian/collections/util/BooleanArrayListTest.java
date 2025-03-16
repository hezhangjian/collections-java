package com.hezhangjian.collections.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BooleanArrayListTest {
    @Test
    void testAddItems() {
        BooleanArrayList list = new BooleanArrayList();
        list.add(true);
        list.add(false);
        Assertions.assertArrayEquals(new boolean[]{true, false}, list.toArray());
    }
}
