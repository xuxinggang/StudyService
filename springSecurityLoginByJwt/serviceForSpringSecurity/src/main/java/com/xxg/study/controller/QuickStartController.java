package com.xxg.study.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xxg.study.annotation.SysLog;
import com.xxg.study.context.LocalUserContextUser.LocalUserContext;
import com.xxg.study.domain.User;
import com.xxg.study.mapper.UserMapper;
import com.xxg.study.serializerUtils.RedisCache;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.redis.core.ClusterOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

@RestController
@Api(value = "学习controller",tags = {"测试"})
public class QuickStartController {

      @Resource
      private UserMapper userMapper;

      @Resource
      private RedisTemplate redisTemplate;

    @Resource
    private RedisCache redisCache;

      @SysLog("redis")
      @GetMapping("/redisOpr")
      @ApiOperation("redis-api学习")
      public void redisCpr(String params){
          LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
          redisTemplate.setKeySerializer(new StringRedisSerializer());
          redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
          List<User> users = userMapper.selectList(null);
          redisTemplate.opsForValue().set("users",users.get(0).getUserName());
          LocalUserContext.setSysUser(users.get(0));
          System.out.println("----redis----操作-----");
          ClusterOperations clusterOperations = redisTemplate.opsForCluster();
          redisTemplate.opsForHash().put("hhh","wwww","rrr");
          System.out.println(redisTemplate.opsForHash().get("hhh", "wwww"));
          redisTemplate.opsForList().leftPush("list",1111);
          redisTemplate.opsForList().leftPush("list",2222);
          redisTemplate.opsForList().leftPush("list",3333);
          redisTemplate.opsForList().leftPush("list",4444);
          redisTemplate.opsForList().rightPush("list",0000);
          System.out.println(redisTemplate.opsForList().range("list", 0, 4));
          Set<ZSetOperations.TypedTuple> typedTuples = new HashSet<>();
      }
      @ApiOperation("函数式接口学习")
      @GetMapping("/test")
      public  String getTest(){
          return "xxg";
      }

      //函数式接口，只能有一个抽象方法
      static void method(String name, Consumer<String> consumer){
          consumer.accept(name);
      }
      static void method2(String name, Consumer<String> consumer,Consumer<String> consumer2){
          consumer.andThen(consumer2).accept(name);
      }
      static String supplied(Supplier<String> stringSupplier){
          return stringSupplier.get();
      }
    static Boolean checkString(String str, Predicate<String> predicate){
        return predicate.test(str);
    }
    static void change(String str, Function<String,Integer> function){
        System.out.println(function.apply(str));
    }
    public static void main(String[] args) {
        //函数式编程初体验
        method("xxg",(name)->{
            System.out.println(new StringBuilder(name).append("love you").reverse());
        });

        supplied(()->{
            return "哈哈哈";
        });

        method2("ooo",(name)->{
            System.out.println(name.toLowerCase());
        },(name)->{
            System.out.println(name.toUpperCase());
        });

        System.out.println(checkString("999", s -> s.equals("999")));
        change("99123",str->Integer.parseInt(str));
    }

    @GetMapping("thread")
    public void ThreadLocal(){
        List<User> users = userMapper.selectList(null);
        LocalUserContext.setSysUser(users.get(0));
        redisTemplate.opsForValue().append("user",users.get(0).getUserName());
        System.out.println("将数据放至本地线程中...");
    }
    @GetMapping("getThread")
    public User getThreadLocal(){
        System.out.println("hhhhhh");
        return LocalUserContext.getSysUser();
    }
    @Transactional(propagation = Propagation.REQUIRED)
    @GetMapping("redisCache")
    public void getRedisCache(){
        List<User> users = userMapper.selectList(null);
    }
}
