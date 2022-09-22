package com.xxg.study.context.LocalUserContextUser;

import com.xxg.study.domain.User;

public class LocalUserContext {

    private static final ThreadLocal<User> THREAD_LOCAL=new ThreadLocal<>();

    public static User getSysUser(){
        return THREAD_LOCAL.get();
    }

    public static void setSysUser(User user) {
        THREAD_LOCAL.set(user);
    }

    public static void remove(){
        THREAD_LOCAL.remove();
    }
}
