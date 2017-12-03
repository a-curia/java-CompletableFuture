package com.dbbyte.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author adrian on 12/3/2017
 * @project udacityApp
 */
public class MainApp {

    static ExecutorService executor = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws Exception {

        Future<String> future = makeFuture("running Future");

        // other statements
        for (int i = 1; i <= 3; i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("running outside... " + i + " time"+" THREAD = "+Thread.currentThread().getName()+" - "+Thread.currentThread().getId());
        }

        String contents = future.get(); // get is blocking
        System.out.println(contents);
    }

    static Future<String> makeFuture(String inString) throws Exception {

        return executor.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                for (int i = 1; i <= 5; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("running inside Future... " + i + " sec"+" THREAD = "+Thread.currentThread().getName()+"["+Thread.currentThread().getId()+"]");
                }
                return "Done " + inString;
            }
        });

    }
}