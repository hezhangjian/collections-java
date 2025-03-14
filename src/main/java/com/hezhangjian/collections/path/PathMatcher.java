package com.hezhangjian.collections.path;

import org.jetbrains.annotations.NotNull;

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
            return false;
        }
        String[] pathParts = path.split(separator);
        return match(patternParts, pathParts);
    }

    public @NotNull List<String> findMatchingPattern(@NotNull String path) {
        List<String> result = new ArrayList<>();
        String[] pathParts = path.split(separator);
        for (Map.Entry<String, String[]> entry : patternMap.entrySet()) {
            String pattern = entry.getKey();
            String[] patternParts = entry.getValue();
            if (match(patternParts, pathParts)) {
                result.add(pattern);
            }
        }
        return result;
    }

    private boolean match(@NotNull String[] patternParts, @NotNull String[] pathParts) {
        if (pathParts.length < patternParts.length) {
            return false;
        }
        for (int i = 0; i < patternParts.length; i++) {
            String patternPart = patternParts[i];
            if ("*".equals(patternPart)) {
                continue;
            }
            if (!patternPart.equals(pathParts[i])) {
                return false;
            }
        }
        return true;
    }
}
