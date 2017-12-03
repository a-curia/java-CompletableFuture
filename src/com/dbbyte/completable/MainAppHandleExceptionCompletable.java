package com.dbbyte.completable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

/**
 * @author adrian on 12/3/2017
 * @project udacityApp
 */
public class MainAppHandleExceptionCompletable {

    private static ArrayList<String> numbers = new ArrayList<String>(Arrays.asList("0", "1", "2"));

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        System.out.println("Requesting");

        CompletableFuture<String> future = createCF(3);

        future = future.thenApply(Integer::parseInt)
                .thenApply(r -> r * 2 * Math.PI)
                .thenApply(s -> "apply>> " + s)
//				.exceptionally(ex -> "Error: " + ex.getMessage())
                .handle((result, ex) -> {
                    if (result != null) {
                        return result;
                    } else {
                        return "Error handling: " + ex.getMessage();
                    }
                });

        // com.dbbyte.future.complete("foo");
        future.thenAccept(result -> System.out.println("accept: " + result));

        // other statements
        for (int i = 1; i <= 3; i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("running outside... " + i + " time");
        }

        // com.dbbyte.future.complete("foo");
        String contents = future.get();
        System.out.println("Future result: " + contents);
    }

    private static CompletableFuture<String> createCF(int index) {
        return CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                try {
                    System.out.println("inside com.dbbyte.future: waiting for detecting...");
                    for (int i = 1; i <= 5; i++) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("running inside Future... " + i + " sec");
                    }
                    System.out.println("inside com.dbbyte.future: done...");

                    return numbers.get(index);
                } catch (Throwable e) {
                    return "not detected";
                }
            }
        });
    }
}
