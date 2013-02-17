package com.es;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.google.common.eventbus.EventBus;

public class Publisher {
    private final EventBus eventBus;
    private final Random rand;
    private boolean running;
    
    public Publisher(EventBus eventBus) {
        this.eventBus = eventBus;
        this.rand = new Random();
        this.running = false;
    }

    public void stop() {
        running = false;
    }
    
    public void start() {
        running = true;

        new Thread(new IntegerProducer()).start();
    }
    
    private class IntegerProducer implements Runnable {
        public void run() {
            while(running) {
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                }
                System.out.println("Publishing at " + System.currentTimeMillis());
                eventBus.post(new IntegerEvent(rand.nextInt(100)));
                System.out.println("Done publishing at " + System.currentTimeMillis());
                System.out.println();
            }
        }
    }
}
