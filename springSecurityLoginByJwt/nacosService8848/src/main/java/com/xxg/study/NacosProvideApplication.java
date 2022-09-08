package com.xxg.study;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan("com.xxg.study.nacos.mapper")
@ComponentScan("com.xxg.study.nacos.*")
@EnableDiscoveryClient
@EnableFeignClients
@EnableSwagger2
public class NacosProvideApplication {
    public static void main(String[] args) {
        SpringApplication.run(NacosProvideApplication.class,args);
    }
}
