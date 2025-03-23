package com.hezhangjian.collections.path;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PathMatcherTest {
    @Test
    public void testExactlyMatch() {
        Assertions.assertTrue(PathMatcher.match("/users", "/users"));
        Assertions.assertTrue(PathMatcher.match("/users/zhangjian", "/users/zhangjian"));
        Assertions.assertFalse(PathMatcher.match("/users/zhangjian/family", "/users/zhangjian/friends"));
        Assertions.assertFalse(PathMatcher.match("/users/zhangjian", "/users"));
        Assertions.assertFalse(PathMatcher.match("/users/zhangjian/friends", "/users"));
    }

    @Test
    public void testWildcardMatch() {
        Assertions.assertTrue(PathMatcher.match("/users/*", "/users/123"));
    }

    @Test
    public void testDoubleWildcardMatch() {
        Assertions.assertTrue(PathMatcher.match("/users/**", "/users/123"));
        Assertions.assertTrue(PathMatcher.match("/users/**", "/users/123/456"));
        Assertions.assertTrue(PathMatcher.match("/users/123/456/**", "/users/123/456"));
    }
}
