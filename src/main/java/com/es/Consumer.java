package com.es;

import java.util.concurrent.TimeUnit;

import com.google.common.eventbus.Subscribe;

public class Consumer {
    private final String name;
    private final long sleep;
    
    public Consumer(String name, long sleep) {
        this.name = name;
        this.sleep = sleep;
    }
    
    @Subscribe
    public void getIntegerEvent(Integer event) throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(sleep);
        System.out.println(System.currentTimeMillis() + ":\t" + name + " got " + event);
    }
}
