package com.mybatis.service.impl.redis;

import com.mybatis.service.redis.RedisServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by yunkai on 2017/10/11.
 */
@Service("redisService")
public class RedisServiceImpl implements RedisServiceI {

    private final static Logger logger = LoggerFactory.getLogger(RedisServiceImpl.class);

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void setKeyValue(Short key, Integer value){
        ValueOperations<Short, Integer> valueOps = redisTemplate.opsForValue();
       // valueOps.set(key, value, 60, TimeUnit.SECONDS);   //设置过期时间
        valueOps.set(key, value);
    }

    @Override
    public void setKeyValue(String key, String value){
        ValueOperations<String, String> valueOps = redisTemplate.opsForValue();
        valueOps.set(key, value);
    }

    @Override
    public void setKeyValue(String key, LinkedHashMap<String, Integer> map){
        redisTemplate.opsForHash().putAll(key, map);
    }

    @Override
    public Integer getValue(Short key){
        ValueOperations<Short, Integer> valueOps = redisTemplate.opsForValue();
        return valueOps.get(key);
    }

    @Override
    public String getValue(String key){
        ValueOperations<String, String> valueOps = redisTemplate.opsForValue();
        return valueOps.get(key);
    }

    @Override
    public LinkedHashMap<String, Integer> getMapValue(String key){
        return (LinkedHashMap<String, Integer>) redisTemplate.opsForHash().entries(key);
    }

}
