package com.xxg.study.nacos.FeignClientImpl;

import com.xxg.study.nacos.FeignClientProvide;
import org.springframework.stereotype.Service;

@Service
public class FeignClientProvideImpl implements FeignClientProvide {
    @Override
    public String getNacos(String name) {
        return "nacos抽取公共服务"+name;
    }
}
