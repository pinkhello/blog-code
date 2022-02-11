package me.pinkhello.lru;

public class DoublyLinkedList<T> {

    private int size;

    private Node<T> head;

    private Node<T> tail;

    public static class Node<T> {
        T v;
        Node<T> prefix;
        Node<T> next;

        public Node(T t, Node<T> prefix, Node<T> next) {
            this.v = t;
            this.prefix = prefix;
            this.next = next;
        }

        public T getV() {
            return v;
        }
    }

    public DoublyLinkedList() {
        clear();
    }

    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }


    public int size() {
        return size;
    }


    public Node<T> removeTail() {
        Node<T> old = tail;
        if (old == head) {
            clear();
        } else {
            tail = tail.prefix;
            tail.next = null;
            old.prefix = null;
            size--;
        }
        return old;
    }

    public Node<T> add(T element) {
        // 添加到头节点添加每次
        Node<T> node = new Node<>(element, head, head);
        if (head == null) {
            head = tail = node;
            head.prefix = null;
            tail.next = null;
        } else {
            head.prefix = node;
            head = node;
            head.prefix = null;
            tail.next = null;
        }
        size++;
        return head;
    }

    public Node<T> updateAndMoveToFront(Node<T> n, T element) {
        removeTail();
        return add(element);
    }

    public void print() {
        if (head != null) {
            Node<T> c = head;
            do {
                System.out.println(c.getV().toString());
                c = c.next;
            } while ( c != null);
        } else {
            System.out.println("cache empty");
        }
    }

}
