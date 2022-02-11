package me.pinkhello.lru;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class LruCacheTest {

    @Test
    public void testCachePut() {
        LruCache<String,String> cache = new LruCache<>(3);
        cache.put("1", "test1");
        cache.print(); // 看对象是不是在头上
        cache.put("2", "test2");
        cache.print();
        cache.put("3", "test3");
        cache.print();
        assertEquals("test1", cache.get("1").get());
        assertEquals("test2", cache.get("2").get());
        assertEquals("test3", cache.get("3").get());
    }

    @Test
    public void testCacheLru() {
        LruCache<String,String> cache = new LruCache<>(3);
        cache.put("1", "test1");
        cache.put("2", "test2");
        cache.put("3", "test3");
        cache.put("4", "test4");
        assertFalse(cache.get("1").isPresent());
    }

}
