package com.es;

import java.util.concurrent.TimeUnit;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.Subscribe;

public class ConcurrentConsumer {
    private final String name;
    private final long sleep;
    
    public ConcurrentConsumer(String name, long sleep) {
        this.name = name;
        this.sleep = sleep;
    }
    
    @Subscribe @AllowConcurrentEvents
    public void getIntegerEvent(Integer event) throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(sleep);
        System.out.println(System.currentTimeMillis() + ":\t" + name + " got " + event);
    }
}
