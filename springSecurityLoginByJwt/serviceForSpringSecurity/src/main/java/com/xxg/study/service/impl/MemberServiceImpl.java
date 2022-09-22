package com.xxg.study.service.impl;

import com.xxg.study.domain.Member;
import com.xxg.study.mapper.MemberMapper;
import com.xxg.study.service.MemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xxg
 * @since 2022-09-22
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

}
