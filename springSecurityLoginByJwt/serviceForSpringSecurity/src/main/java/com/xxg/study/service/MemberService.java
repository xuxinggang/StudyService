package com.xxg.study.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.xxg.study.domain.Member;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xxg
 * @since 2022-09-22
 */
@DS("master")
public interface MemberService extends IService<Member> {

}
