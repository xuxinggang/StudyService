package com.xxg.study.nacos.controller;

import com.xxg.study.nacos.feignClient.FeignClientProvide;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/nacos")
@Api(value = "初探微服务之间的调用-provide")
public class ProvideController {

    @Autowired
    private FeignClientProvide feignClientProvide;

    @GetMapping("/test/{name}")
    @ApiOperation("测试服务调用")
    public String getNacos(@PathVariable String name){
       return feignClientProvide.getNacos(name);
    }
}
