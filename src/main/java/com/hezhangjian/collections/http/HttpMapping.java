package com.hezhangjian.collections.http;

import org.jetbrains.annotations.NotNull;

public class HttpMapping {
    private final HttpMethod httpMethod;

    private final String pattern;

    public HttpMapping(@NotNull HttpMethod httpMethod, @NotNull String pattern) {
        this.httpMethod = httpMethod;
        this.pattern = pattern;
    }

    @NotNull
    public HttpMethod httpMethod() {
        return httpMethod;
    }

    @NotNull
    public String pattern() {
        return pattern;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HttpMapping that = (HttpMapping) o;
        return httpMethod == that.httpMethod && pattern.equals(that.pattern);
    }

    @Override
    public int hashCode() {
        int result = httpMethod.hashCode();
        result = 31 * result + pattern.hashCode();
        return result;
    }
}
