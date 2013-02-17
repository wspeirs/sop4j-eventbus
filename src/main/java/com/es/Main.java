package com.es;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("*** STARTING SYNC BUS ***");
        // create a synchronous event bus
        runEventBus(new EventBus("sync-bus"));

        // setup our thread pool to use for executing events
        final ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(3);
        executor.prestartAllCoreThreads();

        System.out.println("*** STARTING ASYNC BUS ***");
        // run the same tests with an asynchronous event bus
        runEventBus(new AsyncEventBus("async-bus", executor));
        
        TimeUnit.SECONDS.sleep(1); // wait one more second for everything to finish
        executor.shutdown();
    }
    
    public static void runEventBus(EventBus eventBus) throws InterruptedException {
        // create a publisher
        final Publisher publisher = new Publisher(eventBus);
        
        // create 2 consumers
        final Consumer consumer1 = new Consumer("consumer", 200);
        final ConcurrentConsumer concurrentConsumer = new ConcurrentConsumer("concurrent consumer", 100);
        
        // register the two consumers
        eventBus.register(consumer1);
        eventBus.register(concurrentConsumer);
        
        // start the publisher
        publisher.start();

        // wait around for 10 seconds
        TimeUnit.SECONDS.sleep(5);
        
        // shutdown the publisher
        publisher.stop();
    }

}
