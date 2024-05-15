public class MyHashTable<K, V> {
    // Attributes
    private HashNode<K, V>[] chainArray;
    private int M;
    private int size;
    private static final int DEFAULT_CAPACITY = 11;
    private static final double LOAD_FACTOR_THRESHOLD = 0.75;

    public MyHashTable() {
        this(DEFAULT_CAPACITY);
    }

    public MyHashTable(int capacity) {
        this.M = capacity;
        this.chainArray = new HashNode[M];
        this.size = 0;
    }

    // Hash function
    private int hash(K key) {
        return Math.abs(key.hashCode() % M);
    }

    // Put method
    public void put(K key, V value) {
        int index = hash(key);
        HashNode<K, V> newNode = new HashNode<>(key, value);
        HashNode<K, V> head = chainArray[index];

        if (head == null) {
            chainArray[index] = newNode;
        } else {
            while (head.next != null) {
                if (head.key.equals(key)) {
                    head.value = value;
                    return;
                }
                head = head.next;
            }
            head.next = newNode;
        }
        size++;

        if ((1.0 * size) / M >= LOAD_FACTOR_THRESHOLD) {
            rehash();
        }
    }

    public V get(K key) {
        int index = hash(key);
        HashNode<K, V> head = chainArray[index];

        while (head != null) {
            if (head.key.equals(key)) {
                return head.value;
            }
            head = head.next;
        }
        return null;
    }

    public V remove(K key) {
        int index = hash(key);
        HashNode<K, V> head = chainArray[index];
        HashNode<K, V> prev = null;

        while (head != null) {
            if (head.key.equals(key)) {
                if (prev == null) {
                    chainArray[index] = head.next;
                } else {
                    prev.next = head.next;
                }
                size--;
                return head.value;
            }
            prev = head;
            head = head.next;
        }
        return null;
    }

    public boolean contains(V value) {
        for (int i = 0; i < M; i++) {
            HashNode<K, V> head = chainArray[i];
            while (head != null) {
                if (head.value.equals(value)) {
                    return true;
                }
                head = head.next;
            }
        }
        return false;
    }

    public K getKey(V value) {
        for (int i = 0; i < M; i++) {
            HashNode<K, V> head = chainArray[i];
            while (head != null) {
                if (head.value.equals(value)) {
                    return head.key;
                }
                head = head.next;
            }
        }
        return null;
    }

    private void rehash() {
        HashNode<K, V>[] oldChainArray = chainArray;
        M = 2 * M;
        chainArray = new HashNode[M];
        size = 0;

        for (HashNode<K, V> headNode : oldChainArray) {
            while (headNode != null) {
                put(headNode.key, headNode.value);
                headNode = headNode.next;
            }
        }
    }

    static class HashNode<K, V> {
        private K key;
        private V value;
        HashNode<K, V> next;

        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    public int getCapacity() {
        return this.M;
    }

    // Accessor method for chainArray
    public HashNode<K, V>[] getChainArray() {
        return this.chainArray;
    }
}
