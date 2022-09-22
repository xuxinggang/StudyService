package com.xxg.study;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@MapperScan("com.xxg.study.mapper")
@ComponentScan(basePackages = {"com.xxg.study.redis","com.xxg.study.mapper","com.xxg.study.controller","com.xxg.study.config","com.xxg.study.aspect","com.xxg.study.service","com.xxg.study.serializerUtils"})
public class ServiceForSpringSecurity  {
    public static void main(String[] args) {
         SpringApplication.run(ServiceForSpringSecurity.class, args);
        System.out.println("测试Security");
    }
}
