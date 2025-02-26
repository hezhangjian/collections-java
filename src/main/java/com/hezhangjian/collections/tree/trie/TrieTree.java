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

package com.hezhangjian.collections.tree.trie;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class TrieTree<T> {
    private final TrieNode<T> root;

    public TrieTree() {
        this.root = new TrieNode<>();
    }

    public void insert(@NotNull List<T> list) {
        if (list.isEmpty()) {
            throw new IllegalArgumentException("list cannot be empty");
        }

        TrieNode<T> current = root;

        for (T item : list) {
            current = current.children().computeIfAbsent(item, TrieNode::new);
        }

        current.setEnd(true);
        current.setData(list.get(list.size() - 1));
    }

    public boolean contains(@NotNull List<T> list) {
        if (list.isEmpty()) {
            throw new IllegalArgumentException("list cannot be empty");
        }

        TrieNode<T> node = findNode(list);
        return node != null && node.isEnd();
    }

    private TrieNode<T> findNode(@NotNull List<T> list) {
        TrieNode<T> current = root;
        for (T item : list) {
            current = current.children().get(item);
            if (current == null) {
                return null;
            }
        }
        return current;
    }

    public boolean delete(@NotNull List<T> list) {
        if (list.isEmpty()) {
            throw new IllegalArgumentException("list cannot be empty");
        }

        List<TrieNode<T>> auxList = new ArrayList<>();
        TrieNode<T> current = root;
        for (T item : list) {
            current = current.children().get(item);
            if (current == null) {
                return false;
            }
            auxList.add(current);
        }

        if (!auxList.get(auxList.size() - 1).isEnd()) {
            return false;
        }

        for (int i = auxList.size() - 1; i >= 0; i--) {
            TrieNode<T> node = auxList.get(i);
            if (node.children().isEmpty()) {
                TrieNode<T> aux;
                if (i == 0) {
                    aux = root;
                } else {
                    aux = auxList.get(i - 1);
                }
                aux.children().remove(node.data());
            } else {
                node.setEnd(false);
                break;
            }
        }

        return true;
    }
}
