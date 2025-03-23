package com.hezhangjian.collections.path;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PathMatcherTest {
    @Test
    public void testExactlyPatternMatch() {
        Assertions.assertTrue(PathMatcher.match("/users", "/users"));
        Assertions.assertTrue(PathMatcher.match("/users/zhangjian", "/users/zhangjian"));
        Assertions.assertFalse(PathMatcher.match("/users/zhangjian/family", "/users/zhangjian/friends"));
        Assertions.assertFalse(PathMatcher.match("/users/zhangjian", "/users"));
        Assertions.assertFalse(PathMatcher.match("/users/zhangjian/friends", "/users"));
    }

    @Test
    public void testWildcardPatternMatch() {
        Assertions.assertTrue(PathMatcher.match("/users/*", "/users/123"));
        Assertions.assertTrue(PathMatcher.match("/api/*/users", "/api/v1/users"));
        Assertions.assertTrue(PathMatcher.match("/api/*/users", "/api/v2/users"));
        Assertions.assertTrue(PathMatcher.match("/api/v1/*", "/api/v1/users"));
        Assertions.assertTrue(PathMatcher.match("/api/v1/*", "/api/v1/posts"));

        Assertions.assertFalse(PathMatcher.match("/api/*/users", "/api/v1/users/123")); // too many segments
        Assertions.assertFalse(PathMatcher.match("/api/*/users", "/api/v1")); // too few segments
        Assertions.assertFalse(PathMatcher.match("/api/v1/*", "/api/v2/users")); // wrong version

        // Test multiple wildcards
        Assertions.assertTrue(PathMatcher.match("/*/*/users", "/api/v1/users"));
        Assertions.assertTrue(PathMatcher.match("/*/*/users", "/app/admin/users"));

    }

    @Test
    public void testDoubleWildcardPatternMatch() {
        Assertions.assertTrue(PathMatcher.match("/users/**", "/users/123"));
        Assertions.assertTrue(PathMatcher.match("/users/**", "/users/123/456"));
        Assertions.assertTrue(PathMatcher.match("/users/123/456/**", "/users/123/456"));

        // Test exact matches
        Assertions.assertTrue(PathMatcher.match("/api/**/users", "/api/v1/users"));
        Assertions.assertTrue(PathMatcher.match("/api/**/users", "/api/v1/admin/users"));
        Assertions.assertTrue(PathMatcher.match("/api/**/users", "/api/v1/admin/group/users"));

        // Test with double wildcard at the end
        Assertions.assertTrue(PathMatcher.match("/api/v1/**", "/api/v1"));
        Assertions.assertTrue(PathMatcher.match("/api/v1/**", "/api/v1/users"));
        Assertions.assertTrue(PathMatcher.match("/api/v1/**", "/api/v1/users/123"));
        Assertions.assertTrue(PathMatcher.match("/api/v1/**", "/api/v1/users/123/posts"));

        // Test with double wildcard at the start
        Assertions.assertTrue(PathMatcher.match("/**/users", "/users"));
        Assertions.assertTrue(PathMatcher.match("/**/users", "/api/users"));
        Assertions.assertTrue(PathMatcher.match("/**/users", "/api/v1/users"));

        // Test non-matches
        Assertions.assertFalse(PathMatcher.match("/api/**/users", "/api")); // too few segments
        Assertions.assertFalse(PathMatcher.match("/api/**/users", "/app/v1/users")); // wrong prefix
    }
}
