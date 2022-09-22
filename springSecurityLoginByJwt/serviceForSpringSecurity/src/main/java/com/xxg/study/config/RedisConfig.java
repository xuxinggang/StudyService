package com.xxg.study.config;

import com.xxg.study.serializerUtils.FastJsonRedisSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<Object,Object> redisTemplate(RedisConnectionFactory connectionFactory){
        RedisTemplate<Object, Object> objectObjectRedisTemplate = new RedisTemplate<>();
        objectObjectRedisTemplate.setConnectionFactory(connectionFactory);

        /*redis的序列化器*/
        /*使用StringRedisSerializer来序列化和反序列化redis的key值*/
        objectObjectRedisTemplate.setKeySerializer(new StringRedisSerializer());
        objectObjectRedisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        /*HASH的也使用StringRedisSerializer来序列化和反序列化redis的key值*/
        objectObjectRedisTemplate.setHashKeySerializer(new StringRedisSerializer());
        objectObjectRedisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer());

        objectObjectRedisTemplate.afterPropertiesSet();
        return objectObjectRedisTemplate;
    }
}
