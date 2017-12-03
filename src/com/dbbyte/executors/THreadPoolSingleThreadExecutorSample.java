package com.dbbyte.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author adrian on 12/3/2017
 * @project udacityApp
 */
public class THreadPoolSingleThreadExecutorSample {
    public static void main(String[] args) {
        // create ExecutorService
        ExecutorService executor = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 10; i++) {
            Runnable worker = new Worker(Integer.toString(i));
            executor.execute(worker);
        }

        executor.shutdown();
        while (!executor.isTerminated()) {
        }

        System.out.println("Finished all threads");
    }
}
