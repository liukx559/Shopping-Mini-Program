package com.atkeixn.ssyx;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureDemo3 {
    public static void main(String[] args) {
        ExecutorService executorService= Executors.newFixedThreadPool(3);
        CompletableFuture<Integer> objectCompletableFuture = CompletableFuture.supplyAsync(()->{
            System.out.println("当前线程"+Thread.currentThread().getName());
            int value=1024;
            System.out.println(value);
            return value;
        }, executorService).whenComplete((rs,exception)->{
            System.out.println("whenComplete"+rs);
        });



    }
}
