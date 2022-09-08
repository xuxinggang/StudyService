package com.xxg.study.nacos.feignClient;

import com.xxg.study.nacos.config.FallbackFactoryTest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * FallbackFactoryTest:将所有的熔断配置成bean
 */
@FeignClient(value = "nacos-provide" ,configuration = FallbackFactoryTest.class,fallbackFactory = TestFallbackFactory.class)
public interface FeignClientProvide {

    @GetMapping("/get/{name}")
     public String getNacos(@PathVariable String name);
}
