package com.xxg.study.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxg.study.domain.User;
import org.apache.ibatis.annotations.Mapper;


@Mapper
@DS("master")
public interface UserMapper extends BaseMapper<User> {
}

