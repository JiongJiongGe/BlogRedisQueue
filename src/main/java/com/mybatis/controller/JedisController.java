package com.mybatis.controller;

import com.google.gson.Gson;
import com.mybatis.service.redis.JedisServiceI;
import com.mybatis.util.RandomCreateRedUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Queue;

/**
 * Created by yunkai on 2017/11/27.
 */
@RestController
@RequestMapping("/redis")
public class JedisController {

    private Logger logger = LoggerFactory.getLogger(RedisController.class);

    @Autowired
    private JedisServiceI jedisService;

    /**
     * set 值
     *
     * @return
     */
    @GetMapping(value = "/set/queue")
    public String setQueue(){
//        Queue<Integer> queue = new ConcurrentLinkedDeque<Integer>();
//        queue.add(1);
//        queue.add(2);
//        redisService.setKeyValue("queue", new Gson().toJson(queue));
        jedisService.setNxKeyValue("queue", "5");
        return "set queue is running ...";
    }

//    @GetMapping(value = "/operate/queue")
//    public String operateQueue(){
//        String queueString = jedisService.getValue("queue");
//        if(!StringUtils.isEmpty(queueString)){
//            Queue<Integer> queue = new Gson().fromJson(queueString, Queue.class);
//            logger.info("operate queue size = {}", queue.size());
//            queue.poll();
//            logger.info("queue value = {}", new Gson().toJson(queue));
//            redisService.setKeyValue("queue", new Gson().toJson(queue));
//        }
//        return "operate queue is running ...";
//    }

//    @GetMapping(value = "/get/now/queue")
//    public String getNowQueue(){
//        String queueString = jedisService.getValue("queue");
//        Queue<Integer> queue = new Gson().fromJson(queueString, Queue.class);
//        logger.info("queue value = {}", new Gson().toJson(queue));
//        return "get now queue ...";
//    }

    /**
     *  递减
     *
     */
    @GetMapping(value = "/test/nx")
    public void testNx(){
        jedisService.decrKey("queue");
    }

    /**
     * get 值
     *
     * @return
     */
    @GetMapping(value = "/get/queue")
    public String getQueue(){
        String value = jedisService.getValue("queue");
        logger.info("value = {}", value);
        return "get queue is running ...";
    }

    /**
     * sadd  Set集合
     * @return
     */
    @GetMapping(value = "/sadd")
    public String sadd(){
        jedisService.sadd("yunkai", "1");
        jedisService.sadd("yunkai", "2");
        return "sadd is running ...";
    }

    /**
     * lpush List集合
     * @return
     */
    @GetMapping(value = "/lpush")
    public String lpush(){
        for(int i = 0; i < 10; i++) {
            jedisService.lpush("yunkai", i + "");
        }
        return "lpush is running ... ";
    }

    /**
     * lpop 移除List第一个元素并获取该值
     */
    @GetMapping(value = "lpop")
    public String lpop(){
        jedisService.lpop("yunkai");
        return "lpop is running ... ";
    }

    /**
     * sismember
     * @return
     */
    @GetMapping(value = "/sismember")
    public String sismember(){
        jedisService.sismember("yunkai", "1");
        return "sismember is running";
    }
}
