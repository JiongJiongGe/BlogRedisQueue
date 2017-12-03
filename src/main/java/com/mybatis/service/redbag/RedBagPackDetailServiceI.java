package com.mybatis.service.redbag;

import com.mybatis.domain.redbag.RedBagPackDetailModel;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by yunkai on 2017/11/30.
 */
public interface RedBagPackDetailServiceI {

    /**
     * 生成红包详情记录
     *
     * @param packDetail
     */
    public void create(RedBagPackDetailModel packDetail);

    /**
     * 用户领红包
     *
     * @param packId  红包Id
     * @param userId  用户Id
     * @return
     */
    public String getBag(Integer packId, Integer userId);
}
