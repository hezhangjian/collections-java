package com.hezhangjian.collections.path;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * {@link PathMatcher} implementation for path matching, typically used for url matching.
 */
public class PathMatcher {
    private static final String DEFAULT_PATH_SEPARATOR = "/";

    private final String separator;

    private final Map<String, String[]> patternMap;

    public PathMatcher() {
        this(DEFAULT_PATH_SEPARATOR);
    }

    public PathMatcher(@NotNull String separator) {
        this.separator = separator;
        this.patternMap = new HashMap<>();
    }

    public void addPattern(@NotNull String pattern) {
        patternMap.put(pattern, pattern.split(separator));
    }

    public void removePattern(@NotNull String pattern) {
        patternMap.remove(pattern);
    }

    public boolean match(@NotNull String pattern, @NotNull String path) {
        String[] patternParts = patternMap.get(pattern);
        if (patternParts == null) {
            patternParts = pattern.split(separator);
        }
        String[] pathParts = path.split(separator);
        return match(patternParts, pathParts);
    }

    public @NotNull List<String> findMatchingPattern(@NotNull String path) {
        String[] pathParts = path.split(separator);
        List<String> result = new ArrayList<>();
        for (Map.Entry<String, String[]> entry : patternMap.entrySet()) {
            String pattern = entry.getKey();
            String[] patternParts = entry.getValue();
            if (match(patternParts, pathParts)) {
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
                .min((p1, p2) -> {
                    String[] parts1 = patternMap.get(p1);
                    String[] parts2 = patternMap.get(p2);
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
                })
                .orElse(null);
    }

    private boolean match(@NotNull String[] patternParts, @NotNull String[] pathParts) {
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
