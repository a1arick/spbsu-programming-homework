package com.spbsu.a1arick.homework4.task2;

import java.util.Collection;
import java.util.Iterator;

public class AVLTree<T extends Comparable<T>> implements Collection<T>  {

    private Node<T> root;

    private int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public boolean add(T t) {
        size++;
        root = insert(root, t);
        return true; // todo check it
    }

    @Override
    public boolean remove(Object o) {
        size--;
        root = remove(root, (T)o);
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        for (T t : c) {
            add(t);
        }
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        for (Object t : c) {
            remove(t);
        }
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    private static <K extends Comparable<K>> int height(Node<K> node) {
        return node != null ? node.height : 0;
    }

    private static <K extends Comparable<K>> int balanceFactor(Node<K> node) {
        return height(node.left) - height(node.right);
    }

    private static <K extends Comparable<K>> void fixHeight(Node<K> node) {
        node.height = Math.max(height(node.left), height(node.right)) + 1;
    }

    private static <K extends Comparable<K>> Node<K> rotateRight(Node<K> node) {
        Node<K> left = node.left;
        node.left = left.right;
        left.right = node;
        fixHeight(left);
        fixHeight(node);
        return left;
    }

    private static <K extends Comparable<K>> Node<K> inOrderSuccessor(Node<K> node, Node<K> root) {
        if(node.right != null)
            return findMin(node.right);
        Node<K> succ = null;
        while(root != null) {
            int compare = node.key.compareTo(root.key);
            if(compare < 0){
                succ = root;
                root = root.left;
            }
            else if(compare > 0) {
                root = root.right;
            }
            else
                break;
        }
        return succ;
    }

    private static <K extends Comparable<K>> Node<K> rotateLeft(Node<K> node) {
        Node<K> right = node.right;
        node.right = right.left;
        right.left = node;
        fixHeight(right);
        fixHeight(node);
        return right;
    }

    private static  <K extends Comparable<K>> Node<K> balance(Node<K> node) {
        fixHeight(node);
        int balanceFactor = balanceFactor(node);
        if (balanceFactor == 2) {
            if (balanceFactor(node.right) < 0) {
                node.right = rotateRight(node.right);
            }
            return rotateLeft(node);
        } else if (balanceFactor == -2) {
            if (balanceFactor(node.left) > 0) {
                node.left = rotateLeft(node.left);
            }
            return rotateRight(node);
        }
        return node;
    }

    private static <K extends Comparable<K>> Node<K> insert(Node<K> node, K key) {
        if (node == null)
            return new Node<>(key);
        else if (key.compareTo(node.key) < 0){
            node.left = insert(node.left, key);
        }
        else{
            node.right = insert(node.right, key);
        }
        return balance(node);
    }

    private static  <K extends Comparable<K>> Node<K> findMin(Node<K> node) {
        return node.left == null ? node : findMin(node.left);
    }

    private static  <K extends Comparable<K>> Node<K> removeMin(Node<K> node) {
        if (node.left == null)
            return node.right;
        node.left = removeMin(node.left);
        return balance(node);
    }

    private static  <K extends Comparable<K>> Node<K> remove(Node<K> node, K key) {
        if (node == null) return null;
        else {
            int compare = key.compareTo(node.key);
            if (compare < 0) {
                node.left = remove(node.left, key);
            }
            else if (compare > 0) {
                node.right = remove(node.right, key);
            }
            else {
                Node<K> left = node.left;
                Node<K> right = node.right;
                if (right == null)
                    return left;
                Node<K> min = findMin(right);
                min.right = removeMin(right);
                min.left = left;
                return balance(min);
            }
        }
        return balance(node);
    }

    private static final class Node<K>{
        private K key;
        private int height = 1;
        private Node<K> left = null;
        private Node<K> right = null;

        private Node(K key) {
            this.key = key;
        }
    }
}
