package me.pinkhello.lru;

import java.util.Optional;

public interface LruCache<K,V> {
    Optional<V> get(K k);
    boolean set(K k, V v);
    int size();
    boolean isEmpty();
    void clear();
}
