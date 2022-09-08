package com.xxg.study.nacos.controller;


import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class consumerController {

    @GetMapping("/get/{name}")
    public String getNacos(@PathVariable("name") String name){
        System.out.println(name);
        return name;
    }
}
