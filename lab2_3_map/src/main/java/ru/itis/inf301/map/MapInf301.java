package ru.itis.inf301.map;

import java.util.Collection;
import java.util.Set;

public interface MapInf301<K, V> {
    /**
     * @return Предыдущее значение, если ключ уже есть в Map, или null
     */
    V put(K key, V value);
    V get(K key);
    boolean containsKey(K key);
    Collection<V> values();
    Set<K> keySet();
    V remove(K key);
}
