package me.pinkhello.cicular.buffer;

import org.junit.jupiter.api.Test;


public class CircularBufferThreadTest {

    CircularBuffer buffer =  new CircularBuffer(5);

    @Test
    public void testProducerConsumer() {


        new Thread(() -> {
            for (int i = 0; i < 10;) {
                if (buffer.offer("element:" + i)) {
                    System.out.println("produced " + i );
                    i++;
                }
            }
        }).start();

        new Thread(() -> {
            for (int i =0; i < 10; ) {
                String c = (String) buffer.poll();
                if ( c != null) {
                    System.out.println("consumed:" + c);
                }
            }
        }).start();


        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}