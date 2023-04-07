package com.xxg.study.nacos.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/privode")
public class gatewayController {

    @GetMapping("/get")
    public void getNacos(){
        System.out.println("hhhhhhh");
    }
}
