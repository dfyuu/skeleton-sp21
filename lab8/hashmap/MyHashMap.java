package hashmap;

import java.util.*;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    protected class hashKeyIterator implements Iterator<K> {

        private int currentBucket;
        private int currentItem;
        private Iterator<Node> itBucket;

        public hashKeyIterator() {
            currentBucket = 0;
            currentItem = 0;
            itBucket = buckets[0].iterator();
        }

        @Override
        public boolean hasNext() {
            return currentItem < n;
        }

        @Override
        public K next() {
            currentItem += 1;
            if (itBucket.hasNext()) {
                return itBucket.next().key;
            } else {
                while (!itBucket.hasNext()) {
                    currentBucket += 1;
                    itBucket = buckets[currentBucket].iterator();
                }
                return itBucket.next().key;
            }
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    private int n;
    private int m;
    private double maxLoad;

    /** Constructors */
    public MyHashMap() {
        this(16, 0.75);
    }

    public MyHashMap(int initialSize) {
        this(initialSize, 0.75);
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        this.m = initialSize;
        this.maxLoad = maxLoad;

        buckets = (Collection<Node>[]) new Collection[initialSize];
        for (int i = 0; i < initialSize; i += 1) {
            buckets[i] = createBucket();
        }
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return null;
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new LinkedList<Node>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     *
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        return null;
    }

    private int hash(K key, int size) {
        int h = key.hashCode();
        return Math.floorMod(h, size);
    }

    @Override
    public void clear() {
        n = 0;
        for (int i = 0; i < m; i += 1) {
            buckets[i] = null;
        }
    }

    @Override
    public int size() {
        return n;
    }

    @Override
    public boolean containsKey(K key) {
        return (get(key) != null);
    }

    @Override
    public V get(K key) {
        if (key == null) throw new IllegalArgumentException("argument to get() is null");

        int i = hash(key, this.m);

        if (buckets[i] == null) {
            return null;
        }

        for (Node n : buckets[i]) {
            if (n.key.equals(key)) {
                return n.value;
            }
        }

        return null;
    }

    @Override
    public void put(K key, V value) {

        if (this.containsKey(key)) {
            int i = hash(key, m);
            for (Node nn : buckets[i]) {
                if (nn.key.equals(key)) {
                    nn.value = value;
                }
            }
        } else {
            if (this.n + 1 > this.maxLoad * m) {
                this.resize();
            }

            int i = hash(key, m);

            this.n += 1;
            Node nn = new Node(key, value);
            this.buckets[i].add(nn);
        }
    }

    private void resize() {
        int tempM = m * 2;
        Collection<Node>[] tempBuckets = (Collection<Node>[]) new Collection[tempM];
        for (int i = 0; i < tempM; i += 1) {
            tempBuckets[i] = createBucket();
        }

        for (int i = 0; i < m; i += 1) {
            for (Node n : buckets[i]) {
                K key = n.key;
                int j = hash(key, tempM);
                tempBuckets[j].add(n);
            }
        }

        this.m = tempM;
        this.buckets = tempBuckets;
    }

    @Override
    public Set<K> keySet() {
        Set<K> keySet = new HashSet<K>();

        for (K key : this) {
            keySet.add(key);
        }

        return keySet;
    }

    public Iterator<K> iterator() {
        return new hashKeyIterator();
    }

    @Override
    public V remove(K key) {
        if (key == null) throw new IllegalArgumentException("argument to remove() is null");

        if (!containsKey(key)) return null;

        int i = hash(key, m);
        for (Node nn: buckets[i]) {
            if (nn.key.equals(key)) {
                n -= 1;
                buckets[i].remove(nn);
                return nn.value;
            }
        }
        return null;
    }

    @Override
    public V remove(K key, V value) {

        if (key == null) throw new IllegalArgumentException("argument to remove() is null");

        if (!containsKey(key)) return null;

        int i = hash(key, m);
        for (Node nn: buckets[i]) {
            if (nn.key.equals(key) && nn.value.equals(value)) {
                n -= 1;
                buckets[i].remove(nn);
                return nn.value;
            }
        }
        return null;
    }
}
