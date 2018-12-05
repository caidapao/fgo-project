package com.caidapao.fgo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

/**
 * Created by frt on 2017/11/17.
 * 服务入口程序
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableAsync
public class FgoServicesApplication {
    public static void main(String[] args) {
        SpringApplication.run(FgoServicesApplication.class, args);
    }

    /**
     * 针对方法进行校验
     * @return
     */
    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor(){
        return new MethodValidationPostProcessor();
    }
}
