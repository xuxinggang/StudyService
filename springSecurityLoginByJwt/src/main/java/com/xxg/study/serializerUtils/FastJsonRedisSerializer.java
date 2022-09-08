package com.xxg.study.serializerUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.nio.charset.Charset;

/**
 * redis使用fastJson序列化
 * @param <T>
 */
public class FastJsonRedisSerializer<T> implements RedisSerializer<T> {

public static final Charset DEFAULT_CHARSET= Charset.forName("UTF-8");

public Class<T> clazz;

    static {
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
    }
    public FastJsonRedisSerializer(Class<T> clazz){
        super();
        this.clazz=clazz;
    }
    @Override
    public byte[] serialize(T t) throws SerializationException {
        if (t==null)
            return new byte[0];
        return JSON.toJSONString(t, SerializerFeature.WriteClassName).getBytes(DEFAULT_CHARSET);
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        if (bytes.length<=0 || bytes==null){
            return null;
        }
        String s = new String(bytes, DEFAULT_CHARSET);
        return JSON.parseObject(s,clazz);
  }
  protected JavaType getJavaType(Class<?> clazz){
        return TypeFactory.defaultInstance().constructType(clazz);
  }
}
