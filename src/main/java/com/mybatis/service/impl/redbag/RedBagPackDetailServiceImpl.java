package com.mybatis.service.impl.redbag;

import com.mybatis.controller.RedBagController;
import com.mybatis.domain.redbag.RedBagPackDetailModel;
import com.mybatis.mapper.redbag.RedBagPackDetailMapper;
import com.mybatis.service.redbag.RedBagPackDetailServiceI;
import com.mybatis.service.redis.JedisServiceI;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by yunkai on 2017/11/30.
 */
@Service("redBagPackDetailService")
public class RedBagPackDetailServiceImpl implements RedBagPackDetailServiceI{

    private static Logger logger = LoggerFactory.getLogger(RedBagPackDetailServiceImpl.class);

    @Autowired
    private RedBagPackDetailMapper redBagPackDetailMapper;

    @Autowired
    private JedisServiceI jedisService;

    /**
     * 生成红包详情记录
     *
     * @param packDetail
     */
    @Override
    @Transactional
    public void create(RedBagPackDetailModel packDetail){
        redBagPackDetailMapper.create(packDetail);
    }

    /**
     * 用户领取红包，保存数据
     *
     * @param packId  红包Id
     * @param userId  用户Id
     */
    @Override
    @Transactional
    public String getBag(Integer packId, Integer userId){
        String value = jedisService.lpop(packId + "");
        logger.info("value = {}", value);
        if(StringUtils.isEmpty(value)){
            logger.info("红包已领完");
            return "红包已领完";
        }
        RedBagPackDetailModel detail = redBagPackDetailMapper.queryByMoney(Double.parseDouble(value), packId);
        //保存记录
        redBagPackDetailMapper.updateUserId(detail.getId(), userId);
        return value;
    }
}
