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

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class TrieTreeTest {
    @Test
    void testInsertAndContains_singleElement() {
        List<Character> sequence = Collections.singletonList('a');
        TrieTree<Character> trie = new TrieTree<>();
        trie.insert(sequence);

        Assertions.assertTrue(trie.contains(sequence));
        Assertions.assertFalse(trie.contains(Collections.singletonList('b')));
    }

    @Test
    void testInsertAndContains_multipleElements() {
        List<Character> cat = Arrays.asList('c', 'a', 't');
        List<Character> car = Arrays.asList('c', 'a', 'r');

        TrieTree<Character> trie = new TrieTree<>();
        trie.insert(cat);
        trie.insert(car);

        Assertions.assertTrue(trie.contains(cat));
        Assertions.assertTrue(trie.contains(car));
        Assertions.assertFalse(trie.contains(Arrays.asList('c', 'a')));
        Assertions.assertFalse(trie.contains(Arrays.asList('d', 'o', 'g')));
    }

    @Test
    void testContains_nonExistent() {
        List<Character> cat = Arrays.asList('c', 'a', 't');
        TrieTree<Character> trie = new TrieTree<>();
        trie.insert(cat);

        Assertions.assertFalse(trie.contains(Arrays.asList('d', 'o', 'g')));
        Assertions.assertFalse(trie.contains(Arrays.asList('c', 'a', 'r')));
    }

    @Test
    void testDelete_singleElement() {
        List<Character> sequence = Collections.singletonList('a');
        TrieTree<Character> trie = new TrieTree<>();
        trie.insert(sequence);

        Assertions.assertTrue(trie.delete(sequence));
        Assertions.assertFalse(trie.contains(sequence));
    }

    @Test
    void testDelete_multipleElements() {
        List<Character> cat = Arrays.asList('c', 'a', 't');
        List<Character> car = Arrays.asList('c', 'a', 'r');
        TrieTree<Character> trie = new TrieTree<>();
        trie.insert(cat);
        trie.insert(car);

        Assertions.assertTrue(trie.delete(cat));
        Assertions.assertFalse(trie.contains(cat));
        // car should still exist
        Assertions.assertTrue(trie.contains(car));
    }

    @Test
    void testDelete_nonExistent() {
        List<Character> cat = Arrays.asList('c', 'a', 't');
        TrieTree<Character> trie = new TrieTree<>();
        trie.insert(cat);

        Assertions.assertFalse(trie.delete(Arrays.asList('d', 'o', 'g')));
        Assertions.assertFalse(trie.delete(Arrays.asList('c', 'a')));
        // original sequence still exists
        Assertions.assertTrue(trie.contains(cat));
    }

    @Test
    void testDelete_prefixOfExisting() {
        List<Character> cat = Arrays.asList('c', 'a', 't');
        TrieTree<Character> trie = new TrieTree<>();
        trie.insert(cat);

        List<Character> ca = Arrays.asList('c', 'a');
        // ca is prefix, not a complete sequence
        Assertions.assertFalse(trie.delete(ca));
        Assertions.assertTrue(trie.contains(cat));
    }

    @Test
    void testMultipleOperations() {
        List<Character> cat = Arrays.asList('c', 'a', 't');
        List<Character> car = Arrays.asList('c', 'a', 'r');
        List<Character> dog = Arrays.asList('d', 'o', 'g');

        // Insert multiple sequences
        TrieTree<Character> trie = new TrieTree<>();
        trie.insert(cat);
        trie.insert(car);
        trie.insert(dog);

        // Verify all exist
        Assertions.assertTrue(trie.contains(cat));
        Assertions.assertTrue(trie.contains(car));
        Assertions.assertTrue(trie.contains(dog));

        // Delete one
        Assertions.assertTrue(trie.delete(car));

        // Verify results
        Assertions.assertTrue(trie.contains(cat));
        Assertions.assertFalse(trie.contains(car));
        Assertions.assertTrue(trie.contains(dog));

        // Re-insert and verify
        trie.insert(car);
        Assertions.assertTrue(trie.contains(car));
    }
}
