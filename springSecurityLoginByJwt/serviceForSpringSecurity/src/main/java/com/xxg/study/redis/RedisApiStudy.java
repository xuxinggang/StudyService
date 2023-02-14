package com.xxg.study.redis;


import com.xxg.study.common.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
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
        redisTemplate.opsForHash().entries("hash");
        return getName();
    }

    /**
     * redis api list set 操作
     * @return
     */
    @GetMapping("setOrList")
    public AjaxResult setOrList(){
        //序列化
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.setHashValueSerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer());

        ListOperations list = redisTemplate.opsForList();
        SetOperations setOperations = redisTemplate.opsForSet();
//        redisTemplate.delete("l1");
//
//        list.leftPushAll("l1",1,2,3,4,5,6,7);
//        System.out.println(list.leftPop("l1"));
//        System.out.println(list.leftPop("l1",5,TimeUnit.MILLISECONDS));//索引下标
//        System.out.println(list.leftPop("l1", Duration.ZERO));
//        list.range("l1",0,6).forEach(e->{
//            System.out.println(e);
//        });
//        list.leftPush("l2",8,9);
//        list.range("l2",0,4).forEach(e->{
//            System.out.println(e);
//        });


//        list.leftPush("myList","a1");
//        list.leftPushAll("myList","b1","c1","d1","e1");
//
//        System.out.println(list.leftPop("myList"));//一次去除最右边的数据
//        Member member = new Member(1l, 2l, "12");
//        list.rightPush("r1",member,member);
//        list.range("r1",0,-1).forEach(e-> System.out.println(e));

//        list.range("myList",0,-1).forEach(e-> System.out.println(e));

        setOperations.add("set",1,2,3,4,5,6);
        setOperations.add("set1",1,2,3,4,5,6,8,9);
        setOperations.difference("set","set1").forEach(e-> System.out.println(e));


        ZSetOperations zSetOperations = redisTemplate.opsForZSet();
        zSetOperations.add("zset","a",1.0);
        zSetOperations.add("zset","b",2.0);
        zSetOperations.add("zset","v",3.0);
        zSetOperations.add("zset","ac",4.0);
        zSetOperations.add("zset","aa",5.0);
        zSetOperations.add("zset","a1",6.0);
        zSetOperations.range("zset",0,-1).forEach(e-> System.out.println(e));
        zSetOperations.remove("zset,a,aa,b");
        System.out.println(zSetOperations.size("zset"));
        System.out.println(zSetOperations.zCard("zset"));
        return new AjaxResult("200", "操作成功", list.range("myList",0,-1).toString());
    }



    private String getName() {
//        String name=(String)redisTemplate.opsForValue().get("name");
        String name=(String)redisTemplate.opsForHash().get("hash","hash1");
        return name;
    }


}
