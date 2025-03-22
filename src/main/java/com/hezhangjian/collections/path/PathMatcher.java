package com.hezhangjian.collections.path;

import org.jetbrains.annotations.NotNull;

/**
 * {@link PathMatcher} is responsible for matching paths against predefined patterns.
 * It supports Ant-style path patterns, including single-segment wildcards ({@code *}) and
 * multi-segment wildcards ({@code **}).
 */
public class PathMatcher {
    /**
     * Matches a path against a pattern using the default path separator ({@code "/"}).
     * This method supports Ant-style path patterns with wildcards:
     * <ul>
     *   <li>{@code *} - matches exactly one path segment</li>
     *   <li>{@code **} - matches zero or more path segments</li>
     * </ul>
     * @param pattern The pattern to match against
     * @param path The path to match
     * @return {@code true} if the path matches the pattern, {@code false} otherwise
     */
    public static boolean match(@NotNull String pattern, @NotNull String path) {
        String[] patternParts = pattern.split("/");
        String[] pathParts = path.split("/");
        return match(patternParts, pathParts);
    }

    /**
     * Matches a path against a pattern, where both are provided as arrays of segments.
     * This method supports Ant-style path patterns with wildcards:
     * <ul>
     *   <li>{@code *} - matches exactly one path segment</li>
     *   <li>{@code **} - matches zero or more path segments</li>
     * </ul>
     * @param patternParts The pattern segments to match against
     * @param pathParts The path segments to match
     * @return {@code true} if the path matches the pattern, {@code false} otherwise
     */
    public static boolean match(@NotNull String[] patternParts, @NotNull String[] pathParts) {
        int patternIdxStart = 0;
        int patternIdxEnd = patternParts.length - 1;
        int pathIdxStart = 0;
        int pathIdxEnd = pathParts.length - 1;
        while (patternIdxStart <= patternIdxEnd && pathIdxStart <= pathIdxEnd) {
            if (patternParts[patternIdxStart].equals("**")) {
                return true;
            } else if (patternParts[patternIdxStart].equals("*")) {
                patternIdxStart++;
                pathIdxStart++;
            } else if (patternParts[patternIdxStart].equals(pathParts[pathIdxStart])) {
                patternIdxStart++;
                pathIdxStart++;
            } else {
                return false;
            }
        }
        if (patternIdxStart == patternIdxEnd) {
            if (patternParts[patternIdxEnd].equals("**")) {
                return true;
            }
        }
        return patternIdxStart > patternIdxEnd && pathIdxStart > pathIdxEnd;
    }
}
