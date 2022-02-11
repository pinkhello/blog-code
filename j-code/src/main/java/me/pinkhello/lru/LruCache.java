package me.pinkhello.lru;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class LruCache<K, V> implements Cache<K,V> {

    private int size;
    private Map<K, DoublyLinkedList.Node<CacheElement<K,V>>> nodeHashMap;
    private DoublyLinkedList<CacheElement<K,V>> doublyLinkedList;

    public LruCache(int size) {
        this.size = size;
        this.nodeHashMap = new HashMap<>(size);
        this.doublyLinkedList = new DoublyLinkedList<>();
    }

    @Override
    public Optional<V> get(K k) {
        DoublyLinkedList.Node<CacheElement<K, V>> e = nodeHashMap.get(k);
        if (e != null) {
            return Optional.of(e.getV().getValue());
        }
        return Optional.empty();
    }

    @Override
    public boolean put(K k, V v) {
        CacheElement<K, V> element = new CacheElement<>(k, v);
        //找寻到了 key
        DoublyLinkedList.Node<CacheElement<K,V>> node;
        if (nodeHashMap.containsKey(k)) {
            DoublyLinkedList.Node<CacheElement<K,V>> n = nodeHashMap.get(k);
            node = doublyLinkedList.updateAndMoveToFront(n, element);
        } else {
            // 判断队列的大小 和 配置的指定大小是否一致是否已满
            if (size() >= size) {
                //执行LRU策略
                evict();
            }
            node = doublyLinkedList.add(element);
        }
        nodeHashMap.put(k, node);
        return true;
    }

    private boolean evict() {
        DoublyLinkedList.Node<CacheElement<K, V>> node = doublyLinkedList.removeTail();
        nodeHashMap.remove(node.getV().getKey());
        return true;
    }

    @Override
    public int size() {
        return this.doublyLinkedList.size();
    }

    public void print() {
        doublyLinkedList.print();
    }


//    @Override
//    public boolean isEmpty() {
//        return size() == 0;
//    }

//    @Override
//    public void clear() {
//        nodeHashMap.clear();
//        doublyLinkedList.clear();
//    }

}
