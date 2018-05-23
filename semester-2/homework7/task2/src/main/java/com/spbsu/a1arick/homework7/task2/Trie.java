package com.spbsu.a1arick.homework7.task2;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Objects;

/**
 * Trie data structure implementation
 */
public class Trie implements MySerializable {
    private final Trie[] children = new Trie[26];
    private boolean isTerminal = false;
    private int size = 0;

    /**
     * Adds string to the trie
     *
     * @param s string to add to the trie
     * @return {@code true} if string wasn't exist in the trie else {@code false}
     */
    public boolean add(String s) {
        checkNotNull(s);
        if (s.isEmpty()) {
            if (!isTerminal) {
                isTerminal = true;
                size++;
                return true;
            }
            return false;
        }
        char c = s.charAt(0);
        String next = s.substring(1);
        Trie trie = children[c - 'a'];
        if (trie == null) {
            trie = children[c - 'a'] = new Trie();
        }
        boolean newElementAdded = trie.add(next);
        size += newElementAdded ? 1 : 0;
        return newElementAdded;
    }

    /**
     * Checks if trie contains the string
     *
     * @param s string to check
     * @return {@code true} if trie contains the string else {@code false}
     */
    public boolean contains(String s) {
        checkNotNull(s);
        if (s.isEmpty()) return isTerminal;
        char c = s.charAt(0);
        Trie trie = children[c - 'a'];
        return trie != null && trie.contains(s.substring(1));
    }

    /**
     * Removes string from the trie
     *
     * @param s string to remove from the trie
     * @return {@code true} if string existed and was removed from the trie else {@code false}
     */
    public boolean remove(String s) {
        checkNotNull(s);
        if (s.isEmpty()) {
            if (isTerminal) {
                isTerminal = false;
                size--;
                return true;
            }
            return false;
        }
        char c = s.charAt(0);
        String next = s.substring(1);
        Trie trie = children[c - 'a'];
        if (trie == null) {
            return false;
        }
        boolean elementRemoved = trie.remove(next);
        size -= elementRemoved ? 1 : 0;
        return elementRemoved;
    }


    /**
     * @return the number of unique strings in the trie
     */
    public int size() {
        return size;
    }

    /**
     * Counts the number of strings that start with the prefix
     *
     * @param s prefix
     * @return the number of strings that start with the prefix
     */
    public int howManyStartWithPrefix(String s) {
        checkNotNull(s);
        if (s.isEmpty()) return size;
        char c = s.charAt(0);
        Trie trie = children[c - 'a'];
        if (trie == null) return 0;
        return trie.howManyStartWithPrefix(s.substring(1));
    }

    private static void checkNotNull(Object object) {
        if (object == null) {
            throw new IllegalArgumentException("Null value");
        }
    }

    @Override
    public void serialize(OutputStream out) throws IOException {
        out.write(size);
        out.write(isTerminal ? 1 : 0);
        for (Trie child : children) {
            out.write(child != null ? 1 : 0);
            if (child != null) {
                child.serialize(out);
            }
        }
    }

    @Override
    public void deserialize(InputStream in) throws IOException {
        size = in.read();
        isTerminal = in.read() == 1;
        for (int i = 0; i < children.length; i++) {
            boolean exist = in.read() == 1;
            Trie child = null;
            if (exist) {
                child = new Trie();
                child.deserialize(in);
            }
            children[i] = child;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trie trie = (Trie) o;
        return isTerminal == trie.isTerminal &&
                size == trie.size &&
                Arrays.equals(children, trie.children);
    }

    @Override
    public int hashCode() {

        int result = Objects.hash(isTerminal, size);
        result = 31 * result + Arrays.hashCode(children);
        return result;
    }
}
