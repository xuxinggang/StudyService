package com.xxg.study.controller;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableScheduling
public class ScheduledController {

    @Scheduled(cron = "0 * * * * *")
    public void testSchedule(){
        System.out.println("定时任务启动");
    }
}
