package com.hezhangjian.collections.path;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class PathMatcherRegistryTest {
    @Test
    void testExactlyMatch() {
        PathMatcherRegistry matcherRegistry = new PathMatcherRegistry();
        matcherRegistry.addPattern("/api/v1/users");
        matcherRegistry.addPattern("/api/v1/products");

        List<String> result = matcherRegistry.findMatchingPattern("/api/v1/users");
        Assertions.assertNotNull(result, "Matching pattern should be found");
        Assertions.assertEquals(1, result.size(), "FindMatchingPattern should return a single pattern");
        Assertions.assertEquals("/api/v1/users", result.get(0));

        Assertions.assertEquals(0, matcherRegistry.findMatchingPattern("/api/v1/orders").size(), "Non-existent path should return empty list");
    }

    @Test
    void testWildcardMatch() {
        PathMatcherRegistry matcherRegistry = new PathMatcherRegistry();

        // Add patterns with single wildcards
        matcherRegistry.addPattern("/api/*/users");
        matcherRegistry.addPattern("/api/v1/*");
        matcherRegistry.addPattern("/*/*/users");

        // Test best matching pattern
        Assertions.assertEquals("/api/v1/*", matcherRegistry.findBestMatchingPattern("/api/v1/users"));
        Assertions.assertEquals("/api/*/users", matcherRegistry.findBestMatchingPattern("/api/v2/users"));
    }

    @Test
    void testDoubleWildcardMatch() {
        PathMatcherRegistry matcherRegistry = new PathMatcherRegistry();

        // Add patterns with double wildcards
        matcherRegistry.addPattern("/api/**/users");
        matcherRegistry.addPattern("/api/v1/**");
        matcherRegistry.addPattern("/**/users");

        // Test best matching pattern
        Assertions.assertEquals("/api/v1/**", matcherRegistry.findBestMatchingPattern("/api/v1/users"));
        Assertions.assertEquals("/api/**/users", matcherRegistry.findBestMatchingPattern("/api/v2/admin/users"));
        Assertions.assertEquals("/**/users", matcherRegistry.findBestMatchingPattern("/any/path/to/users"));
    }

    @Test
    void testFindBestMatchingPattern() {
        PathMatcherRegistry matcherRegistry = new PathMatcherRegistry();
        matcherRegistry.addPattern("/api/v1/users");
        matcherRegistry.addPattern("/api/v1/*");
        matcherRegistry.addPattern("/api/v1/*/play-football");

        Assertions.assertEquals("/api/v1/users", matcherRegistry.findBestMatchingPattern("/api/v1/users"));
        Assertions.assertEquals("/api/v1/*/play-football", matcherRegistry.findBestMatchingPattern("/api/v1/users/play-football"));
    }
}
