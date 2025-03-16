package com.hezhangjian.collections.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CharArrayListTest {
    @Test
    void testAddItems() {
        CharArrayList list = new CharArrayList();
        list.add((char) 1);
        list.add((char) 2);
        list.add((char) 3);
        list.add((char) 4);
        list.add((char) 5);
        Assertions.assertArrayEquals(new char[]{1, 2, 3, 4, 5}, list.toArray());
    }
}
