package com.mybatis.service.impl.redbag;

import com.google.gson.Gson;
import com.mybatis.domain.redbag.RedBagPackDetailModel;
import com.mybatis.domain.redbag.RedBagPackModel;
import com.mybatis.mapper.redbag.RedBagPackMapper;
import com.mybatis.service.redbag.RedBagPackDetailServiceI;
import com.mybatis.service.redbag.RedBagPackServiceI;
import com.mybatis.service.redis.JedisServiceI;
import com.mybatis.util.RandomCreateRedUtil;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by yunkai on 2017/11/30.
 */
@Service("redBagPackService")
public class RedBagPackServiceImpl implements RedBagPackServiceI{

    private static Logger logger = LoggerFactory.getLogger(RedBagPackServiceImpl.class);

    @Autowired
    private RedBagPackMapper redBagPackMapper;

    @Autowired
    private RedBagPackDetailServiceI redBagPackDetailService;

    @Autowired
    private JedisServiceI jedisService;

    /**
     * 生成发红包记录
     *
     * @param pack
     */
    @Override
    public void create(RedBagPackModel pack){
        redBagPackMapper.create(pack);
        //红包Id
        int packId = redBagPackMapper.last_insert_id();
        //生成红包明细
        double[] bags = RandomCreateRedUtil.setRedBag(pack.getMoney(), pack.getNum());
        logger.info("bags = {}", new Gson().toJson(bags));
        if(bags != null && bags.length > 0){
            for(int i = 0; i < bags.length; i++) {
                RedBagPackDetailModel packDetail = new RedBagPackDetailModel();
                packDetail.setMoney(bags[i]);
                packDetail.setPackId(packId);
                redBagPackDetailService.create(packDetail);
                logger.info("packId = {}", packId);
                jedisService.lpush(packId + "", bags[i] + "");
            }
        }
    }

}
