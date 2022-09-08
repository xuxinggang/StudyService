package com.xxg.study.service.impl;

import com.xxg.study.domain.SysLog;
import com.xxg.study.mapper.SysLogMapper;
import com.xxg.study.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    private SysLogMapper sysLogMapper;

    @Override
    public void save(SysLog sysLog) {
        sysLogMapper.insert(sysLog);
    }
}
