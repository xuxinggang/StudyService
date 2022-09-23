package com.xxg.study.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.xxg.study.common.AjaxResult;
import com.xxg.study.domain.Member;
import com.xxg.study.mapper.MemberMapper;
import com.xxg.study.service.MemberService;
import com.xxg.study.vo.MemberVo;
import net.minidev.json.writer.UpdaterMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xxg
 * @since 2022-09-22
 */
@RestController
public class MemberController {

    @Resource
    private MemberService memberService;

    @Autowired
    private MemberMapper memberMapper;

    @PostMapping("/member/add")
    public AjaxResult addMember(MemberVo memberVo){
        List<Member> members=new ArrayList<>();
        Member member = new Member();
        for (int i = 0; i < 100000; i++) {
            BeanUtils.copyProperties(memberVo,member);
            member.setBal(new BigDecimal(i++));
            member.setId(new Random().nextLong());
            member.setUserId(new Random().nextLong());
            member.setCreTme(LocalDateTime.now());
            members.add(member);
        }
        memberService.saveBatch(members);
        AjaxResult ajaxResult = new AjaxResult("200","批量新增成功","YES");
        return ajaxResult;
    }

    @PostMapping("/member/update")
    public AjaxResult updateMember(){
        List<Member> members = memberMapper.selectList(null);
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        LocalDateTime now = LocalDateTime.now();
        System.out.println("批量更新前："+now);
        members.forEach(e->{
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    e.setUpdTme(LocalDateTime.now());
                    e.setMemLvl(1L);
                    e.setSts("0");
                    memberMapper.update(e,null);
                }
            });

         });
//        boolean b = memberService.updateBatchById(members);
        LocalDateTime after = LocalDateTime.now();
        System.out.println("批量更新后："+after);
        AjaxResult ajaxResult;
        if (true){
            ajaxResult = new AjaxResult("200","批量更新成功","YES");
        }else {
            ajaxResult = new AjaxResult("500","批量更新失败","YES");
        }
        return ajaxResult;
    }
}

