package com.xxg.study.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxg.study.domain.Menu;
import com.xxg.study.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface MenuMapper extends BaseMapper<Menu> {


   List<Menu> selectPermsByUserId(Long userId);
}