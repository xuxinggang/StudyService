//package com.xxg.study.test;
//
//import com.xxg.study.domain.Menu;
//import com.xxg.study.domain.User;
//import com.xxg.study.mapper.MenuMapper;
//import com.xxg.study.mapper.UserMapper;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import redis.clients.jedis.Jedis;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Set;
//
//@SpringBootTest
//public class MapperTest {
//    @Autowired
//    public UserMapper userMapper;
//
//    @Autowired
//    private MenuMapper menuMapper;
//
//
//    @Autowired
//    private RedisTemplate redisTemplate;
//
//    @Test
//    public void userTest(){
//        User user = new User();
//        user.setAvatar("hhhh");
//        user.setEmail("test@163.com");
//        List<User> users = userMapper.selectList(null);
//        List<Menu> strings = menuMapper.selectPermsByUserId(2l);
//        System.out.println(strings);
//        System.out.println(users);
//
//
//        System.out.println("------------redis操作begin-----------");
//        redisTemplate.opsForValue().set("users",users);
//
//        System.out.println("------------redis操作-----------");
//
//
//
//
//
//        //redis反序列化
////        redisTemplate.setKeySerializer(new StringRedisSerializer());
////        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
////        redisTemplate.opsForValue().append("x","x");
////        redisTemplate.opsForValue().append("x1","x");
////        redisTemplate.opsForValue().append("x2","x");
////        redisTemplate.opsForValue().append("x3","x");
////        redisTemplate.opsForValue().append("x4","x");
////        System.out.println(redisTemplate.keys("*"));
//////        List<String> keys= (List<String>) redisTemplate.keys("*");
//////        System.out.println(redisTemplate.opsForValue().multiGet(keys));
////        redisTemplate.opsForValue().append("name","xxf");
////        System.out.println(redisTemplate.opsForValue().getAndSet("name1", "xxf"));
////        System.out.println(redisTemplate.opsForValue().getAndSet("name1", "xxx"));
////        System.out.println(redisTemplate.opsForValue().get("name1"));
//    }
//
//    @Test
//    public void jedisTest(){
//        Jedis jedis = new Jedis("127.0.0.1", 6379);
//        jedis.set("test","hhhh");
//    }
//
//    public static String encodePassword(String password){
//        return new BCryptPasswordEncoder().encode(password);
//    }
//
//    public static void main(String[] args) {
//        String password = "123456";
//        String pwd = encodePassword(password);
//        System.out.println(pwd);
//    }
//}
