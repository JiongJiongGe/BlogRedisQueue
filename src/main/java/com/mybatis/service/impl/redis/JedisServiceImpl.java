package com.mybatis.service.impl.redis;

import com.mybatis.service.redis.JedisServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by yunkai on 2017/11/27.
 */
@Service("jedisService")
public class JedisServiceImpl implements JedisServiceI{

    private final static Logger logger = LoggerFactory.getLogger(JedisServiceImpl.class);

    @Autowired
    private JedisPool jedisPool;

    private Jedis getResource() {
        return jedisPool.getResource();
    }

    @Override
    public void setKeyValue(String key, String value){
        try {
            String state = getResource().set(key, value);
            logger.info("state = {}", state);
        }catch (Exception e){
            logger.error(" error = {} ", e);
        }finally {
            //释放Jedis对象
            jedisPool.returnBrokenResource(getResource());
            //返还到连接池
            jedisPool.returnResource(getResource());
        }

    }

    @Override
    public void setNxKeyValue(String key, String value){
        try {
            long nxState = getResource().setnx(key, value);
            logger.info("nxState = {}", nxState);
        }catch (Exception e){
            logger.error(" error = {} ", e);
        }finally {
            //释放Jedis对象
            jedisPool.returnBrokenResource(getResource());
            //返还到连接池
            jedisPool.returnResource(getResource());
        }
    }

    @Override
    public String getValue(String key){
        String value = "";
        try {
            value = getResource().get(key);
        }catch (Exception e){
            logger.error( " error = {} ", e);
        }finally {
            //释放Jedis对象
            jedisPool.returnBrokenResource(getResource());
            //返还到连接池
            jedisPool.returnResource(getResource());
        }
        return value;
    }

    @Override
    public void decrKey(String key){
        try {
            Long value = getResource().decr(key);
            logger.info("value = {}", value);
        }catch (Exception e){
            logger.error(" error = {} ", e);
        }finally {
            //释放Jedis对象
            jedisPool.returnBrokenResource(getResource());
            //返还到连接池
            jedisPool.returnResource(getResource());
        }
    }

    @Override
    public Long sadd(String key, String value){
        Long result = (long) 0;
        try{
            result = getResource().sadd(key, value);
            logger.info("result = {}", result);
        }catch (Exception e){
            logger.error(" error = {} ", e);
        }finally {
            jedisPool.returnBrokenResource(getResource());
            jedisPool.returnResource(getResource());
        }
        return result;
    }

    @Override
    public Long lpush(String key, String value){
        Long result = (long)0;
        try{
            result = getResource().lpush(key, value);
            logger.info("result = {}", result);
        }catch (Exception e){
            logger.error(" error = {}", e);
        }finally {
            jedisPool.returnBrokenResource(getResource());
            jedisPool.returnResource(getResource());
        }
        return result;
    }

    @Override
    public String lpop(String key){
        String value = "";
        try{
            value = getResource().lpop(key);
            logger.info(" value = {}", value);
        }catch (Exception e){
            logger.error(" error = {} ", e);
        }finally {
            jedisPool.returnBrokenResource(getResource());
            jedisPool.returnResource(getResource());
        }
        return value;
    }

    @Override
    public void sismember(String key, String member){
        Boolean result;
        try{
            result = getResource().sismember(key, member);
            logger.info("result = {}", result);
        }catch (Exception e){
            logger.error(" error = {}", e);
        }finally {
            jedisPool.returnBrokenResource(getResource());
            jedisPool.returnResource(getResource());
        }
    }


}
