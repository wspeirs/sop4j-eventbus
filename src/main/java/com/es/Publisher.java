package com.es;

import java.util.concurrent.TimeUnit;

import com.google.common.eventbus.EventBus;

public class Publisher {
    private final EventBus eventBus;
    private boolean running;
    private Integer curInt;
    
    public Publisher(EventBus eventBus) {
        this.eventBus = eventBus;
        this.running = false;
        this.curInt = Integer.valueOf(0);
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
                Integer integer = new Integer(curInt++);
                
                System.out.println(System.currentTimeMillis() + ":\tPublishing " + integer);
                eventBus.post(integer);
                System.out.println(System.currentTimeMillis() + ":\tDone publishing " + integer);
                
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                }
            }
        }
    }
}
