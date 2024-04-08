package Model;

import java.util.*;

public class Dictionary<K, V> {
    private Map<K, V> entries;

    public Dictionary() {
        entries = new HashMap<>();
    }

    public Map<K, V> getEntries() {
        return entries;
    }

    public void addEntry(K key, V value) {
        entries.put(key, value);
    }

    public void removeEntry(K key) {
        entries.remove(key);
    }

    public V findEntry(K key) {
        return entries.get(key);
    }
}
