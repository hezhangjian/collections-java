/*
 * Copyright 2025 hezhangjian <hezhangjian97@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hezhangjian.collections.trie;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

public class TrieNode<V> {
    private final Map<V, TrieNode<V>> children;

    @Setter
    private V val;

    @Setter
    @Getter
    private boolean end;

    public TrieNode() {
        this(null);
    }

    public TrieNode(V val) {
        this.val = val;
        this.children = new HashMap<>();
    }

    public V val() {
        return val;
    }

    public Map<V, TrieNode<V>> children() {
        return children;
    }
}
