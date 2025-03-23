package com.hezhangjian.collections.path;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;

/**
 * A comparator for comparing path patterns.
 * This comparator can be used to find the best matching pattern among multiple patterns.
 * <p>
 * The comparison rules are:
 * <ul>
 *   <li>Exact matches are preferred over wildcard matches</li>
 *   <li>Single wildcard ({@code *}) matches are preferred over double wildcard ({@code **}) matches</li>
 *   <li>Shorter patterns are preferred over longer ones</li>
 * </ul>
 */
public class PathPatternComparator implements Comparator<String[]> {
    private static final PathPatternComparator INSTANCE = new PathPatternComparator();

    private PathPatternComparator() {
    }

    /**
     * Returns the singleton instance of the comparator.
     *
     * @return the singleton instance
     */
    public static PathPatternComparator getInstance() {
        return INSTANCE;
    }

    /**
     * Creates a comparator for comparing objects that have associated path patterns.
     * The comparator can be used to find the best matching pattern among multiple patterns.
     *
     * @param patternMap a map containing the patterns and their parts
     * @return a comparator that can be used to find the best matching pattern
     */
    public static <T> Comparator<T> createComparator(@NotNull java.util.Map<T, String[]> patternMap) {
        return (p1, p2) -> INSTANCE.compare(patternMap.get(p1), patternMap.get(p2));
    }

    @Override
    public int compare(@NotNull String[] parts1, @NotNull String[] parts2) {
        int loopCount = Math.min(parts1.length, parts2.length);
        for (int i = 0; i < loopCount; i++) {
            if (parts1[i].equals(parts2[i])) {
                continue;
            }
            if (parts1[i].equals("**")) {
                return 1;
            }
            if (parts2[i].equals("**")) {
                return -1;
            }
            if (parts1[i].equals("*")) {
                return 1;
            }
            if (parts2[i].equals("*")) {
                return -1;
            }
        }
        return parts2.length - parts1.length;
    }
}
