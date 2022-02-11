package me.pinkhello.cicular.buffer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CircularBufferTest {

    CircularBuffer buffer;

    @BeforeEach
    public void initBuffer() {
        buffer = new CircularBuffer(10);
        assertTrue(buffer.offer("Wocao"));
    }

    @Test
    public void testOffer() {
        assertTrue(buffer.offer("mmp"));
        assertEquals(2, buffer.size());
    }

    @Test
    public void testPoll() {
        assertTrue(buffer.offer("mmp2"));
        assertEquals("Wocao", buffer.poll());
        assertEquals("mmp2", buffer.poll());
    }
}