package com.caidapao.fgo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 服务开启时，先得启动注册中心==》服务提供者==》服务消费者，启动项目有先后顺序
 * Created by caidapao on 2017/11/17.
 * 服务注册中心、配置中心入口程序
 */
@EnableEurekaServer
@SpringBootApplication
public class FgoConfigServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(FgoConfigServerApplication.class, args);
    }
}
