package com.atkeixn.ssyx;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureDemo4 {
    public static void main(String[] args) {
        ExecutorService executorService= Executors.newFixedThreadPool(3);
        CompletableFuture<Integer> objectCompletableFuture = CompletableFuture.supplyAsync(()->{
            System.out.println("当前线程"+Thread.currentThread().getName()+"begin");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            int value=1024;
            System.out.println(value);
            System.out.println("end");
            return value;
        }, executorService);
        CompletableFuture<Integer> objectCompletableFuture2 = CompletableFuture.supplyAsync(()->{
            System.out.println("当前线程"+Thread.currentThread().getName()+"begin");
            int value=200;
            System.out.println(value);
            System.out.println("end");
            return value;
        }, executorService);
        CompletableFuture<Void> all = CompletableFuture.allOf(objectCompletableFuture, objectCompletableFuture2);
        try {
            all.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        System.out.println("end...");

    }
}
