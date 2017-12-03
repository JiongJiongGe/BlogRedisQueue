package com.mybatis.controller;

import com.google.gson.Gson;
import com.mybatis.domain.pen.YunKaiPenModel;
import com.mybatis.service.pen.YunKaiPenServiceI;
import com.mybatis.service.redis.RedisServiceI;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by yunkai on 2017/10/11.
 */
@RestController
@RequestMapping("/redis")
public class RedisController {

    private Logger logger = LoggerFactory.getLogger(RedisController.class);

    private static ReentrantLock lock = new ReentrantLock();

    @Autowired
    private YunKaiPenServiceI yunKaiPenService;

    @Autowired
    private RedisServiceI redisService;

    @GetMapping("/plug/data")
    public String plugData(){
        List<YunKaiPenModel> pens = yunKaiPenService.findPenList();
        if(CollectionUtils.isNotEmpty(pens)){
            for(YunKaiPenModel pen : pens){
                redisService.setKeyValue(pen.getId(), pen.getPenNum());
            }
        }
        return "plug data is running ...";
    }

    @GetMapping("/concurrency")
    public String concurrency(Short penId){
        Integer num = redisService.getValue(penId);
        num --;
        if(num <= 0){
            logger.info("库存不足");
        }else{
            logger.info("num = {}", num);
        }
        redisService.setKeyValue(penId, num);
        return "concurrency is running ...";
    }

    /**
     *
     * @return
     */
    @GetMapping("/set")
    public String set(){
        List<YunKaiPenModel> pens = yunKaiPenService.findPenList();
        LinkedHashMap<String, Integer> penMap = new LinkedHashMap<String, Integer>();
        if(CollectionUtils.isNotEmpty(pens)){
            for(YunKaiPenModel pen : pens){
                penMap.put(pen.getId() + "", pen.getPenNum());
            }
        }
        redisService.setKeyValue("penMap", penMap);
        return "set is running";
    }

    /**
     * redis + Reentrantlock 实现减库存
     *
     * @param penId
     * @return
     */
    @GetMapping("/operate/reen")
    public String reen(String penId){
        int exec = 0;
        LinkedHashMap<String, Integer> penMap = redisService.getMapValue("penMap");
        logger.info("penMap = {}", new Gson().toJson(penMap));
        if(!penMap.isEmpty()){
            Integer penNum = penMap.get(penId);
            if(penNum <= 0){
                logger.info("库存不足 1");
                return "库存不足 1";
            }
            try {
                if (penNum <= 1 && lock.tryLock(1, TimeUnit.SECONDS)) {
                    try {
                        logger.info("going ...");
                        exec = yunKaiPenService.updatePenNum(penId);
                    }catch (Exception e){
                        logger.error("try lock error is {}", e);
                    }finally {
                        lock.unlock();
                        if(exec > 0){
                            logger.info("成功");
                            penNum = yunKaiPenService.getNumById(penId);
                            penMap.put(penId, penNum);
                            redisService.setKeyValue("penMap", penMap);
                            return "成功 1";
                        }else{
                            logger.info("库存不足 2");
                            return "库存不足 2";
                        }
                    }
                }else{
                    logger.info("wait ...");
                    yunKaiPenService.updatePenNum(penId);
                    penNum = yunKaiPenService.getNumById(penId);
                    penMap.put(penId, penNum);
                    redisService.setKeyValue("penMap", penMap);
                    logger.info("成功");
                    return "成功 2";
                }
            }catch (Exception e){
                logger.error("error = {}", e);
            }
            return "库存不足 3";
        }else{
            logger.info("库存不足 4");
            return "库存不足 4";
        }
    }

    @GetMapping("/get/now")
    public String getNow(){
        LinkedHashMap<String, Integer> penMap = redisService.getMapValue("penMap");
        logger.info("penMap = {}", new Gson().toJson(penMap));
        return "get now ...";
    }



//    redis 自带计数器
//
//    队列的方式



}
