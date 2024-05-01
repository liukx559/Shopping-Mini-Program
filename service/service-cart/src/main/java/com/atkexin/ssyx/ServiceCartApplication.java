package com.atkexin.ssyx;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ServiceCartApplication {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}