package com.atkeixn.ssyx;

import javafx.application.Application;
import org.apache.catalina.core.ApplicationContext;
import org.apache.naming.factory.BeanFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureDemo1 {
    public static void main(String[] args){
        ExecutorService executorService= Executors.newFixedThreadPool(3);
        CompletableFuture<Void> completableFuture=CompletableFuture.runAsync(()->{
            System.out.println("当前线程"+Thread.currentThread().getName());
        },executorService);


    }
}
