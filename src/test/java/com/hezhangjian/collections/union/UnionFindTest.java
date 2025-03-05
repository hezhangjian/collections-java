package com.hezhangjian.collections.union;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

public class UnionFindTest {

    private UnionFind<String> unionFind;

    @BeforeEach
    void setUp() {
        unionFind = new UnionFind<>();
    }

    @Test
    void testMakeSetWithNullElement() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> unionFind.makeSet(null),
                "Should throw IllegalArgumentException for null element");
    }

    @Test
    void testMakeSetWithNewElement() {
        unionFind.makeSet("A");
        Assertions.assertEquals(1, unionFind.count(), "Count should be 1 after adding one element");
        Assertions.assertEquals("A", unionFind.find("A"), "Root should be the element itself");
    }

    @Test
    void testMakeSetWithExistingElement() {
        unionFind.makeSet("A");
        int initialCount = unionFind.count();
        unionFind.makeSet("A");
        Assertions.assertEquals(initialCount, unionFind.count(), "Count should not change for existing element");
        Assertions.assertEquals("A", unionFind.find("A"), "Root should remain the same");
    }

    @Test
    void testFindWithNullElement() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> unionFind.find(null),
                "Should throw IllegalArgumentException for null element");
    }

    @Test
    void testFindWithNonExistentElement() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> unionFind.find("A"),
                "Should throw IllegalArgumentException for non-existent element");
    }

    @Test
    void testFindWithSingleElement() {
        unionFind.makeSet("A");
        Assertions.assertEquals("A", unionFind.find("A"), "Should return the element itself as root");
    }

    @Test
    void testUnionWithNullElements() {
        unionFind.makeSet("A");
        Assertions.assertThrows(IllegalArgumentException.class, () -> unionFind.union(null, "A"),
                "Should throw IllegalArgumentException for null first element");
        Assertions.assertThrows(IllegalArgumentException.class, () -> unionFind.union("A", null),
                "Should throw IllegalArgumentException for null second element");
    }

    @Test
    void testUnionWithSameSet() {
        unionFind.makeSet("A");
        unionFind.makeSet("B");
        unionFind.union("A", "B");
        int initialCount = unionFind.count();
        unionFind.union("A", "B");
        Assertions.assertEquals(initialCount, unionFind.count(), "Count should not change when uniting same set");
    }

    @Test
    void testUnionWithDifferentSets() {
        unionFind.makeSet("A");
        unionFind.makeSet("B");
        unionFind.makeSet("C");
        Assertions.assertEquals(3, unionFind.count(), "Initial count should be 3");
        unionFind.union("A", "B");
        Assertions.assertEquals(2, unionFind.count(), "Count should decrease to 2 after first union");
        unionFind.union("B", "C");
        Assertions.assertEquals(1, unionFind.count(), "Count should decrease to 1 after second union");
        Assertions.assertTrue(unionFind.connected("A", "C"), "A and C should be connected");
    }

    @Test
    void testConnectedWithNullElements() {
        unionFind.makeSet("A");
        Assertions.assertFalse(unionFind.connected(null, "A"), "Should return false for null first element");
        Assertions.assertFalse(unionFind.connected("A", null), "Should return false for null second element");
    }

    @Test
    void testConnectedWithNonExistentElements() {
        Assertions.assertFalse(unionFind.connected("A", "B"), "Should return false for non-existent elements");
        unionFind.makeSet("A");
        Assertions.assertFalse(unionFind.connected("A", "B"), "Should return false if one element doesn't exist");
    }

    @Test
    void testConnectedWithSameSet() {
        unionFind.makeSet("A");
        unionFind.makeSet("B");
        Assertions.assertFalse(unionFind.connected("A", "B"), "A and B should not be connected initially");
        unionFind.union("A", "B");
        Assertions.assertTrue(unionFind.connected("A", "B"), "A and B should be connected after union");
    }

    @Test
    void testCountInitially() {
        Assertions.assertEquals(0, unionFind.count(), "Initial count should be 0");
    }

    @Test
    void testCountAfterMultipleSets() {
        unionFind.makeSet("A");
        unionFind.makeSet("B");
        unionFind.makeSet("C");
        Assertions.assertEquals(3, unionFind.count(), "Count should be 3 after adding three elements");
        unionFind.union("A", "B");
        Assertions.assertEquals(2, unionFind.count(), "Count should be 2 after uniting A and B");
    }

    @Test
    void testWithCustomObjects() {
        Set<String> set1 = new HashSet<>();
        set1.add("value1");
        Set<String> set2 = new HashSet<>();
        set2.add("value2");
        UnionFind<Set<String>> uf = new UnionFind<>();
        uf.makeSet(set1);
        uf.makeSet(set2);
        Assertions.assertEquals(2, uf.count(), "Count should be 2 with custom objects");
        uf.union(set1, set2);
        Assertions.assertEquals(1, uf.count(), "Count should be 1 after union");
        Assertions.assertTrue(uf.connected(set1, set2), "Sets should be connected after union");
    }
}
