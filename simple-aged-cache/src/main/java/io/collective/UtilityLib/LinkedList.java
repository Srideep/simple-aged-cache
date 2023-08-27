package io.collective.UtilityLib;

class Node<K, V> {
    K key;
    V value;
    Node<K, V> next;

    public Node(K key, V value) {
        this.key = key;
        this.value = value;
        this.next = null;
    }
}

class LinkedList<K, V> {
    Node<K, V> head;

    public void add(K key, V value) {
        Node<K, V> newNode = new Node<>(key, value);
        if (head == null) {
            head = newNode;
        } else {
            Node<K, V> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    public V find(K key) {
        Node<K, V> current = head;
        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }

    public void remove(K key) {
        if (head == null) {
            return;
        }
        if (head.key.equals(key)) {
            head = head.next;
            return;
        }
        Node<K, V> current = head;
        while (current.next != null) {
            if (current.next.key.equals(key)) {
                current.next = current.next.next;
                return;
            }
            current = current.next;
        }
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        int count = 0;
        Node<K, V> current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }
}



