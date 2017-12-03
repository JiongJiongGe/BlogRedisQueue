package com.mybatis.controller;

import com.mybatis.domain.redbag.RedBagPackDetailModel;
import com.mybatis.domain.redbag.RedBagPackModel;
import com.mybatis.service.redbag.RedBagPackDetailServiceI;
import com.mybatis.service.redbag.RedBagPackServiceI;
import com.mybatis.util.RandomCreateRedUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * 红包相关
 *
 * Created by yunkai on 2017/11/30.
 */
@RestController
@RequestMapping("/redbag")
public class RedBagController {

    private static Logger logger = LoggerFactory.getLogger(RedBagController.class);

    @Autowired
    private RedBagPackServiceI redBagPackService;

    @Autowired
    private RedBagPackDetailServiceI redBagPackDetailService;

    /**
     * 设置红包
     *
     * @param money  红包总金额
     * @param num   红包数
     * @return
     */
    @GetMapping(value = "/set")
    public String setRedBag(Double money, Integer num){
        RedBagPackModel pack = new RedBagPackModel();
        pack.setMoney(money);
        pack.setNum(num);
        pack.setUserId(1);
        redBagPackService.create(pack);
        return "set red bag ... ";
    }

    /**
     * 领红包
     *
     * @param packId  红包Id
     * @return
     */
    @GetMapping(value = "/pick/bag")
    public String pickBag(Integer packId){
        Integer userId = new Random().nextInt(100);
        logger.info("userId = {}", userId);
        redBagPackDetailService.getBag(packId, userId);
        return "pick bag ... ";
    }
}
