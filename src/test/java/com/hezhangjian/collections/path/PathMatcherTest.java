package com.hezhangjian.collections.path;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class PathMatcherTest {
    @Test
    void testFindMatchingPattern() {
        PathMatcher matcher = new PathMatcher();
        matcher.addPattern("/api/v1/users");
        matcher.addPattern("/api/v1/products");

        List<String> result = matcher.findMatchingPattern("/api/v1/users");
        Assertions.assertNotNull(result, "Matching pattern should be found");
        Assertions.assertEquals(1, result.size(), "FindMatchingPattern should return a single pattern");
        Assertions.assertEquals("/api/v1/users", result.get(0));

        Assertions.assertEquals(0, matcher.findMatchingPattern("/api/v1/orders").size(), "Non-existent path should return empty list");
    }
}
