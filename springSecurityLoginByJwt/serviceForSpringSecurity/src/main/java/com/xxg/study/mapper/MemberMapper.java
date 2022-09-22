package com.xxg.study.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.xxg.study.domain.Member;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xxg
 * @since 2022-09-22
 */
@Mapper
@DS("master")
public interface MemberMapper extends BaseMapper<Member> {

}
