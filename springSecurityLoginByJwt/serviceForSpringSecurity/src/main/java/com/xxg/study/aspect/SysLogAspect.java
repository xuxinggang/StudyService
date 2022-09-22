package com.xxg.study.aspect;

import com.google.gson.Gson;
import com.xxg.study.context.LocalUserContextUser.LocalUserContext;
import com.xxg.study.domain.SysLog;
import com.xxg.study.serializerUtils.RedisCache;
import com.xxg.study.service.SysLogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.time.LocalDate;

@Aspect
@Component
public class SysLogAspect {

    @Resource
    private SysLogService sysLogService;

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private RedisCache redisCache;

    @Pointcut("@annotation(com.xxg.study.annotation.SysLog)")
    public void SysLogService(){}

    @Around("SysLogService()")
   public void around(ProceedingJoinPoint pointcut) {
       try {
           long currentTimeMillis = System.currentTimeMillis();
           //方法调用
           Object proceed = pointcut.proceed();
           //计算执行时长
           Long time=System.currentTimeMillis()-currentTimeMillis;

           //生成日志
           saveSysLog(pointcut,time);
       } catch (Throwable throwable) {
           throwable.printStackTrace();
       }
    }

    private void saveSysLog(ProceedingJoinPoint pointcut, Long time) {
        MethodSignature signature = (MethodSignature) pointcut.getSignature();
        Method method = signature.getMethod();

        SysLog sysLog = new SysLog();
        com.xxg.study.annotation.SysLog annotation = method.getAnnotation(com.xxg.study.annotation.SysLog.class);
        if (annotation!=null){
            sysLog.setOperation(annotation.value());
        }
        //执行的方法
        String name = pointcut.getTarget().getClass().getName();
        String mthName = signature.getName();
        System.out.println("执行的方法为："+name);
        sysLog.setMethod(name+"."+mthName+"()");

        //请求参数
        Object[] args = pointcut.getArgs();
        if (args.length>0){
            String params = new Gson().toJson(args[0]);
            sysLog.setParams(params);
        }

        //获取request
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //设置ip地址
        sysLog.setIp(request.getRemoteAddr());
        String userName = LocalUserContext.getSysUser().getUserName();
        sysLog.setUsername((String) redisTemplate.opsForValue().get("users"));
//        sysLog.setUsername(userName);
        sysLog.setCreateDate(LocalDate.now());
        //保存日志
        sysLogService.save(sysLog);
    }
}
