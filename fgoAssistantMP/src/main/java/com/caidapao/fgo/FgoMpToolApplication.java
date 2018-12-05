package com.caidapao.fgo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@ServletComponentScan
@SpringCloudApplication
public class FgoMpToolApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(FgoMpToolApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(FgoMpToolApplication.class, args);
    }

    @Bean
    @LoadBalanced
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Value("${crm.fileUpload.tempPath}")
    private String tempPath;
    @Value("${crm.fileUpload.uploadPath}")
    private String uploadPath;
    @Value("${crm.fileUpload.fileWebUrl}")
    private String fileWebUrl;
    @Value("${crm.img.default.spec:60X60,100X100,160X160,200X60,220X220,380X380,640X640}")
    private String imgSpec;

//    @Bean
//    public FileResolver fileResolver(){
//        return new FileResolver(tempPath, uploadPath, fileWebUrl, imgSpec);
//    }
}
