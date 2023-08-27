package io.collective.UtilityLib;
import java.util.*;

public class HashMap<K, V> {
    private static final int SIZE = 16;
    private LinkedList<K, V>[] table;

    @SuppressWarnings("unchecked")
    public HashMap() {
        table = new LinkedList[SIZE];
        for (int i = 0; i < SIZE; i++) {
            table[i] = new LinkedList<>();
        }
    }

    private int getIndex(K key) {
        return Math.abs(key.hashCode()) % SIZE;
    }

    public void put(K key, V value) {
        int index = getIndex(key);
        table[index].add(key, value);
    }

    public V get(K key) {
        int index = getIndex(key);
        return table[index].find(key);
    }

    public boolean containsKey(K key) {
        int index = getIndex(key);
        return table[index].find(key) != null;
    }

    public void remove(K key) {
        int index = getIndex(key);
        table[index].remove(key);
    }

    public boolean isEmpty() {
        for (LinkedList<K, V> list : table) {
            if (list != null && !list.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public int size() {
        int count = 0;
        for (LinkedList<K, V> list : table) {
            if (list != null) {
                count += list.size();
            }
        }
        return count;
    }

    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();
        for (LinkedList<K, V> list : table) {
            if (list != null) {
                Node<K, V> current = list.head;
                while (current != null) {
                    keys.add(current.key);
                    current = current.next;
                }
            }
        }
        return keys;
    }
}
