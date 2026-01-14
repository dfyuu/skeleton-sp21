package bstmap;

import java.util.Set;
import java.util.Iterator;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private BSTNode root;

    private class BSTNode {
        K key;
        V value;
        BSTNode left;
        BSTNode right;
        int size;

        public BSTNode(K key, V value, int size) {
            this.key = key;
            this.value = value;
            this.size = size;
        }

        public BSTNode(K key, V value, BSTNode left, BSTNode right, int size) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
            this.size = size;
        }
    }

    private BSTNode search(BSTNode node, K key) {
        if (node == null) {
            return null;
        } else if (key == null) {
            throw new IllegalArgumentException("arguments is null");
        } else {
            int cmp = key.compareTo(node.key);
            if (cmp < 0) {
                return search(node.left, key);
            } else if (cmp > 0) {
                return search(node.right, key);
            } else {
                return node;
            }
        }
    }

    @Override
    public V get(K key) {
        BSTNode getNode = search(root, key);
        if (getNode == null) {
            return null;
        } else {
            return getNode.value;
        }
    }

    @Override
    public boolean containsKey(K key) {
        return (search(root, key) != null);
    }

    @Override
    public int size() {
        if (root == null) {
            return 0;
        } else {
            return root.size;
        }
    }

    private int size(BSTNode node) {
        if (node == null) {
            return 0;
        }
        return node.size;
    }

    @Override
    public void clear() {
        root = null;
    }

    @Override
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("argument is null");
        }
        root = put(root, key, value);
    }

    private BSTNode put(BSTNode node, K key, V value) {
        if (node == null) {
            return new BSTNode(key, value, 1);
        } else {
            int cmp = key.compareTo(node.key);
            if (cmp > 0) {
                node.right = put(node.right, key, value);
            } else if (cmp < 0) {
                node.left = put(node.left, key, value);
            } else {
                node.value = value;
            }
            node.size = 1 + size(node.left) + size(node.right);
            return node;
        }
    }

    public void printInOrder() {
        StringBuilder sb = new StringBuilder();
        inOrderBuilder(root, sb);
        System.out.println(sb);
    }

    private void inOrderBuilder(BSTNode node, StringBuilder sb) {
        if (node == null) {
            return;
        } else {
            inOrderBuilder(node.left, sb);
            sb.append(node.key).append(" ");
            inOrderBuilder(node.right, sb);
        }
    }

    private class BSTMapIter implements Iterator<K> {
        @Override
        public boolean hasNext() {
            throw new UnsupportedOperationException();
        }

        @Override
        public K next() {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }
}
