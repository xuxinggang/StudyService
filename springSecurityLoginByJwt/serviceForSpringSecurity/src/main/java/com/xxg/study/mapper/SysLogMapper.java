package com.xxg.study.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxg.study.domain.Menu;
import com.xxg.study.domain.SysLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
@DS("sub")
public interface SysLogMapper extends BaseMapper<SysLog> {


}