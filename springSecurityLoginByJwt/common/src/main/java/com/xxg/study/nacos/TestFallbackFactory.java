package com.xxg.study.nacos;

import org.springframework.cloud.openfeign.FallbackFactory;

public class TestFallbackFactory implements FallbackFactory<Object> {

    //进入熔断接口
    @Override
    public Object create(Throwable cause) {
        return new FeignClientProvide() {
            @Override
            public String getNacos(String name) {
                return name+"hui退了！！！";
            }
        };
    }
}
