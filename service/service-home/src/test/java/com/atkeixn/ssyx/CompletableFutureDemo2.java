package com.atkeixn.ssyx;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureDemo2 {
    public static void main(String[] args) {
        System.out.println("main begin...");
        ExecutorService executorService= Executors.newFixedThreadPool(3);
        CompletableFuture<Integer> completableFuture=CompletableFuture.supplyAsync(()->{
            System.out.println("当前线程"+Thread.currentThread().getName());
            int value=1024;
            System.out.println(value);
            return value;
        },executorService);

        Integer i = null;
        try {
            i = completableFuture.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        System.out.println("main end..."+i);
    }
}
