package com.xxg.study.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xxg.study.domain.Menu;
import com.xxg.study.domain.User;
import com.xxg.study.mapper.MenuMapper;
import com.xxg.study.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 重写userDetailService,返回用户信息
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private MenuMapper menuMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //查询用户信息
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getUserName,username);
        User user = userMapper.selectOne(userLambdaQueryWrapper);
        if (Objects.isNull(user)){
            throw new RuntimeException("用户名错误，请重试！！！");
        }
        //查询用户权限
        List<Menu> roles= menuMapper.selectPermsByUserId(user.getId());
        ArrayList<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Menu role : roles) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole()));
        }
        return new org.springframework.security.core.userdetails.User(user.getUserName(),user.getPassword(),grantedAuthorities);
    }
}
