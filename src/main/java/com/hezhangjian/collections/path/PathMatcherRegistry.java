package com.hezhangjian.collections.path;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * {@link PathMatcherRegistry} is a registry for managing and storing path patterns.
 * It allows adding, removing, and retrieving patterns, as well as finding the best matching pattern
 * for a given path.
 * <p>
 * This registry works in conjunction with {@link PathMatcher} to provide efficient path matching.
 * It supports Ant-style patterns with wildcards ({@code *}, {@code **}).
 * </p>
 */
public class PathMatcherRegistry {
    private static final String DEFAULT_PATH_SEPARATOR = "/";

    private final String separator;

    private final Map<String, String[]> patternMap;

    /**
     * Constructs a new {@code PathMatcherRegistry} with the default path separator ({@code "/"}).
     */
    public PathMatcherRegistry() {
        this(DEFAULT_PATH_SEPARATOR);
    }

    /**
     * Constructs a new {@code PathMatcherRegistry} with the specified path separator.
     * @param separator the separator to use for splitting patterns and paths (e.g., "/")
     */
    public PathMatcherRegistry(@NotNull String separator) {
        this.separator = separator;
        this.patternMap = new HashMap<>();
    }

    /**
     * Adds a pattern to the matcher for later use in matching operations.
     */
    public void addPattern(@NotNull String pattern) {
        patternMap.put(pattern, pattern.split(separator));
    }

    /**
     * Removes a pattern from the matcher.
     */
    public void removePattern(@NotNull String pattern) {
        patternMap.remove(pattern);
    }

    /**
     * Finds all patterns that match the given path.
     * This method iterates over all stored patterns and returns those that match the path.
     */
    public @NotNull List<String> findMatchingPattern(@NotNull String path) {
        String[] pathParts = path.split(separator);
        List<String> result = new ArrayList<>();
        for (Map.Entry<String, String[]> entry : patternMap.entrySet()) {
            String pattern = entry.getKey();
            String[] patternParts = entry.getValue();
            if (PathMatcher.match(patternParts, pathParts)) {
                result.add(pattern);
            }
        }
        return result;
    }

    /**
     * Find the best matching pattern for the given path.
     *
     * @param path the path to match against
     * @return the best matching pattern, or null if no match is found
     */
    public @Nullable String findBestMatchingPattern(@NotNull String path) {
        List<String> matchingPatterns = findMatchingPattern(path);
        return matchingPatterns.stream()
                .min((p1, p2) -> PathPatternComparator.getInstance().compare(patternMap.get(p1), patternMap.get(p2)))
                .orElse(null);
    }
}
