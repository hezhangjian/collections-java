# Collections Java

![License](https://img.shields.io/badge/license-Apache2.0-green)
![Language](https://img.shields.io/badge/language-Java-blue.svg)
[![version](https://img.shields.io/github/v/tag/hezhangjian/collections-java?label=release&color=blue)](https://github.com/hezhangjian/collections-java/releases)

This project is a Java-based library implementing common data structures and algorithms.

## Prerequisites

- Java 8 or higher

## Install

Add this dependency to your project's POM:

```xml
<dependency>
    <groupId>io.github.hezhangjian</groupId>
    <artifactId>collections</artifactId>
    <version>0.1.0</version>
</dependency>
```

## Support Data Structures

### PathMatcher

The `PathMatcher` class provides a simple yet powerful way to match paths (e.g., URLs) against predefined patterns. It supports custom separators and wildcards (`*`).

#### Usage Example

```java
PathMatcher matcher = new PathMatcher();
matcher.addPattern("/api/users/*");
matcher.addPattern("/api/posts");

System.out.println(matcher.match("/api/users/123", "/api/users/*")); // true
System.out.println(matcher.match("/api/posts", "/api/posts"));       // true
System.out.println(matcher.findMatchingPattern("/api/users/123"));   // ["/api/users/*"]
```

### Trie

The `Trie<T>` class implements a prefix tree (trie) data structure, allowing storage and retrieval of generic sequences (e.g., lists). It supports insertion, deletion, and lookup operations.

#### Usage Example

```java
Trie<String> trie = new Trie<>();
trie.insert(List.of("a", "b", "c"));
System.out.println(trie.contains(List.of("a", "b", "c"))); // true
System.out.println(trie.contains(List.of("a", "b")));      // false
trie.delete(List.of("a", "b", "c"));
System.out.println(trie.contains(List.of("a", "b", "c"))); // false
```

### UnionFind

The `UnionFind<T>` class implements a generic disjoint-set data structure using a `HashMap`, with path compression for optimization. It supports operations to create sets, union sets, and check connectivity.

#### Usage Example
```java
UnionFind<Integer> uf = new UnionFind<>();
uf.makeSet(1);
uf.makeSet(2);
uf.makeSet(3);
uf.union(1, 2);
System.out.println(uf.connected(1, 2)); // true
System.out.println(uf.connected(2, 3)); // false
System.out.println(uf.count());         // 2
```
