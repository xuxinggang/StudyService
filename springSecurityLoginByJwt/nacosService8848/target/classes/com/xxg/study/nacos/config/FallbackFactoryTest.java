package com.xxg.study.nacos.config;


import com.xxg.study.nacos.feignClient.TestFallbackFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 将所有的熔断配置成bean
 */
@Configuration
public class FallbackFactoryTest {

    @Bean
    public TestFallbackFactory getTestFallbackFactory(){
         return new TestFallbackFactory();
    }
}
