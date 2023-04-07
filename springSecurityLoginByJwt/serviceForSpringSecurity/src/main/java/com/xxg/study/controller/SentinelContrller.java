package com.xxg.study.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * sentinel限流学习
 */
@RestController
public class SentinelContrller {
    /***
     * 流控：直接拒绝
     * @return
     */
    @GetMapping("/sentinel")
    public String sentinelTest(){
        return "sentinel--study";
    }

    /***
     * 关联
     * @return
     */
    @GetMapping("/read")
    public String readSentinelReadTest(){
        System.out.println("sentinel--read");
        return "sentinel--read";
    }
    /***
     * 关联
     * @return
     */
    @GetMapping("/write")
    public String readSentinelWriteTest(){
        System.out.println("sentinel--write");
        return "sentinel--write";
    }
    //sentinel 1.7.2以后版本,同一个controller的上下文会整合再一起，所以需要web-context-unify: false 来区分
    @GetMapping("/link/v1")
    public String readSentinelLinkTest(String v1){
        System.out.println("sentinel--link");
        return getLinkSentinel(v1);
    }
    @SentinelResource("link")
    private String getLinkSentinel(String v1) {
        System.out.println(v1);
        return v1;
    }

    @GetMapping("/link/v2")
    public String readSentinelLinkTest2(String v1){
        System.out.println("sentinel--link2");
        return getLinkSentinel(v1);
    }
}
