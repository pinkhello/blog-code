package me.pinkhello.lru;

public class CacheElement<K, V> {
    private K key;
    private V value;
    CacheElement(K k, V v) {
        this.key = k;
        this.value = v;
    }
    public K getKey() {
        return key;
    }
    public V getValue() {
        return value;
    }
}
