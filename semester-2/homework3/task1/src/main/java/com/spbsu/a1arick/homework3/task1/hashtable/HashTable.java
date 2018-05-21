package com.spbsu.a1arick.homework3.task1.hashtable;

import com.spbsu.a1arick.homework3.task1.hash.HashFunction;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;

/**
 * Hash table implementation using {@link LinkedList} for buckets
 *
 * @param <K> object type for keys
 * @param <V> object type for values
 */
public class HashTable<K, V> {
    private ArrayList<LinkedList<Entry<K, V>>> table;
    private HashFunction<K> hashFunction;
    private int size = 0;
    private int conflictNumber = 0;

    public HashTable(int size, HashFunction<K> hashFunction) {
        table = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            table.add(new LinkedList<>());
        }
        this.hashFunction = hashFunction;
    }

    public void add(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("Key is null");
        }
        int index = hashFunction.hash(key) % table.size();
        LinkedList<Entry<K, V>> bucket = table.get(index);
        for (Entry<K, V> entry : bucket) {
            if (entry.key.equals(key)) {
                entry.key = key;
                entry.value = value;
                return;
            }
        }
        if (bucket.size() > 0) {
            conflictNumber++;
        }
        bucket.add(new Entry<>(key, value));
        size++;
    }

    public boolean delete(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key is null");
        }
        int index = hashFunction.hash(key) % table.size();
        LinkedList<Entry<K, V>> bucket = table.get(index);
        if (bucket.remove(new Entry<>(key, (V) null))) {
            size--;
            if (bucket.size() > 0) {
                conflictNumber--;
            }
            return true;
        }
        return false;
    }

    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key is null");
        }
        int index = hashFunction.hash(key) % table.size();
        LinkedList<Entry<K, V>> bucket = table.get(index);
        for (Entry<K, V> entry : bucket) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
        }
        return null;
    }

    public int size() {
        return size;
    }

    public int getConflictNumber() {
        return conflictNumber;
    }

    public int getMaxListSize() {
        int result = 0;
        for (LinkedList list : table) {
            result = Integer.max(result, list.size());
        }
        return result;
    }

    public double loadFactor() {
        return (double) size() / table.size();
    }

    private final static class Entry<K1, V1> {
        private K1 key;
        private V1 value;

        private Entry(K1 key, V1 value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Entry<?, ?> entry = (Entry<?, ?>) o;
            return Objects.equals(key, entry.key);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key);
        }
    }
}