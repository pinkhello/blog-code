package me.pinkhello.cicular.buffer;


public class CircularBuffer<E> {
    public static final int DEFAULT_CAPACITY = 1000;

    private final E[] data;
    private final int capacity;
    private volatile int readSeq = 0, writeSeq = -1;

    public CircularBuffer(int capacity) {
        this.capacity = (capacity < 1) ? DEFAULT_CAPACITY : capacity;
        this.data = (E[]) new Object[this.capacity];
        this.readSeq = 0;
        this.writeSeq = -1;
    }

    public int size() {
        return writeSeq - readSeq + 1;
    }

    public boolean offer(E element) {
        boolean isFull = writeSeq - readSeq + 1 == capacity;
        if (!isFull) {
            int nextWriteSeq = writeSeq + 1;
            data[nextWriteSeq % capacity] = element;
            writeSeq++;
            return true;
        }
        return false;
    }

    public E poll() {
        boolean isEmpty = writeSeq < readSeq;
        if (!isEmpty) {
            E nextElement = data[readSeq % capacity];
            readSeq++;
            return nextElement;
        }
        return null;
    }

}
