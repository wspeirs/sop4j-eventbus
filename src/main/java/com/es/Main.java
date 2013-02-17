package com.es;

import java.util.concurrent.TimeUnit;

import com.google.common.eventbus.EventBus;

public class Main {

    /**
     * @param args
     * @throws InterruptedException 
     */
    public static void main(String[] args) throws InterruptedException {
        // create a single event bus
        final EventBus eventBus = new EventBus("test-bus");
        
        // create a publisher
        final Publisher publisher = new Publisher(eventBus);
        
        // create 2 consumers
        final Consumer consumer1 = new Consumer("consumer 1", 200);
        final Consumer consumer2 = new Consumer("consumer 2", 100);
        
        // register the two consumers
        eventBus.register(consumer1);
        eventBus.register(consumer2);
        
        // start the publisher
        publisher.start();

        // wait around for 10 seconds
        TimeUnit.SECONDS.sleep(10);
        
        // shutdown the publisher
        publisher.stop();
    }

}
