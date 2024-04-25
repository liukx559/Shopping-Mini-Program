package com.atkexin.ssyx;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

//@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)//取消数据源自动配置
@EnableDiscoveryClient
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@EnableFeignClients(basePackages="com.atkexin.ssyx.product.client")
@ComponentScan({"com.atkexin.ssyx.search.*", "com.atkexin.ssyx.search.api"})
@Configurable
public class ServiceSearchApplication {

    public static void main(String[] args) {
//        SpringApplication app =new SpringApplication(ServiceSearchApplication.class);
//        app.setAdditionalProfiles("Elasticsearch");
//        app.run(args);
        SpringApplication.run(ServiceSearchApplication.class, args);
    }


}