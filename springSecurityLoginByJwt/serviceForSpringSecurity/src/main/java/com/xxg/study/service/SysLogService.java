package com.xxg.study.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xxg.study.domain.SysLog;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

public interface SysLogService{

    void save(SysLog sysLog);
}
