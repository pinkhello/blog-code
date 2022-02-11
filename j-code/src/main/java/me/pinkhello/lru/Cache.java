package me.pinkhello.lru;

import java.util.Optional;

public interface Cache<K,V> {
    Optional<V> get(K k);
    boolean put(K k, V v);
    int size();
//    boolean isEmpty();
//    void clear();
}
