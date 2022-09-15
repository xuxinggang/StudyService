package com.xxg.study.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxg.study.domain.Menu;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@DS("master")
public interface MenuMapper extends BaseMapper<Menu> {


   List<Menu> selectPermsByUserId(Long userId);
}