package com.dbbyte.executors;

/**
 * @author adrian on 12/3/2017
 * @project udacityApp
 */

// 1. Create WorkerThread
public class Worker  implements Runnable{

    private String message;

    public Worker(String message){
        this.message = message;
    }



    @Override
    public void run() {
        doJob();
    }

    private void doJob(){
        try{
            long id = Thread.currentThread().getId();
            System.out.println("Thread with " + id + " START PROCESS. Message = " + message);
            Thread.sleep(2000);
            System.out.println("Thread with " + id + " END");
            System.out.println();
        }catch(Exception e){
        }
    }

    public String getMessage() {
        return message;
    }

}