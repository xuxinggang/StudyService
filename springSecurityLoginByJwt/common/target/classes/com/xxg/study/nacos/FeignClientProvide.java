package com.xxg.study.nacos;

import com.xxg.study.config.FallbackFactoryTest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * FallbackFactoryTest:将所有的熔断配置成bean
 */
@FeignClient(value = "nacos-service" ,configuration = FallbackFactoryTest.class,fallbackFactory = TestFallbackFactory.class)
public interface FeignClientProvide {

    @GetMapping("/nacos/get/{name}")
     public String getNacos(@PathVariable String name);
}
