package com.hashmap;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Author Bryan.C <br>
 * Date 2018/7/18 16:17
 */
public class LRULinkedHashMapCache<K, V> {
  private int capacity;
  private LinkedHashMap<K, V> cache;

  public LRULinkedHashMapCache(int capacity) {
    this.capacity = capacity;
    this.cache = new LinkedHashMap<K, V>(capacity, 0.75f, true) {
      // 定义put后的移除规则，大于容量就删除eldest
      @Override
      protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > capacity;
      }
    };
  }

  public V get(K key) {
    return cache.getOrDefault(key, null);
  }

  public void set(K key, V value) {
    cache.put(key, value);
  }
}
