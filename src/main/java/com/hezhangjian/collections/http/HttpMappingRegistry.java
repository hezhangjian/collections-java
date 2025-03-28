package com.hezhangjian.collections.http;

import com.hezhangjian.collections.path.PathMatcher;
import com.hezhangjian.collections.path.PathPatternComparator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpMappingRegistry {
    private final String separator;

    private final Map<HttpMapping, String[]> mappingMap;

    public HttpMappingRegistry() {
        this.separator = "/";
        this.mappingMap = new HashMap<>();
    }

    public void addMapping(@NotNull HttpMapping mapping) {
        mappingMap.put(mapping, mapping.pattern().split(separator));
    }

    public void removeMapping(@NotNull HttpMapping mapping) {
        mappingMap.remove(mapping);
    }

    public boolean match(@NotNull HttpMapping mapping, String method, String path) {
        String[] patternParts = mappingMap.get(mapping);
        if (patternParts == null) {
            return false;
        }

        if (!mapping.httpMethod().name().equalsIgnoreCase(method)) {
            return false;
        }

        String[] pathPatterns = path.split(separator);
        return PathMatcher.match(patternParts, pathPatterns);
    }

    private boolean match(@NotNull HttpMapping mapping, String method, String[] pathParts) {
        String[] patternParts = mappingMap.get(mapping);
        if (patternParts == null) {
            return false;
        }

        if (!mapping.httpMethod().name().equalsIgnoreCase(method)) {
            return false;
        }

        return PathMatcher.match(patternParts, pathParts);
    }

    public List<HttpMapping> findMatchMappings(@NotNull String method, @NotNull String path) {
        String[] pathParts = path.split(separator);
        List<HttpMapping> matchMappings = new ArrayList<>();
        for (Map.Entry<HttpMapping, String[]> entry : mappingMap.entrySet()) {
            if (match(entry.getKey(), method, pathParts)) {
                matchMappings.add(entry.getKey());
            }
        }
        return matchMappings;
    }

    public @Nullable HttpMapping findBestMatchingMapping(@NotNull String method, @NotNull String path) {
        List<HttpMapping> matchMappings = findMatchMappings(method, path);
        return matchMappings.stream()
            .min((m1, m2) -> PathPatternComparator.getInstance().compare(mappingMap.get(m1), mappingMap.get(m2)))
            .orElse(null);
    }
}
