package com.xxg.study.redis;


import com.baomidou.dynamic.datasource.tx.ConnectionFactory;
import com.xxg.study.config.RedisConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/redis")
@Slf4j
public class RedisApiStudy {

    @Resource
    private RedisTemplate redisTemplate;


    @GetMapping("/String")
    public String getString(){
        //序列化
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.setHashValueSerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer());
//        redisTemplate.opsForValue().set("name","888");
        //如果没有这个key，就会生成对应的key-value ,如果有就后面拼接
        redisTemplate.delete("h1");
         if (redisTemplate.opsForValue().setIfAbsent("h1","xxxxxappendappendappend")){
             System.out.println("确实不存在 h1 ！！！");
             redisTemplate.delete("x");
             System.out.println(redisTemplate.opsForValue().get("h1"));
         }
         redisTemplate.opsForValue().append("x","append");
         redisTemplate.opsForHash().put("hash","hash1","hash1");
         redisTemplate.opsForHash().put("hash","hash2","hash2");
         redisTemplate.opsForHash().put("hash","hash3","hash3");
         redisTemplate.opsForHash().put("hash","hash4","hash4");
//         redisTemplate.opsForHash().delete("hash","hash1");
//         if (redisTemplate.opsForHash().hasKey("hash","hash1")){
//             System.out.println("存在此key");
//         }else {
//             System.out.println("不存在此key");
//         }
//        for (Object hash1 : redisTemplate.opsForHash().entries("hash1").entrySet()) {
//            System.out.println(hash1);
//        }
        Map map=new HashMap<>();
        map.put("user","name");
        map.put("user1","age");
        redisTemplate.opsForHash().putAll("keys",map);
        System.out.println(redisTemplate.opsForHash().entries("keys"));
        redisTemplate.opsForHash().entries("keys").entrySet().forEach(e-> System.out.println(e));
        System.out.println(redisTemplate.opsForHash().getOperations().boundHashOps("keys"));
        redisTemplate.opsForHash().getOperations().boundHashOps("keys").values().forEach(e-> System.out.println(e));
        redisTemplate.opsForHash().getOperations().expire("x",2, TimeUnit.MINUTES);
        return getName();
    }

    private String getName() {
//        String name=(String)redisTemplate.opsForValue().get("name");
        String name=(String)redisTemplate.opsForHash().get("hash","hash1");
        return name;
    }

}
