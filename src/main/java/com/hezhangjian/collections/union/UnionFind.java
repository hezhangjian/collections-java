package com.hezhangjian.collections.union;

import java.util.HashMap;
import java.util.Objects;

/**
 * A generic Disjoint-Set (Union-Find) data structure implemented with HashMap,
 * using path compression but without rank optimization.
 * @param <T> the type of elements in the set
 */
public class UnionFind<T> {
    private final HashMap<T, T> map;

    private int count;

    public UnionFind() {
        this.map = new HashMap<>();
        this.count = 0;
    }

    /**
     * Creates a new set containing the given element if it doesn't exist.
     * @param t the element to add
     */
    public void makeSet(T t) {
        if (t == null) {
            throw new IllegalArgumentException("element cannot be null");
        }
        if (!map.containsKey(t)) {
            map.put(t, t);
            count++;
        }
    }

    /**
     * Finds the representative (root) of the set that contains the element,
     * with path compression.
     * @param t the element to find
     * @return the root element of the set
     * @throws IllegalArgumentException if the element is not found
     */
    public T find(T t) {
        if (t == null) {
            throw new IllegalArgumentException("Element cannot be null");
        }
        if (!map.containsKey(t)) {
            throw new IllegalArgumentException("Element not found: " + t);
        }
        if (!Objects.equals(map.get(t), t)) {
            // path compression: make the found root the direct root
            map.put(t, find(map.get(t)));
        }
        return map.get(t);
    }

    /**
     * Merges the sets containing elements x and y (union by arbitrary choice).
     * @param x the first element
     * @param y the second element
     * @throws IllegalArgumentException if either element is not found
     */
    public void union(T x, T y) {
        if (x == null || y == null) {
            throw new IllegalArgumentException("Elements cannot be null");
        }
        makeSet(x); // Ensure x exists
        makeSet(y); // Ensure y exists
        T rootX = find(x);
        T rootY = find(y);
        if (!Objects.equals(rootX, rootY)) {
            map.put(rootX, rootY); // Arbitrarily attach rootX under rootY
            count--;
        }
    }

    /**
     * Checks if two elements are in the same set.
     * @param x the first element
     * @param y the second element
     * @return true if x and y are connected, false otherwise
     */
    public boolean connected(T x, T y) {
        if (x == null || y == null) {
            return false;
        }
        if (!map.containsKey(x) || !map.containsKey(y)) {
            return false;
        }
        return Objects.equals(find(x), find(y));
    }

    /**
     * Returns the number of disjoint sets.
     * @return the number of disjoint sets
     */
    public int count() {
        return count;
    }
}
