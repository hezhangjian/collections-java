package com.hezhangjian.collections.path;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PathMatcherTest {
    @Test
    void testExactlyMatch() {
        PathMatcher matcher = new PathMatcher();
        matcher.addPattern("/api/v1/users");
        matcher.addPattern("/api/v1/products");

        List<String> result = matcher.findMatchingPattern("/api/v1/users");
        Assertions.assertNotNull(result, "Matching pattern should be found");
        Assertions.assertEquals(1, result.size(), "FindMatchingPattern should return a single pattern");
        Assertions.assertEquals("/api/v1/users", result.get(0));

        Assertions.assertEquals(0, matcher.findMatchingPattern("/api/v1/orders").size(), "Non-existent path should return empty list");
    }

    @Test
    void testWildcardMatch() {
        PathMatcher matcher = new PathMatcher();

        // Add patterns with single wildcards
        matcher.addPattern("/api/*/users");
        matcher.addPattern("/api/v1/*");
        matcher.addPattern("/*/*/users");

        // Test exact matches
        assertTrue(matcher.match("/api/*/users", "/api/v1/users"));
        assertTrue(matcher.match("/api/*/users", "/api/v2/users"));
        assertTrue(matcher.match("/api/v1/*", "/api/v1/users"));
        assertTrue(matcher.match("/api/v1/*", "/api/v1/posts"));

        // Test non-matches
        assertFalse(matcher.match("/api/*/users", "/api/v1/users/123")); // too many segments
        assertFalse(matcher.match("/api/*/users", "/api/v1")); // too few segments
        assertFalse(matcher.match("/api/v1/*", "/api/v2/users")); // wrong version

        // Test multiple wildcards
        assertTrue(matcher.match("/*/*/users", "/api/v1/users"));
        assertTrue(matcher.match("/*/*/users", "/app/admin/users"));

        // Test best matching pattern
        assertEquals("/api/v1/*", matcher.findBestMatchingPattern("/api/v1/users"));
        assertEquals("/api/*/users", matcher.findBestMatchingPattern("/api/v2/users"));
    }

    @Test
    void testDoubleWildcardMatch() {
        PathMatcher matcher = new PathMatcher();

        // Add patterns with double wildcards
        matcher.addPattern("/api/**/users");
        matcher.addPattern("/api/v1/**");
        matcher.addPattern("/**/users");

        // Test exact matches
        assertTrue(matcher.match("/api/**/users", "/api/v1/users"));
        assertTrue(matcher.match("/api/**/users", "/api/v1/admin/users"));
        assertTrue(matcher.match("/api/**/users", "/api/v1/admin/group/users"));

        // Test with double wildcard at the end
        assertTrue(matcher.match("/api/v1/**", "/api/v1"));
        assertTrue(matcher.match("/api/v1/**", "/api/v1/users"));
        assertTrue(matcher.match("/api/v1/**", "/api/v1/users/123"));
        assertTrue(matcher.match("/api/v1/**", "/api/v1/users/123/posts"));

        // Test with double wildcard at the start
        assertTrue(matcher.match("/**/users", "/users"));
        assertTrue(matcher.match("/**/users", "/api/users"));
        assertTrue(matcher.match("/**/users", "/api/v1/users"));

        // Test non-matches
        assertFalse(matcher.match("/api/**/users", "/api")); // too few segments
        assertFalse(matcher.match("/api/**/users", "/app/v1/users")); // wrong prefix

        // Test best matching pattern
        assertEquals("/api/v1/**", matcher.findBestMatchingPattern("/api/v1/users"));
        assertEquals("/api/**/users", matcher.findBestMatchingPattern("/api/v2/admin/users"));
        assertEquals("/**/users", matcher.findBestMatchingPattern("/any/path/to/users"));
    }

    @Test
    void testFindBestMatchingPattern() {
        PathMatcher matcher = new PathMatcher();
        matcher.addPattern("/api/v1/users");
        matcher.addPattern("/api/v1/*");
        matcher.addPattern("/api/v1/*/play-football");

        Assertions.assertEquals("/api/v1/users", matcher.findBestMatchingPattern("/api/v1/users"));
        Assertions.assertEquals("/api/v1/*/play-football", matcher.findBestMatchingPattern("/api/v1/users/play-football"));
    }
}
